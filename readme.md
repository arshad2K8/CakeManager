# Cake Manager Service

## How To Run
* Git Clone CakeManager Project `git clone https://github.com/arshad2K8/CakeManager.git`
* `cd ~/CakeManager`
* run `./mvnw spring-boot:run`
* Open a browser and type `http://localhost:8080/`
* This app is Secured by Github OAuth2 login

* If you want to run Un-Authorized version of app please pass in Spring profile `-Dspring.profiles.active=local-noauth`

# Tech Stack
* Backend for this App is using Spring Data Rest
* `https://docs.spring.io/spring-data/rest/docs/current/reference/html/#intro-chapter`
* For frontend its React.
* App is secured by Oauth2 via Github


## Home Page
* `http://localhost:8080`

* If you browse the endpoint `http://localhost:8080/cakes` , you will be able to download cakes as json file

## CRUD Endpoints  
* GET CAKES
`curl http://localhost:8080/api/cakes`
  
* POST CAKES
`curl -X POST localhost:8080/api/cakes -d "{\"title\": \"CakeTitle1\", \"image\": \"CakeI mage1\", \"description\": \"CakeDescription1\"}" -H "Content-Type:application/json"`
  

* DELETE CAKE
curl -X DELETE http://localhost:8080/api/cakes/20
  

* Spring Security - OAuth2 - Github



Test Change