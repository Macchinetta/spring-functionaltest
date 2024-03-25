# Spring Functional Test

This project provides functional tests of [Macchinetta Server Framework (1.x) Development Guideline](https://macchinetta.github.io/server.html).

## Project structure

Two types of bean definition methods are available: JavaConfig-based and XMLConfig-based.  
The project structure under the JavaConfig and XMLConfig folders is the same for both.

**Project structure**

```
spring-functionaltest  
|- spring-functionaltest-initdb
|- JavaConfig 
|    |- spring-functionaltest-env
|    |- spring-functionaltest-domain
|    |- spring-functionaltest-web
|    |- spring-functionaltest-env-functions
|    |- spring-functionaltest-selenium
|    |- spring-functionaltest-web-xxxx
|    |- (omitted)
∟ XMLConfig
     |- (omitted)
```

**Description of each project**

| project name  | summary     |
|:--------------|:------------|
| spring-functionaltest-env                 | ``spring-functionaltest-web`` dedicated environment definition <br> Contains H2 table definitions for DB Appender and starts H2DB at web startup |
| spring-functionaltest-domain              | ``spring-functionaltest-web`` dedicated domain project |
| spring-functionaltest-web                 | Provides only H2 startup and index to each function |
| spring-functionaltest-env-functions       | Environment definition for test projects |
| spring-functionaltest-selenium            | selenium project |
| spring-functionaltest-web-xxxx            | Project to test each function |

## How to perform functional test

**Preconditions are as follow:**

* [JDK 17](https://developers.redhat.com/products/openjdk/download) installed (``JAVA_HOME`` defined as environment variable)
* [Maven](https://maven.apache.org/download.cgi) installed (Can run ``mvn`` command)
* Firefox([for personal](https://www.mozilla.org/en-US/firefox/all/#product-desktop-release) or [ESR](https://www.mozilla.org/en-US/firefox/all/#product-desktop-esr)) installed (ESR is used on our CI environment)

### [Step 1] Clone a repository

Clone the ``Macchinetta/spring-functionaltest`` repository into local machine.

```console
$ git clone https://{remote host url}/Macchinetta/spring-functionaltest.git
```

### [Step 2] Create database of PostgreSQL (Optional)
If [PostgreSQL](http://www.postgresql.org/) use as database , you need to create database of PostgreSQL into local machine. (PostgreSQL can download via [here site](http://www.postgresql.org/download/)).

> **Note:**  
> If [H2](http://www.h2database.com/html/main.html) use as database, you can skip this step.

#### Download & install

By default, database owner is ``postgres`` user, and password of ``postgres`` user is `'P0stgres'`.

#### Create database
```console
$ createdb -U postgres --locale=C --encoding=UTF8 --template=template0 spring-functionaltest
$ createdb -U postgres --locale=C --encoding=UTF8 --template=template0 spring-functionaltest-open
$ createdb -U postgres --locale=C --encoding=UTF8 --template=template0 spring-functionaltest-close
```

#### Initialize database

If PostgreSQL use as database, initialize database before run functional test.

```console
$ cd spring-functionaltest-initdb
$ mvn -U sql:execute -P local-postgres
$ mvn -U sql:execute -P local-postgres-open
$ mvn -U sql:execute -P local-postgres-close
```

> **Note:**  
> If you not use default user(`postgres`) or password(`P0stgres`), you should specify `-Ddb.username={your user}` or `-Ddb.password={your password}` or both.

### [Step 3] Build artifacts

After navigating to the Configration to be used, build the artifact using the maven command.
* Java Config : ``spring-functionaltest/JavaConfig``
* XML Config : ``spring-functionaltest/XMLConfig``

#### Case that use embedded H2 as database

If you want to use H2 for the database, execute the following command to build it.

```console
$ cd {your repository directory}
$ git checkout {target branch}
$ mvn -U clean install -am -pl spring-functionaltest-web
# Build the project to be tested
$ mvn -U clean install -am -pl spring-functionaltest-web-xxxx

# Example: In the case of ajax
$ mvn -U clean install -am -pl spring-functionaltest-web-ajax
```

To build the entire project, execute the following command.
```console
$ cd {your repository directory}
$ git checkout {target branch}
$ mvn -U clean install
```

#### Case that use PostgreSQL as database

If you want to use PostgreSQL for the database, execute the following command to build it.

```console
$ cd {your repository directory}
$ git checkout {target branch}
$ mvn -U clean install -am -pl spring-functionaltest-web -P tomcat10-postgresql,travis
# Build the project to be tested
$ mvn -U clean install -am -pl spring-functionaltest-web-xxxx -P tomcat10-postgresql,travis

# Example: In the case of ajax
$ mvn -U clean install -am -pl spring-functionaltest-web-ajax -P tomcat10-postgresql,travis
```

To build the entire project, execute the following command.
```console
$ cd {your repository directory}
$ git checkout {target branch}
$ mvn -U clean install -P tomcat10-postgresql,travis
```

> **Note:**  
> If you not use default user(``postgres``) or password(``P0stgres``), you should modify settings in ``spring-functionaltest-env/configs/tomcat10-postgresql/ContainerConfigXML/context.xml``.


### [Step 4] Startup Tomcat10 and deploy war file
Startup Tomcat10 and deploy war file using [CARGO maven plugin](https://codehaus-cargo.github.io/cargo/Maven+3+Plugin.html).

If you do not use the cargo command, place the war file into a locally installed Tomcat and start it.

> **Warning:**  
> When ``spring-functionaltest-web`` is started, we create the H2DB tables to be used by the Logback DB Appender. We also create tables to be used in each project if built with H2DB.  
> Note that ``spring-functionaltest-web`` must therefore be invoked first.  

#### Case that use embedded H2 as database

```console
$ cd {your repository directory}
$ mvn -U cargo:run -pl spring-functionaltest-web
```

Shutdown trigger is "Ctrl + C" on console.

#### Case that use PostgreSQL as database (use Tomcat JNDI Resource)

If you use PostgreSQL as your database, deploy it by executing the following command.

```console
$ cd {your repository directory}
$ mvn -U cargo:run -pl spring-functionaltest-web -P travis
```

Shutdown trigger is "Ctrl + C" on console.

> **Note:**  
> you can access application at `http://localhost:8080/spring-functionaltest-web/`.

### [Step 5] Deploy functions war files

To deploy each functional app to the already started cargo.

```console
$ cd {your repository directory}
# Add the project to be tested to cargo
$ mvn -U cargo:deploy -pl spring-functionaltest-web -Dcargo.deployable.artifactId=spring-functionaltest-web-xxxx -Dcargo.deployable.warName=spring-functionaltest-web-xxxx

# Example: In the case of ajax
$ mvn -U cargo:deploy -pl spring-functionaltest-web -Dcargo.deployable.artifactId=spring-functionaltest-web-ajax -Dcargo.deployable.warName=spring-functionaltest-web-ajax
```

#### Case that use PostgreSQL as database (use Tomcat JNDI Resource)

If you want to use PostgreSQL for the database, run the following command to add it to the already started cargo.

```console
$ cd {your repository directory}
# Add the project to be tested to cargo
$ mvn -U cargo:deploy -pl spring-functionaltest-web -Dcargo.deployable.artifactId=spring-functionaltest-web-xxxx -Dcargo.deployable.warName=spring-functionaltest-web-xxxx -P travis

# Example: In the case of ajax
$ mvn -U cargo:deploy -pl spring-functionaltest-web -Dcargo.deployable.artifactId=spring-functionaltest-web-ajax -Dcargo.deployable.warName=spring-functionaltest-web-ajax -P travis
```

### [Step 6] Run functional tests

After navigating to the ``spring-functionaltest-selenium`` project, start Selenium (``WebDriver``) and run JUnit.

```console
$ cd {your repository directory}/spring-functionaltest-selenium
$ mvn -U test -Dwdm.cachePath=/opt/geckodriver -Dwdm.geckoDriverVersion=0.32.0
```

If you wish to test a specific project, use ``-Dtest=`` to narrow down the target project to be tested.  
You can also specify the project name to narrow down the target.  

```console
# Example: In the case of ajax
$ mvn -U test -Dwdm.cachePath=/opt/geckodriver -Dwdm.geckoDriverVersion=0.32.0 -Dtest=**.ajax.**
```

> **Note:**  
> In the above example, options are set for the startup arguments.
>
> To specify the download directory, specify "``-Dwdm.cachePath``" in the startup argument.  
> To specify the version of geckoDriver, specify "``-Dwdm.geckoDriverVersion``" in the startup argument.  
> By dafault latest version [geckodriver](https://github.com/mozilla/geckodriver/releases) will download automatically to ``~/.cache/selenium`` by [webdrivermanager](https://github.com/bonigarcia/webdrivermanager).  
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
$ mvn -U package -am -pl spring-functionaltest-web -P tomcat10-postgresql,travis
```

Profiles that are available are as follows.

#### The Combination of application server and a database

|        environments        |  specify maven profiles         |
|----------------------------|---------------------------------|
| Tomcat10 + H2              | (not specify)                   |
| Tomcat10 + Postgresql      | ``tomcat10-postgresql,travis``  |
| Tomcat10 + Postgresql [^1] | ``tomcat10-postgresql,warpack`` |

[^1]: Please deploy ``spring-functionaltest-web.war``, ``spring-functionaltest-env.jar``, and ``context.xml``.

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

|       environments       | specify spring profiles     |
|--------------------------|-----------------------------|
| using embedded GreenMail | ``nonMailServer`` (default) |
| using actual Mail Server | ``mailServer`` [^2]         |

[^2]: The configuration values in the ``spring-functionaltest-infra.properties`` file should be modified to match the server you are connecting to.

#### Connecting message queue

|        environments        | specify spring profiles     |
|----------------------------|-----------------------------|
| using embedded ActiveMQ    | ``nonMqServer`` (default)   |
| using actual Message Queue | ``mqServer`` [^2]           |

### How to switch specific test cases to run for various environments

Using the system property(``-D``) with ``@IfProfileValue``, enable to switch specific test cases to run in accordance with the environment that you want to run.

```console
$ cd {your repository directory}
$ mvn -U test -pl spring-functionaltest-selenium -Dtest.environment=mailServier
```

Properties that are available are as follows.

|        properties         | description             |
|---------------------------|-------------------------|
|    test.environment       | Whether to run tests must use actual mail server.<br>Set **``mailServer``** when ``spring.profiles.active`` of application contains ``mailServer``. |
|    jms.test.environment   | Whether to run tests must use actual mq server.<br>Set **``mqServer``** when ``spring.profiles.active`` of application contains ``mqServer``. |
