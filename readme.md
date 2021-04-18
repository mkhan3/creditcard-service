1. To run spring boot application:  mvn spring-boot:run
2. To run integration testing:      mvn verify
3. To run unit testing:             mvn test
Some keypoints:
--------------------
4. CustomizedResponseEntityExceptionHandler (which extends ResponseEntityExceptionHandler) is used to handle exceptions thrwon by any controllers. This handler is also a rest controller 
so it can send customized exception message to client.
5. CreditCardManager is an extra layer between rest controller and repository.
6. To receive client request or generate client response, a valueobject (CreditCardValue) is used. javax.validation.constraints were applied on this valueobject.
7. CreditCardEntity is an entity object. It has been kept separate from the value object.
8. After successfully creating Credit card resource, the api will send HTTP.OK response with the location of the newly created resource.
9. maven-surefire-plugin is used to run integration testing. Unit testing has also been separated from the integration testing . 

 