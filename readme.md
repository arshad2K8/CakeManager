# Cake Manager Service

## How To Run
* Git Clone CakeManager Project `git clone https://github.com/arshad2K8/CakeManager.git`
* `cd ~/CakeManager`
* run `./mvnw spring-boot:run`
* Open a browser and type `http://localhost:8080/`
* This app is Secured by Github OAuth2 login

* If you want to run Un-Authorized version of app please pass in Spring profile `-Dspring.profiles.active=local-noauth`

# Add New Cake Using Curl
curl -X POST localhost:8080/api/cakes -d "{\"title\": \"CakeTitle1\", \"image\": \"CakeI mage1\", \"description\": \"CakeDescription1\"}" -H "Content-Type:application/json"


