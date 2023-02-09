# Spring Functional Test
This project provides functional tests of Macchinetta Server Framework (1.x) Development Guideline.

* [JSP](https://macchinetta.github.io/server-guideline/list/)
* [Thymeleaf](https://macchinetta.github.io/server-guideline-thymeleaf/list/)

## Project structure
The build process of functional tests using JSP for View is built with the following projects.

```console
spring-functionaltest
├── spring-functionaltest-env
├── spring-functionaltest-model
├── spring-functionaltest-webservice
├── spring-functionaltest-domain
├── spring-functionaltest-web
├── spring-functionaltest-env-oauth2
├── spring-functionaltest-web-oauth2-resource
├── spring-functionaltest-initdb
└── spring-functionaltest-selenium
```

If you want to use Thymeleaf for View, switch the web projects in the build process to the following project for Thymeleaf.

```console
spring-functionaltest
├── spring-functionaltest-web-thymeleaf
└── spring-functionaltest-web-oauth2-resource-thymeleaf
```

## How to perform functional test

**Preconditions are as follow:**

* [JDK 8 or JDK 11 or JDK 17](https://developers.redhat.com/products/openjdk/download) installed (`JAVA_HOME` defined as environment variable)
* [Maven](https://maven.apache.org/download.cgi) installed (Can run `mvn` command)
* Firefox([for personal](https://www.mozilla.org/en-US/firefox/all/#product-desktop-release) or [ESR](https://www.mozilla.org/en-US/firefox/all/#product-desktop-esr)) installed (ESR is used on our CI environment)


### [Step 1] Create database of PostgreSQL (Optional)
If [PostgreSQL](http://www.postgresql.org/) use as database , you need to create database of PostgreSQL into local machine. (PostgreSQL can download via [here site](http://www.postgresql.org/download/)).

> **Note:**
>
> If [H2](http://www.h2database.com/html/main.html) use as database, you can skip this step.

#### Download & install
By default, database owner is `postgres` user, and password of `postgres` user is `'P0stgres'`.

#### Create database
```console
$ createdb -U postgres --locale=C --encoding=UTF8 --template=template0 spring-functionaltest
$ createdb -U postgres --locale=C --encoding=UTF8 --template=template0 spring-functionaltest-open
$ createdb -U postgres --locale=C --encoding=UTF8 --template=template0 spring-functionaltest-close
```
### [Step 2] Clone a repository
Clone the `Macchinetta/spring-functionaltest` repository into local machine.

```console
$ git clone https://{remote host url}/Macchinetta/spring-functionaltest.git
```

### [Step 3] Build artifacts
Build artifacts using maven commands as follows.

#### Case that use embedded H2 as database

```console
$ cd {your repository directory}
$ git checkout {target branch}
$ mvn -U clean install -am -pl spring-functionaltest-web
$ mvn -U clean install -am -pl spring-functionaltest-web-oauth2-resource
```

> **Note:**
>
> Using JDK 11 or JDK17, add `-P local,warpack-jaxb,warpack-activation`. (Specify the `local` profile explicitly because JDK 11 or JDK 17 will automatically activate the `jdk11` or `jdk17` profile.)

#### Case that use PostgreSQL as database

```console
$ cd {your repository directory}
$ git checkout {target branch}
$ mvn -U clean install -am -pl spring-functionaltest-web -P tomcat9-postgresql,warpack-env,warpack-jstl,warpack-cxf,warpack-transaction,travis
$ mvn -U clean install -am -pl spring-functionaltest-web-oauth2-resource -P tomcat9-postgresql,warpack-env,warpack-jstl,travis
```

> **Note:**
>
> Using JDK 11 or JDK17, add `-P warpack-jaxb`.

> **Note:**
>
> If you not use default user(`postgres`) or password(`P0stgres`), you should modify settings in `spring-functionaltest-env/configs/tomcat9-postgresql/ContainerConfigXML/context.xml`.

### [Step 4] Initialize database (Optional)
If PostgreSQL use as database, initialize database before run functional test.

```console
$ mvn -U sql:execute -pl spring-functionaltest-initdb -P local-postgres
$ mvn -U sql:execute -pl spring-functionaltest-initdb -P local-postgres-open
$ mvn -U sql:execute -pl spring-functionaltest-initdb -P local-postgres-close
```

> **Note:**
>
> If you not use default user(`postgres`) or password(`P0stgres`), you should specify `-Ddb.username={your user}` or `-Ddb.password={your password}` or both.

### [Step 5] Startup Tomcat9 and deploy war file
Startup Tomcat9 and deploy war file using [CARGO maven plugin](https://codehaus-cargo.github.io/cargo/Maven+3+Plugin.html).

#### Case that use embedded H2 as database

```console
$ cd {your repository directory}
$ mvn -U cargo:run -pl spring-functionaltest-web
```

#### Case that use PostgreSQL as database (use Tomcat JNDI Resource)

```console
$ cd {your repository directory}
$ mvn -U cargo:run -pl spring-functionaltest-web -P travis
```

> **Note:**
>
> For both JSP and Thymeleaf, the same war file name will be used.  
> you can access application at `http://localhost:8080/spring-functionaltest-web/`.

> **Note:**
>
> Shutdown trigger is "Ctrl + C" on console.

### [Step 6] Deploy OAuth war files
Deploy OAuth war files to already running container.

```console
$ cd {your repository directory}
$ mvn -U cargo:deploy -pl spring-functionaltest-web -Dcargo.deployable.artifactId=spring-functionaltest-web-oauth2-resource  -Dcargo.deployable.warName=spring-functionaltest-web-oauth2-resource
```

### [Step 7] Run functional tests
Run tests using Selenium(`WebDriver`) on JUnit.

```console
$ cd {your repository directory}
$ mvn -U test -pl spring-functionaltest-selenium -Dwdm.cachePath=/opt/geckodriver -Dwdm.geckoDriverVersion=0.32.0
```

> **Note:**
>
> In the case of Thymeleaf, add `-Dtest.environment.view=thymeleaf`.

> **Note:**
>
> By dafault latest version [geckodriver](https://github.com/mozilla/geckodriver/releases) will download automatically to `~/.cache/selenium` by [webdrivermanager](https://github.com/bonigarcia/webdrivermanager).
> To specify the download directory, specify "`-Dwdm.cachePath`" in the startup argument.
> To specify the version of geckoDriver, specify "`-Dwdm.geckoDriverVersion`" in the startup argument.
>
> See  [advanced-configuration](https://bonigarcia.dev/webdrivermanager/#advanced-configuration) for configuration details.

## Appendix

### How to use latest or any branch snapshot of Common Library

If latest or any branch snapshot of Common Library want to use, install latest or any branch snapshot before build and test.

#### Clone terasoluna-gfw repository into local machine

```console
$ git clone https://github.com/terasolunaorg/terasoluna-gfw.git
```

#### Install latest or any branch snapshot of Common Library into local machine

```console
$ cd {your repository directory of terasoluna-gfw}
$ git checkout {target branch}
$ mvn -U clean install
```

### How to build war for various environments

Using the maven profile , build the war in accordance with the environment that you want to deploy.

```console
$ cd {your repository directory}
$ git checkout {target branch}
$ mvn -U package -am -pl spring-functionaltest-web -P tomcat9-postgresql,warpack-env,warpack-jstl,warpack-cxf,warpack-transaction,travis
```

Profiles that are available are as follows.

#### The Combination of application server and a database

|        environments       | specify maven profiles                                                               |
|:-------------------------:|--------------------------------------------------------------------------------------|
|        Tomcat9 + H2       | (not specify)                                                                        |
|    Tomcat9 + Postgresql   | `tomcat9-postgresql,warpack-env,warpack-jstl,warpack-cxf,warpack-transaction,travis` |
| Tomcat9 + Postgresql [^1] | `tomcat9-postgresql,warpack-jstl,warpack-cxf,warpack-transaction`                    |

[^1]: Please deploy spring-functionaltest-web.war, spring-functionaltest-env.jar, and context.xml.

#### The JVM version of build and runtime environment

Profiles for Java version are automatically activated.

|   environments  | specify maven profile                       |
|:---------------:|---------------------------------------------|
|      Java8      | (not specify)                               |
|      Java11     | `jdk11,warpack-jaxb,warpack-activation` [^2]|
|      Java17     | `jdk17,warpack-jaxb,warpack-activation` [^2]|

[^2]: `warpack-xxx` is required if the activatin lib is not provided by the AP server(eg. Tomcat).

### How to switch bean definition for various environments

Using the spring profile , enable to switch the bean definition in accordance with the environment that you want to run.

```xml
<context-param>
    <param-name>spring.profiles.active</param-name>
    <param-value>nonMailServer,nonMqServer</param-value>
</context-param>
```

Profiles that are available are as follows.

#### Connecting mail server

|       environments       | specify spring profiles   |
|:------------------------:|---------------------------|
| using embedded GreenMail | `nonMailServer` (default) |
| using actual Mail Server | `mailServer` [^3]         |

[^3]: Please correct the set values of the spring-functionaltest-infra.properties files according to the destination mail server.

#### Connecting message queue

|        environments        | specify spring profiles   |
|:--------------------------:|---------------------------|
| using embedded ActiveMQ    | `nonMqServer` (default)   |
| using actual Message Queue | `mqServer` [^4]           |

[^4]: Please correct the set values of the spring-functionaltest-infra.properties files according to the destination message queue.

### How to switch specific test cases to run for various environments

Using the system property(`-D`) with `@IfProfileValue`, enable to switch specific test cases to run in accordance with the environment that you want to run.

```console
$ cd {your repository directory}
$ mvn -U test -pl spring-functionaltest-selenium -Dtest.environment=mailServier
```

Properties that are available are as follows.

|        properties         | description                                                                                                                                   |
|:-------------------------:|-----------------------------------------------------------------------------------------------------------------------------------------------|
|    test.environment       | Whether to run tests must use actual mail server.<br>Set **`mailServer`** when `spring.profiles.active` of application contains `mailServer`. |
|    jms.test.environment   | Whether to run tests must use actual mq server.<br>Set **`mqServer`** when `spring.profiles.active` of application contains `mqServer`. |
|   test.environment.view   | Whether to run tests depending on a specific HTML view template.<br>Set view template name **`jsp`**(default) or **`thymeleaf`**.             |
