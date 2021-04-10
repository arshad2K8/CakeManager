package com.cake.manager;

import com.cake.manager.domain.Cake;
import com.cake.manager.repository.CakeRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@ActiveProfiles("local-noauth")
@AutoConfigureMockMvc
@SpringBootTest(classes = CakeManagerApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CakeManagerApplicationTests {

	@Autowired
	private MockMvc mvc;

	@Autowired
	private CakeRepository cakeRepository;


	@Test
	void getCakes() throws Exception {

		mvc.perform(get("/api/cakes")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}

	@Test
	void testPostCakeSuccesfully() throws Exception {
		Cake cake = new Cake();
		cake.setTitle("title1");
		cake.setDescription("description");
		cake.setImage("image");

		String cakeJson = new ObjectMapper().writeValueAsString(cake);
		mvc.perform(post("/api/cakes")
				.content(cakeJson)
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isCreated());

	}

	@Test
	void testDeleteCakeSuccesfully() {

	}

}
