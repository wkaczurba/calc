# Calc
Simple API-based calculator. Uses swagger. Simply run & use:
 - http://127.0.0.1:8080/api/v1/calc/swagger-ui.html

## The app uses:
 - Standard Spring-MVC, Swagger
 
## Controller:
  - Uses double primitives to make things easier
  - For financial and scientific use it should uses BigDecimal

## Tests:
 - CalcApplicationTests : full integration test
 - ControllerTest : web-layer only test
 - DecimalCalculatorServiceTest

## Documentation
 - Sourcecode and JavaDocs generated through POM 
 - Swagger

## Extensions: 
 - Should consider security
 - Need to add CircleCi for GITHUB, but no time for that at the moment.
 
## REST:
 - Uses GET and return JSON; other REST-verbs not appropriate.
 - Could use POST if we were to store (non-indepotenet) sequence of calculations on the server.
 - API versioned (server context "v1") set through application.properties.
