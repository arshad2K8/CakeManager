package com.cake.manager.init;

import com.cake.manager.domain.Cake;
import com.cake.manager.repository.CakeRepository;
import com.cake.manager.utils.HttpRestResult;
import com.cake.manager.utils.HttpUtils;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import org.apache.http.client.methods.HttpGet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import java.io.IOException;

@Component
public class DatabaseLoader implements CommandLineRunner {

    private static final Logger LOGGER = LoggerFactory.getLogger(DatabaseLoader.class);
    private static final String CAKES_URL = "https://gist.githubusercontent.com/hart88/198f29ec5114a3ec3460/raw/8dd19a88f9b8d24c23d9960f3300d0c917a4f07c/cake.json";

    private final CakeRepository cakeRepository;

    public DatabaseLoader(CakeRepository cakeRepository) {
        this.cakeRepository = cakeRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        LOGGER.info("Running DatabaseLoader");
        HttpRestResult<Integer, String> cakesContent;

        cakesContent = HttpUtils.execute(new HttpGet(CAKES_URL));

        LOGGER.info("parsing cake json");
        try (JsonParser parser = new JsonFactory().createParser(cakesContent.getResponse())) {
            if (JsonToken.START_ARRAY != parser.nextToken()) {
                throw new ServletException("bad token");
            }

            JsonToken nextToken = parser.nextToken();
            while (nextToken == JsonToken.START_OBJECT) {
                LOGGER.info("creating cake entity");

                Cake cake = getCakeEntity(parser);
                LOGGER.info("Constructed cakeEntity {}", cake);
                cakeRepository.save(cake);

                nextToken = parser.nextToken();
                LOGGER.info("nextToken {}", nextToken);
                nextToken = parser.nextToken();
                LOGGER.info("nextToken {}", nextToken);
                LOGGER.info("init finished");
            }
        } catch (IOException e) {
            LOGGER.error("Exception during JsonParser ", e);
            throw new ServletException(e);
        }
    }

    private static Cake getCakeEntity(JsonParser jsonParser) throws IOException {
        Cake cakeEntity = new Cake();
        for(int i = 0; i < 3; i++) {
            String fieldName = jsonParser.nextFieldName();
            String value = jsonParser.nextTextValue();
            if(fieldName.equalsIgnoreCase("title")) {
                cakeEntity.setTitle(value);
            }else if(fieldName.equalsIgnoreCase("desc")) {
                cakeEntity.setDescription(value);
            }else if(fieldName.equalsIgnoreCase("image")) {
                cakeEntity.setImage(value);
            }
        }
        return cakeEntity;
    }
}
