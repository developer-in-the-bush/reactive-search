**A simple search engine**


_How to launch this project_

1. Clone the github project
2. Ensure that `mvnw` script has execution rights, `8080` port is free and from the project root type `mvnw spring-boot:run (*sh)` || `mvnw.cmd spring-boot:run (windows)`
3. Open in browser [http://localhost:8080/index.html](http://localhost:8080/index.html) 

Please, ensure you have a fast enough channel without strict traffic limitations in order maven frontend plugin can quickly install node, npm and modules.

_Smoke and unit tests_

1. `mvnw` || `mvnw.cmd test`

_Tips & tricks_

For boosted web interface development js sources tree could be put on the watch via `target\node\npm.cmd run watch` command. Thus, after js code changes all you need to see update(s) is to reload the browser page - application context redeployment is not required. 