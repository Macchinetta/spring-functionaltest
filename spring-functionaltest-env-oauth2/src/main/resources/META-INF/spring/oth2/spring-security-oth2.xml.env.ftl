<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
    xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    xmlns:sec="http://www.springframework.org/schema/security"
    xsi:schemaLocation="http://www.springframework.org/schema/security https://www.springframework.org/schema/security/spring-security.xsd
                        http://www.springframework.org/schema/beans https://www.springframework.org/schema/beans/spring-beans.xsd">

    <sec:client-registrations>
        <!-- READ Only -->
        <sec:client-registration
            registration-id="registration_read" client-id="readClient"
            client-secret="secret" authorization-grant-type="authorization_code"
            redirect-uri="{baseUrl}/auth/authorized" scope="READ"
            provider-id="provider_1" />
        <!-- UPDATE not allowed -->
        <sec:client-registration
            registration-id="registration_wrong" client-id="readClient"
            client-secret="secret" authorization-grant-type="authorization_code"
            redirect-uri="{baseUrl}/auth/authorized" scope="READ,UPDATE"
            provider-id="provider_1" />
        <!-- ALL OK -->
        <sec:client-registration
            registration-id="registration_all" client-id="testClient"
            client-secret="secret" authorization-grant-type="authorization_code"
            redirect-uri="{baseUrl}/auth/authorized"
            scope="READ,UPDATE,CREATE,DELETE" provider-id="provider_1" />
        <!-- READ or DELETE -->
        <sec:client-registration
            registration-id="registration_partial" client-id="testClient"
            client-secret="secret" authorization-grant-type="authorization_code"
            redirect-uri="{baseUrl}/auth/authorized" scope="READ,DELETE"
            provider-id="provider_1" />
        <!-- AccessToken endpoint is wrong -->
        <sec:client-registration
            registration-id="registration_token_wrong" client-id="readClient"
            client-secret="secret" authorization-grant-type="authorization_code"
            redirect-uri="{baseUrl}/auth/authorized" scope="READ"
            provider-id="token_wrong" />
        <!-- Authorization endpoint is wrong -->
        <sec:client-registration
            registration-id="registration_auth_wrong" client-id="readClient"
            client-secret="secret" authorization-grant-type="authorization_code"
            redirect-uri="{baseUrl}/auth/authorized" scope="READ"
            provider-id="auth_wrong" />
        <!-- Send Wait -->
        <sec:client-registration
            registration-id="registration_wait" client-id="testClient"
            client-secret="secret" authorization-grant-type="authorization_code"
            redirect-uri="{baseUrl}/auth/authorized"
            scope="READ,UPDATE,CREATE,DELETE" provider-id="provider_1" />
        <!-- Set the url directly as placeholders do not work. -->
        <sec:provider provider-id="provider_1"
            authorization-uri="${AUTH_SERVER_PROTOCOL!'http'}://${AUTH_SERVER_IP!'localhost'}:${AUTH_SERVER_PORT!'8900'}/realms/spring-functionaltest/protocol/openid-connect/auth"
            token-uri="${AUTH_SERVER_PROTOCOL!'http'}://${AUTH_SERVER_IP!'localhost'}:${AUTH_SERVER_PORT!'8900'}/realms/spring-functionaltest/protocol/openid-connect/token" />
        <sec:provider provider-id="auth_wrong"
            authorization-uri="${AUTH_SERVER_PROTOCOL!'http'}://${AUTH_SERVER_IP!'localhost'}:${AUTH_SERVER_PORT!'8900'}/realms/spring-functionaltest/protocol/openid-connect/wrong"
            token-uri="${AUTH_SERVER_PROTOCOL!'http'}://${AUTH_SERVER_IP!'localhost'}:${AUTH_SERVER_PORT!'8900'}/realms/spring-functionaltest/protocol/openid-connect/token" />
        <sec:provider provider-id="token_wrong"
            authorization-uri="${AUTH_SERVER_PROTOCOL!'http'}://${AUTH_SERVER_IP!'localhost'}:${AUTH_SERVER_PORT!'8900'}/realms/spring-functionaltest/protocol/openid-connect/auth"
            token-uri="${AUTH_SERVER_PROTOCOL!'http'}://${AUTH_SERVER_IP!'localhost'}:${AUTH_SERVER_PORT!'8900'}/realms/spring-functionaltest/protocol/openid-connect/wrong" />
    </sec:client-registrations>

</beans>