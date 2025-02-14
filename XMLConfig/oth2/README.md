# Authorization Server

## infomation

* keycloak  
https://www.keycloak.org/

* version  
24.0.5

* download  
  * [zip](https://github.com/keycloak/keycloak/releases/download/24.0.5/keycloak-24.0.5.zip)
  * [tar.gz](https://github.com/keycloak/keycloak/releases/download/24.0.5/keycloak-24.0.5.tar.gz)

## spring-functionaltest

The port number is set as ``8900``.  
Realm should import ``spring-functionaltest.json``.  
A ``demo``/``demo`` is created as user.

* command  
``bin/kc.[bat|sh] start-dev --http-port 8900``

* Realm  
``spring-functionaltest.json``

* user  
``demo``/``demo``

To access the administration screen, use  
http://localhost:8900/  
and create an admi user as appropriate.  