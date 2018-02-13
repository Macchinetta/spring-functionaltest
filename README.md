# Spring Functional Test
This project provides functional tests of [Macchinetta Server Framework (1.x) Development Guideline](http://Macchinetta.github.io/server-guideline/current/ja).

## How to perform functional test

**Preconditions are as follow:**

* [JDK 8](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html) or [JDK 7](http://www.oracle.com/technetwork/java/javase/downloads/jdk7-downloads-1880260.html) installed (`JAVA_HOME` defined as environment variable)
* "JCE Unlimited Strength Jurisdiction Policy Files" (for [JDK 8](http://www.oracle.com/technetwork/java/javase/downloads/jce8-download-2133166.html) or [JDK 7](http://www.oracle.com/technetwork/java/embedded/embedded-se/downloads/jce-7-download-432124.html)) installed
* [Maven](https://maven.apache.org/download.cgi) installed (Can run `mvn` command)
* Firefox([for personal](https://www.mozilla.org/en-US/firefox/all/) or [ESR](https://www.mozilla.org/en-US/firefox/organizations/all/)) installed (ESR is used on our CI environment)

### [Step 1] Create database of PostgreSQL (Optional)
If [PostgreSQL](http://www.postgresql.org/) use as database , you need to create database of PostgreSQL into local machine. (PostgreSQL can download via [here site](http://www.postgresql.org/download/)).

> **Note:**
>
> If [H2](http://www.h2database.com/) use as database, you can skip this step.

#### Download & install
By default, database owner is `postgres` user, and password of `postgres` user is `'P0stgres'`.

#### Create database
```console
$ createdb -U postgres spring-functionaltest --locale=C --encoding=UTF8 --template=template0
$ createdb -U postgres spring-functionaltest-open --locale=C --encoding=UTF8 --template=template0
$ createdb -U postgres spring-functionaltest-close --locale=C --encoding=UTF8 --template=template0
```
### [Step 2] Clone a repository
Clone the `Macchinetta/spring-functionaltest` repository into local machine.

```console
$ git clone https://github.com/Macchinetta/spring-functionaltest.git
```

### [Step 3] Place dependent libraries
Place `iTextAsian-1.0.jar` on `spring-functionaltest-env/3rdparty`.
You have to get this jar file in some way. 

```console
$ mv iTextAsian-1.0.jar spring-functionaltest-env/3rdparty/com/lowagie/iTextAsian/1.0
```

If you'd like to use another version, it's necessary to change some configurations.

### [Step 4] Build artifacts
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
> When using JDK 7, build as follows.  
> you must compile with the target version 1.7 in accordance with the runtime environment JVM and exclude the source code that depends on JDK 1.8 .  
> You can set the target version using `java-version` property, and in spring-functionaltest-web you can exclude using `build-for-jdk7` Maven Profile in addition to the default profile.
>```console
> $ mvn clean install -am -pl spring-functionaltest-web -Djava-version=1.7 -P local,build-for-jdk7
> $ mvn clean install -am -pl spring-functionaltest-web-oauth2-db,spring-functionaltest-web-oauth2-memory,spring-functionaltest-web-oauth2-remote -Djava-version=1.7
>```

#### Case that use PostgreSQL as database

```console
$ cd {your repository directory}
$ git checkout {target branch}
$ mvn clean install -am -pl spring-functionaltest-web -P tomcat8-postgresql,warpack-env,warpack-jstl,warpack-cxf,warpack-transaction,travis
$ mvn clean install -am -pl spring-functionaltest-web-oauth2-db,spring-functionaltest-web-oauth2-memory,spring-functionaltest-web-oauth2-remote -P tomcat8-postgresql,warpack-env,warpack-jstl,travis
```

> **Note:**
>
> If you not use default user(`postgres`) or password(`P0stgres`), you should modify settings in `terasoluna-gfw-functionaltest-env/configs/travis/ContainerConfigXML/context.xml`.

### [Step 5] Initialize database (Optional)
If PostgreSQL use as database, initialize database before run functional test.

```console
$ mvn -U sql:execute -pl spring-functionaltest-initdb -P local-postgres
$ mvn -U sql:execute -pl spring-functionaltest-initdb -P local-postgres-open
$ mvn -U sql:execute -pl spring-functionaltest-initdb -P local-postgres-close
```

> **Note:**
>
> If you not use default user(`postgres`) or password(`P0stgres`), you should specify `-Ddb.username={your user}` or `-Ddb.password={your password}` or both.

### [Step 6] Startup Tomcat8 and deploy war file
Startup Tomcat8 and deploy war file using [CARGO maven plugin](https://codehaus-cargo.github.io/cargo/Maven2+plugin.html).

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
> Shutdown trigger is "Ctrl + C" on console.

### [Step 7] Deploy OAuth war files
Deploy OAuth war files to already running container.

```console
$ cd {your repository directory}
$ mvn -U cargo:deploy -pl spring-functionaltest-web -Dcargo.deployable.artifactId=spring-functionaltest-web-oauth2-db
$ mvn -U cargo:deploy -pl spring-functionaltest-web -Dcargo.deployable.artifactId=spring-functionaltest-web-oauth2-memory
$ mvn -U cargo:deploy -pl spring-functionaltest-web -Dcargo.deployable.artifactId=spring-functionaltest-web-oauth2-remote
```

### [Step 8] Run functional tests
Run tests using Selenium(`WebDriver`) on JUnit.

```console
$ cd {your repository directory}
$ mvn -U test -pl spring-functionaltest-selenium
```

> **Note:**
>
> If functional test is failed, try again using latest Selenium(specify with `-Dselenium.version={latest version}`).

> **Note:**
>
> When using JDK 7,  please build as follows.
>```console
> $ mvn -U test -pl spring-functionaltest-selenium -Djava-version=1.7 -P build-for-jdk7
>```

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
|      Java7      | `build-for-jdk7`      |

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
