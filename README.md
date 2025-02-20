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
├── spring-functionaltest-web-oauth2-client
├── spring-functionaltest-web-oauth2-resource
├── spsc-hsts
├── spring-functionaltest-initdb
└── spring-functionaltest-selenium
```

If you want to use Thymeleaf for View, switch the web projects in the build process to the following project for Thymeleaf.

```console
spring-functionaltest
├── spring-functionaltest-env
├── spring-functionaltest-model
├── spring-functionaltest-webservice
├── spring-functionaltest-domain
├── spring-functionaltest-web-thymeleaf
├── spring-functionaltest-env-oauth2
├── spring-functionaltest-web-oauth2-client
├── spring-functionaltest-web-oauth2-resource
├── spsc-hsts
├── spring-functionaltest-initdb
└── spring-functionaltest-selenium
```

## How to perform functional test

**Preconditions are as follows:**

* [JDK 8+](https://developers.redhat.com/products/openjdk/download) installed (`JAVA_HOME` defined as environment variable)
* [Maven](https://maven.apache.org/download.cgi) installed (Can run `mvn` command)
* Firefox([for personal](https://www.mozilla.org/en-US/firefox/all/#product-desktop-release) or [ESR](https://www.mozilla.org/en-US/firefox/all/#product-desktop-esr)) installed (ESR is used on our CI environment)


### [Step 1] Create database of PostgreSQL (Optional)
If [PostgreSQL](http://www.postgresql.org/) is used as database , you need to create database of PostgreSQL into local machine. (PostgreSQL can be downloaded via [here site](http://www.postgresql.org/download/)).

> **Note:**
>
> If [H2](http://www.h2database.com/html/main.html) is used as database, you can skip this step.

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
$ git clone -b 1.8.x https://github.com/Macchinetta/spring-functionaltest.git
```

### [Step 3] Build artifacts
Build artifacts using maven commands as follows.

#### Case of using embedded H2 as database

#### Case of using JDK8

Specify the target project and run clean install.

```console
$ cd {your repository directory}
$ git checkout {target branch}

$ mvn -U clean install -am -pl spring-functionaltest-web

# Case of using Thymeleaf
$ mvn -U clean install -am -pl spring-functionaltest-web-thymeleaf

# Case of using OAuth2
$ mvn -U clean install -am -pl spring-functionaltest-web-oauth2-client
$ mvn -U clean install -am -pl spring-functionaltest-web-oauth2-resource
```

#### Case of using JDK11/JDK17/JDK21

Specify the target project and run clean install.
Since the jdk profile takes precedence, the profile must be specified explicitly.

```console
$ cd {your repository directory}
$ git checkout {target branch}

$ mvn -U clean install -am -pl spring-functionaltest-web -P include-context,compile-env,compile-activemq

# Case of using Thymeleaf
$ mvn -U clean install -am -pl spring-functionaltest-web-thymeleaf -P include-context,compile-env,compile-activemq

# Case of using OAuth2
$ mvn -U clean install -am -pl spring-functionaltest-web-oauth2-client -P include-context,compile-env
$ mvn -U clean install -am -pl spring-functionaltest-web-oauth2-resource -P include-context,compile-env
```

#### Case of using PostgreSQL as database

#### Case of using JDK8

Specify the target project and run clean install.

```console
$ cd {your repository directory}
$ git checkout {target branch}

$ mvn -U clean install -am -pl spring-functionaltest-web -P tomcat9-postgresql,include-context

# Case of using Thymeleaf
$ mvn -U clean install -am -pl spring-functionaltest-web-thymeleaf -P tomcat9-postgresql,include-context

# Case of using OAuth2
$ mvn -U clean install -am -pl spring-functionaltest-web-oauth2-client -P tomcat9-postgresql,include-context
$ mvn -U clean install -am -pl spring-functionaltest-web-oauth2-resource -P tomcat9-postgresql,include-context
```

#### Case of using JDK11/JDK17/JDK21

Specify the target project and run clean install.

```console
$ cd {your repository directory}
$ git checkout {target branch}

$ mvn -U clean install -am -pl spring-functionaltest-web -P tomcat9-postgresql,include-context,compile-env,compile-activemq

# Case of using Thymeleaf
$ mvn -U clean install -am -pl spring-functionaltest-web-thymeleaf -P tomcat9-postgresql,include-context,compile-env,compile-activemq

# Case of using OAuth2
$ mvn -U clean install -am -pl spring-functionaltest-web-oauth2-client -P tomcat9-postgresql,include-context,compile-env
$ mvn -U clean install -am -pl spring-functionaltest-web-oauth2-resource -P tomcat9-postgresql,include-context,compile-env
```

> **Note:**
>
> If you do not use default user(`postgres`) or password(`P0stgres`), you should modify settings in `spring-functionaltest-env/configs/local/ContainerConfigXML/context.xml`.

### [Step 4] Initialize database (Optional)
If PostgreSQL is used as database, initialize database before running functional test.

```console
$ mvn -U sql:execute -pl spring-functionaltest-initdb -P local-postgres
$ mvn -U sql:execute -pl spring-functionaltest-initdb -P local-postgres-open
$ mvn -U sql:execute -pl spring-functionaltest-initdb -P local-postgres-close
```

> **Note:**
>
> If you do not use default user(`postgres`) or password(`P0stgres`), you should specify `-Ddb.username={your user}` or `-Ddb.password={your password}` or both.

### [Step 5] Start up Tomcat9 and deploy war file
Start up Tomcat9 and deploy war file using [CARGO maven plugin](https://codehaus-cargo.github.io/cargo/Maven+3+Plugin.html).

```console
$ cd {your repository directory}

$ mvn -U cargo:run -pl spring-functionaltest-web

# Case of using Thymeleaf
$ mvn -U cargo:run -pl spring-functionaltest-web-thymeleaf
```

The following options can be set at cargo runtime.

| Option | Overview | Value that can be set | Default value | Setting example
| ---- | ---- | ---- | ---- | ---- |
| cargo.maven.containerUrl | container URL for Cargo | [Tomcat](https://archive.apache.org/dist/tomcat/) | URL corresponding to the version of Tomcat set in parent of | -Dcargo.maven.containerUrl=https://archive.apache.org/dist/tomcat/tomcat-9/v9.0.97/bin/apache-tomcat-9.0.97.zip |

> **Note:**
>
> For both JSP and Thymeleaf, the same war file name will be used.  
> You can access application at `http://localhost:8080/spring-functionaltest-web/`.

> **Note:**
>
> Shutdown trigger is "Ctrl + C" on console.

### [Step 6] Deploy OAuth war files
Deploy OAuth war files to already running container.

```console
$ cd {your repository directory}
$ mvn -U cargo:deploy -pl spring-functionaltest-web -Dcargo.deployable.artifactId=spring-functionaltest-web-oauth2-client -Dcargo.deployable.warName=spring-functionaltest-web-oauth2-client
$ mvn -U cargo:deploy -pl spring-functionaltest-web -Dcargo.deployable.artifactId=spring-functionaltest-web-oauth2-resource -Dcargo.deployable.warName=spring-functionaltest-web-oauth2-resource
```

### [Step 7] Run functional tests
Run tests using Selenium(`WebDriver`) on JUnit.

```console
$ cd {your repository directory}

# Case of JSP testing
$ mvn -U test -pl spring-functionaltest-selenium

# Case of Thymeleaf testing
$ mvn -U test -pl spring-functionaltest-selenium -Dtest.environment.view=thymeleaf
```

The following options can be set at selenium runtime.

Please use them according to the situation.

If you do not specify any options, firefox (the latest driver) & headless mode will be used.

| Option | Overview | Value that can be set | Default value | Setting example
| ---- | ---- | ---- | ---- | ---- |
| wdm.cachePath | Directory where web driver is downloaded | Any directory | /.cache/selenium | -Dwdm.cachePath=/opt/geckodriver |
| wdm.geckoDriverVersion | Version of geckoDriver | [Version](https://github.com/mozilla/geckodriver/releases) | Latest version | -Dwdm.geckoDriverVersion=0.32.0 |
| selenium.headless | Headless | true, false | true | -Dselenium.headless=false |

## Appendix

### How to use latest or any branch snapshot of Common Library

If you want to use latest or any branch snapshot of Common Library, install latest or any branch snapshot before building and testing.

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
$ mvn -U package -am -pl spring-functionaltest-web -P tomcat9-postgresql,provided-env
```

Profiles that are available are as follows.

#### The Combination of application server and a database

|        environments       | specify maven profiles                            |
|:-------------------------:|---------------------------------------------------|
|        Tomcat9 + H2       | (not specify)                                     |
|    Tomcat9 + Postgresql   | `tomcat9-postgresql,include-context,compile-env`  |
| Tomcat9 + Postgresql [^1] | `tomcat9-postgresql,provided-env`                 |

[^1]: Please deploy spring-functionaltest-web.war, spring-functionaltest-env.jar, and context.xml.

### How to switch bean definition for various environments

Using the spring profile, you can switch the bean definition in accordance with the environment that you want to run.

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

Using the system property(`-D`) with `@IfProfileValue`, you can switch specific test cases to run in accordance with the environment that you want to run.

```console
$ cd {your repository directory}
$ mvn -U test -pl spring-functionaltest-selenium -Dtest.environment=mailServier
```

Properties that are available are as follows.

|        properties         | description                                                                                                                                   |
|:-------------------------:|-----------------------------------------------------------------------------------------------------------------------------------------------|
|    test.environment       | Whether to run tests using actual mail server.<br>Set **`mailServer`** when `spring.profiles.active` of application contains `mailServer`. |
|    jms.test.environment   | Whether to run tests using actual mq server.<br>Set **`mqServer`** when `spring.profiles.active` of application contains `mqServer`. |
|   test.environment.view   | Whether to run tests depending on a specific HTML view template.<br>Set view template name **`jsp`**(default) or **`thymeleaf`**.             |
