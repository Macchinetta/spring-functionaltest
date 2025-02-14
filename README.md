# Spring Functional Test

This project provides functional tests of [Macchinetta Server Framework (1.x) Development Guideline](https://macchinetta.github.io/server.html).

## Project structure

Two types of bean definition methods are available: JavaConfig-based and XMLConfig-based.  
The project structure under the JavaConfig and XMLConfig folders is the same for both.

**Project structure**

```
spring-functionaltest  
|- JavaConfig 
|    |- spring-functionaltest-env
|    |- spring-functionaltest-initdb
|    |- spring-functionaltest-selenium
|    |- xxxx
|    |- (omitted)
∟ XMLConfig
     |- (omitted)
```

**Description of each project**

| project name  | summary     |
|:--------------|:------------|
| spring-functionaltest-env      | Environment definition for test projects |
| spring-functionaltest-selenium | selenium project |
| xxxx(e.g. ajax) | Project to test each function |

## How to perform functional test

**Preconditions are as follows:**

* [JDK 17](https://developers.redhat.com/products/openjdk/download) installed (``JAVA_HOME`` defined as environment variable)
* [Maven](https://maven.apache.org/download.cgi) installed (Can run ``mvn`` command)
* Firefox([for personal](https://www.mozilla.org/en-US/firefox/all/#product-desktop-release) or [ESR](https://www.mozilla.org/en-US/firefox/all/#product-desktop-esr)) installed (ESR is used on our CI environment)

### [Step 1] Clone a repository

Clone the ``Macchinetta/spring-functionaltest`` repository into local machine.

```console
$ git clone https://github.com/Macchinetta/spring-functionaltest.git
```

### [Step 2] Create database of PostgreSQL (Optional)
If [PostgreSQL](http://www.postgresql.org/) is used as database , you need to create database of PostgreSQL into local machine. (PostgreSQL can be downloaded via [here site](http://www.postgresql.org/download/)).

> **Note:**  
> If [H2](http://www.h2database.com/html/main.html) is used as database, you can skip this step.

#### Download & install

By default, database owner is ``postgres`` user, and password of ``postgres`` user is `'P0stgres'`.

#### Create database
```console
$ createdb -U postgres --locale=C --encoding=UTF8 --template=template0 spring-functionaltest
$ createdb -U postgres --locale=C --encoding=UTF8 --template=template0 spring-functionaltest-open
$ createdb -U postgres --locale=C --encoding=UTF8 --template=template0 spring-functionaltest-close
```

#### Initialize database

If PostgreSQL is used as database, initialize database before running functional test.

```console
$ cd spring-functionaltest-initdb
$ mvn -U sql:execute -P local-postgres
$ mvn -U sql:execute -P local-postgres-open
$ mvn -U sql:execute -P local-postgres-close
```

> **Note:**  
> If you do not use default user(`postgres`) or password(`P0stgres`), you should specify `-Ddb.username={your user}` or `-Ddb.password={your password}` or both.

### [Step 3] Build artifacts

After navigating to the Configration to be used, build the artifact using the maven command.
* Java Config : ``spring-functionaltest/JavaConfig``
* XML Config : ``spring-functionaltest/XMLConfig``

#### Case of using embedded H2 as database

If you want to use H2 for the database, execute the following command to build it.

```console
# Build the project to be tested
$ mvn -U clean install -am -pl xxxx

# Example: In the case of ajax
$ mvn -U clean install -am -pl ajax
```

To build the entire project, execute the following command.
```console
$ mvn -U clean install
```

#### Case of using PostgreSQL as database

If you want to use PostgreSQL for the database, execute the following command to build it.

```console
# Build the project to be tested
$ mvn -U clean install -am -pl xxxx -P tomcat10-postgresql,include-context,compile-env

# Example: In the case of ajax
$ mvn -U clean install -am -pl ajax -P tomcat10-postgresql,include-context,compile-env
```

To build the entire project, execute the following command.
```console
$ mvn -U clean install -P tomcat10-postgresql,include-context,compile-env
```

> **Note:**  
> If you do not use default user(``postgres``) or password(``P0stgres``), you should modify settings in ``spring-functionaltest-env/configs/tomcat10-postgresql/ContainerConfigXML/context.xml``.


### [Step 4] Start up Tomcat10 and deploy war file

After navigating to the Configration to be used, start up Tomcat10 and deploy war file using the maven command.
* Java Config : ``spring-functionaltest/JavaConfig``
* XML Config : ``spring-functionaltest/XMLConfig``

Start up Tomcat10 and deploy war file using [CARGO maven plugin](https://codehaus-cargo.github.io/cargo/Maven+3+Plugin.html).

If you do not use the cargo command, place the war file into a locally installed Tomcat and start it.

```console
$ mvn -U cargo:run -pl xxxx

# Example: In the case of ajax
$ mvn -U cargo:run -pl ajax
```

The following options can be set at cargo runtime.

| Option | Overview | Value that can be set | Default value | Setting example
| ---- | ---- | ---- | ---- | ---- |
| cargo.maven.containerUrl | container URL for Cargo | [Tomcat](https://archive.apache.org/dist/tomcat/) | URL corresponding to the version of Tomcat set in parent of | -Dcargo.maven.containerUrl=[https://archive.apache.org/dist/tomcat/tomcat-10/v10.1.33/bin/apache-tomcat-10.1.33.zip](https://archive.apache.org/dist/tomcat/tomcat-10/v10.1.33/bin/apache-tomcat-10.1.33.zip) |

Shutdown trigger is "Ctrl + C" on console.

> **Note:**  
> You can access application at `http://localhost:8080/xxxx/`.

### [Step 5] Deploy functions war files

After navigating to the Configration to be used, deploy war file using the maven command.
* Java Config : ``spring-functionaltest/JavaConfig``
* XML Config : ``spring-functionaltest/XMLConfig``

Deploy each functional app to the already started cargo.

```console
# Add the project to be tested to cargo
$ mvn -U cargo:deploy -pl xxxx -Dcargo.deployable.artifactId=yyyy -Dcargo.deployable.warName=yyyy

# Example: If ajax is running and you want to add aply
$ mvn -U cargo:deploy -pl ajax -Dcargo.deployable.artifactId=aply -Dcargo.deployable.warName=aply
```

### [Step 6] Run functional tests

After navigating to the Configration to be used, run functional tests using the maven command.
* Java Config : ``spring-functionaltest/JavaConfig``
* XML Config : ``spring-functionaltest/XMLConfig``

After navigating to the ``spring-functionaltest-selenium`` project, start Selenium (``WebDriver``) and run JUnit.

```console
$ cd spring-functionaltest-selenium
$ mvn -U test
```

If you wish to test a specific project, use ``-Dtest=`` to narrow down the target project to be tested.  
You can also specify the project name to narrow down the target.  

```console
# Example: In the case of ajax
$ mvn -U test -Dtest=*/ajax/*
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

After navigating to the Configration to be used, build war using the maven command.
* Java Config : ``spring-functionaltest/JavaConfig``
* XML Config : ``spring-functionaltest/XMLConfig``

Using the maven profile , build the war in accordance with the environment that you want to deploy.

```console
$ mvn -U package -am -pl xxxx -P tomcat10-postgresql,provided-env
```

Profiles that are available are as follows.

#### The Combination of application server and a database

|        environments        |  specify maven profiles         |
|----------------------------|---------------------------------|
| Tomcat10 + H2              | (not specify)                   |
| Tomcat10 + Postgresql      | ``tomcat10-postgresql,include-context,compile-env``  |
| Tomcat10 + Postgresql [^1] | ``tomcat10-postgresql,provided-env`` |

[^1]: Please deploy ``xxxx.war``, ``spring-functionaltest-env.jar``, and ``context.xml``.

### How to switch bean definition for various environments

Using the spring profile , you can switch the bean definition in accordance with the environment that you want to run.

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

After navigating to the Configration to be used, switch specific test cases to run for various environments using the maven command.
* Java Config : ``spring-functionaltest/JavaConfig``
* XML Config : ``spring-functionaltest/XMLConfig``

Using the system property(``-D``) with ``@IfProfileValue``, you can switch specific test cases to run in accordance with the environment that you want to run.

```console
$ cd spring-functionaltest-selenium
$ mvn -U test -pl spring-functionaltest-selenium -Dtest.environment=mailServier
```

Properties that are available are as follows.

|        properties         | description             |
|---------------------------|-------------------------|
|    test.environment       | Whether to run tests using actual mail server.<br>Set **``mailServer``** when ``spring.profiles.active`` of application contains ``mailServer``. |
|    jms.test.environment   | Whether to run tests using actual mq server.<br>Set **``mqServer``** when ``spring.profiles.active`` of application contains ``mqServer``. |
