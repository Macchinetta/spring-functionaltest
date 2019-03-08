# Spring Functional Test
This project provides functional tests of Macchinetta Server Framework (1.x) Development Guideline.

* [JSP](https://macchinetta.github.io/server-guideline/list/)
* [Thymeleaf](https://macchinetta.github.io/server-guideline-thymeleaf/list/)

## Test case design

Please refer to [docs](./docs/README.md).

## How to perform functional test

**Preconditions are as follow:**

* [JDK 8](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html) installed (`JAVA_HOME` defined as environment variable)
* "JCE Unlimited Strength Jurisdiction Policy Files" (for [JDK 8](http://www.oracle.com/technetwork/java/javase/downloads/jce8-download-2133166.html)) installed
* [Maven](https://maven.apache.org/download.cgi) installed (Can run `mvn` command)
* Firefox([for personal](https://www.mozilla.org/en-US/firefox/all/) or [ESR](https://www.mozilla.org/en-US/firefox/organizations/all/)) installed (ESR is used on our CI environment)
* [geckodriver](https://github.com/mozilla/geckodriver/releases) (`v0.14.0` recommended) placed in application execution environment and add to `PATH`.

### [Step 1] Create database of PostgreSQL (Optional)
If [PostgreSQL](http://www.postgresql.org/) use as database , you need to create database of PostgreSQL into local machine. (PostgreSQL can download via [here site](http://www.postgresql.org/download/)).

> **Note:**
>
> If [H2](http://www.h2database.com/) use as database, you can skip this step.

#### Download & install
By default, database owner is `postgres` user, and password of `postgres` user is `'P0stgres'`.

#### Create database
```console
$ createdb -U postgres --locale=C --encoding=UTF8 --template=template0 spring-functionaltest
$ createdb -U postgres --locale=C --encoding=UTF8 --template=template0 spring-functionaltest-open
$ createdb -U postgres --locale=C --encoding=UTF8 --template=template0 spring-functionaltest-close
```
### [Step 2] Clone a repository
Clone the `terasolunaorg/spring-functionaltest` repository into local machine.

```console
$ git clone https://{remote host url}/terasolunaorg/spring-functionaltest.git
```

### [Step 3] Build artifacts
Build artifacts using maven commands as follows.

#### Case that use embedded H2 as database

```console
$ cd {your repository directory}
$ git checkout {target branch}
$ mvn clean install -am -pl spring-functionaltest-web
$ mvn clean install -am -pl spring-functionaltest-web-oauth2-db,spring-functionaltest-web-oauth2-memory,spring-functionaltest-web-oauth2-remote
```

> **Note:**
>
> Case that use Thymeleaf as view
>```console
> $ cd {your repository directory}
> $ git checkout {target branch}
> $ mvn clean install -am -pl spring-functionaltest-web-thymeleaf
> $ mvn clean install -am -pl spring-functionaltest-web-oauth2-db-thymeleaf,spring-functionaltest-web-oauth2-memory-thymeleaf,spring-functionaltest-web-oauth2-remote-thymeleaf
>```

#### Case that use PostgreSQL as database

```console
$ cd {your repository directory}
$ git checkout {target branch}
$ mvn clean install -am -pl spring-functionaltest-web -P tomcat9-postgresql,warpack-env,warpack-jstl,warpack-cxf,warpack-transaction,travis
$ mvn clean install -am -pl spring-functionaltest-web-oauth2-db,spring-functionaltest-web-oauth2-memory,spring-functionaltest-web-oauth2-remote -P tomcat9-postgresql,warpack-env,warpack-jstl,travis
```

> **Note:**
>
> If you not use default user(`postgres`) or password(`P0stgres`), you should modify settings in `terasoluna-gfw-functionaltest-env/configs/travis/ContainerConfigXML/context.xml`.

> **Note:**
>
> Case that use Thymeleaf as view
>```console
> $ cd {your repository directory}
> $ git checkout {target branch}
> $ mvn clean install -am -pl spring-functionaltest-web-thymeleaf -P tomcat8-postgresql,warpack-env,warpack-cxf,warpack-transaction,travis
> $ mvn clean install -am -pl spring-functionaltest-web-oauth2-db-thymeleaf,spring-functionaltest-web-oauth2-memory-thymeleaf,spring-functionaltest-web-oauth2-remote-thymeleaf -P tomcat8-postgresql,warpack-env,travis
>```

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
Startup Tomcat9 and deploy war file using [CARGO maven plugin](https://codehaus-cargo.github.io/cargo/Maven2+plugin.html).

#### Case that use embedded H2 as database

```console
$ cd {your repository directory}
$ mvn -U cargo:run -pl spring-functionaltest-web
```

> **Note:**
>
> Case that use Thymeleaf as view
>```console
> $ cd {your repository directory}
> $ mvn -U cargo:run -pl spring-functionaltest-web-thymeleaf
>```

#### Case that use PostgreSQL as database (use Tomcat JNDI Resource)

```console
$ cd {your repository directory}
$ mvn -U cargo:run -pl spring-functionaltest-web -P travis
```

> **Note:**
>
> Case that use Thymeleaf as view
>```console
> $ cd {your repository directory}
> $ mvn -U cargo:run -pl spring-functionaltest-web-thymeleaf -P travis
>```

> **Note:**
>
> Shutdown trigger is "Ctrl + C" on console.

### [Step 6] Deploy OAuth war files
Deploy OAuth war files to already running container.

```console
$ cd {your repository directory}
$ mvn -U cargo:deploy -pl spring-functionaltest-web -Dcargo.deployable.artifactId=spring-functionaltest-web-oauth2-db -Dcargo.deployable.warName=spring-functionaltest-web-oauth2-db
$ mvn -U cargo:deploy -pl spring-functionaltest-web -Dcargo.deployable.artifactId=spring-functionaltest-web-oauth2-memory -Dcargo.deployable.warName=spring-functionaltest-web-oauth2-memory
$ mvn -U cargo:deploy -pl spring-functionaltest-web -Dcargo.deployable.artifactId=spring-functionaltest-web-oauth2-remote  -Dcargo.deployable.warName=spring-functionaltest-web-oauth2-remote
```

> **Note:**
>
> In the case of Thymeleaf, run the following command .
> 
> ```console
> $ cd {your repository directory}
> $ mvn -U cargo:deploy -pl spring-functionaltest-web-thymeleaf -Dcargo.deployable.artifactId=spring-functionaltest-web-oauth2-db-thymeleaf -Dcargo.deployable.warName=spring-functionaltest-web-oauth2-db
> $ mvn -U cargo:deploy -pl spring-functionaltest-web-thymeleaf -Dcargo.deployable.artifactId=spring-functionaltest-web-oauth2-memory-thymeleaf -Dcargo.deployable.warName=spring-functionaltest-web-oauth2-memory
> $ mvn -U cargo:deploy -pl spring-functionaltest-web-thymeleaf -Dcargo.deployable.artifactId=spring-functionaltest-web-oauth2-remote-thymeleaf -Dcargo.deployable.warName=spring-functionaltest-web-oauth2-remote
> ```

### [Step 7] Run functional tests
Run tests using Selenium(`WebDriver`) on JUnit.

```console
$ cd {your repository directory}
$ mvn -U test -pl spring-functionaltest-selenium
```

> **Note:**
>
> Case that use Thymeleaf as view
> ```console
> $ cd {your repository directory}
> $ mvn -U test -pl spring-functionaltest-selenium -Dtest.environment.view=thymeleaf
> ```

> **Note:**
>
> If GeckoDriver is not registered in the path, [webdrivermanager](https://github.com/bonigarcia/webdrivermanager) will download it automatically.  
> If required proxy, please set `selenium.proxyHttpServer`.  
> If required proxy authentication, set   `selenium.proxyUserName`, `selenium.proxyUserPassword`.  
> ```console
> $ mvn -U test -pl spring-functionaltest-selenium -Dselenium.proxyHttpServer={HttpServer} -Dselenium.proxyUserName={UserName} -Dselenium.proxyUserPassword={Password}
> ```

> **Note:**
>
> In the guava`20.0` on the TERASOLUNA Framework stack, can not run Selenium`3.9.1` defined by SpringIO.  
> Therefore, downgraded to Selenium '3.0.1` which can be run.  
> 
> If functional test can not run , the execution environment such as browser version may be the problem.  
> In that case , change `selenium` and `geckodriver` to appropriate version.  
> specify with `-Dselenium.version={selenium version}`, `-Dselenium.geckodriverVersion={geckodriver version}`.
>
> However, by upgrading Selenium version, it is necessary to upgrade Guava version.  
> Therefore, add the version of Guava to spring-functionaltest-selenium/pom.xml as below.
> ```xml
> <dependency>
>   <groupId>com.google.guava</groupId>
>   <artifactId>guava</artifactId>
>   <version>any guava version</version>
>   <scope>test</scope>
> </dependency>
> ```
> Since it has not been tested with Guava version over 20.0, unpredictable behavior may occur.

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
$ mvn -U package -am -pl spring-functionaltest-web -P weblogic-oracle,warpack-env
```

Profiles that are available are as follows.

#### The Combination of application server and a database

|        environments       | specify maven profiles                                                               |
|:-------------------------:|--------------------------------------------------------------------------------------|
|    Tomcat9 + Postgresql   | `tomcat9-postgresql,warpack-env,warpack-jstl,warpack-cxf,warpack-transaction,travis` |
| Tomcat9 + Postgresql [^1] | `tomcat9-postgresql,warpack-jstl,warpack-cxf,warpack-transaction`                    |
|  Tomcat9 + Oracle [^1]    | `tomcat9-oracle,warpack-jstl,warpack-cxf,warpack-transaction`                        |
|   Tomcat8.5 + Postgresql  | `tomcat85-postgresql,warpack-env,warpack-jstl,warpack-cxf,warpack-transaction,travis`|
|Tomcat8.5 + Postgresql [^1]| `tomcat85-postgresql,warpack-jstl,warpack-cxf,warpack-transaction`                   |
|  Tomcat8.5 + Oracle [^1]  | `tomcat85-oracle,warpack-jstl,warpack-cxf,warpack-transaction`                       |
|        Tomcat8 + H2       | (not specify)                                                                        |
|    Tomcat8 + Postgresql   | `tomcat8-postgresql,warpack-env,warpack-jstl,warpack-cxf,warpack-transaction,travis` |
| Tomcat8 + Postgresql [^1] | `tomcat8-postgresql,warpack-jstl,warpack-cxf,warpack-transaction`                    |
|   Tomcat8 + Oracle [^1]   | `tomcat8-oracle,warpack-jstl,warpack-cxf,warpack-transaction`                        |
| Tomcat7 + Postgresql      | `tomcat-postgresql,warpack-env,warpack-jstl,warpack-cxf,warpack-transaction,travis`  |
| Tomcat7 + Postgresql [^1] | `tomcat-postgresql,warpack-jstl,warpack-cxf,warpack-transaction`                     |
| Tomcat7 + Oracle [^1]     | `tomcat-oracle,warpack-jstl,warpack-cxf,warpack-transaction`                         |
|     Weblogic + Oracle     | `weblogic-oracle,warpack-env`                                                        |
|     JBoss7 + Postgresql   | `jboss7-postgresql,warpack-env`                                                      |
|     JBoss + Postgresql    | `jboss-postgresql,warpack-env,warpack-transaction`                                   |
|  Interstage + Postgresql  | `interstage11-postgresql,warpack-env`                                                |
|      WebOTX + Oracle      | `webotx-oracle,warpack-env`                                                          |
|    WebSphere(LP) + DB2    | `webpsherelp-db2,warpack-env,change-wsdl-location`                                   |
|    WebSphere(TR) + DB2    | `webpsheretr-db2,warpack-env,change-wsdl-location`                                   |

[^1]: Please deploy spring-functionaltest-web.war, spring-functionaltest-env.jar, and context.xml.

#### The JVM version of build and runtime environment

|   environments  | specify maven profile |
|:---------------:|-----------------------|
|      Java8      | (not specify)         |

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

|       environments       | specify spring profiles  |
|:------------------------:|--------------------------|
| using embedded GreenMail | nonMailServer (default)  |
| using actual Mail Server | mailServer [^1]          |

[^1]: Please correct the set values of the spring-functionaltest-infra.properties files according to the destination mail server.

#### Connecting message queue

|        environments        | specify spring profiles  |
|:--------------------------:|--------------------------|
| using embedded ActiveMQ    | nonMqServer (default)    |
| using actual Message Queue | mqServer [^1]            |

[^1]: Please correct the set values of the spring-functionaltest-infra.properties files according to the destination message queue.
