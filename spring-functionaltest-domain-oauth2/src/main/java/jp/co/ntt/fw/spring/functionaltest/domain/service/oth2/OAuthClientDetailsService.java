/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.domain.service.oth2;

import java.util.HashSet;
import java.util.Set;

import javax.inject.Inject;

import jp.co.ntt.fw.spring.functionaltest.domain.model.OAuthClient;
import jp.co.ntt.fw.spring.functionaltest.domain.repository.oth2.OAuthClientRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.security.oauth2.provider.NoSuchClientException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("clientDetailsService")
@Transactional
public class OAuthClientDetailsService implements ClientDetailsService {

    private static final Logger logger = LoggerFactory
            .getLogger(OAuthClientDetailsService.class);

    @Inject
    OAuthClientRepository clientRepository;

    @Value("${oth2.serverUrl}")
    String redirectHost;

    @Override
    public ClientDetails loadClientByClientId(String clientId) throws ClientRegistrationException {

        OAuthClient client = clientRepository.findClientByClientId(clientId);
        if (client == null) {
            throw new NoSuchClientException("No client with requested id: "
                    + clientId);
        }
        Set<String> clientScopes = clientRepository
                .findClientScopesByClientId(clientId);
        Set<String> clientResources = clientRepository
                .findClientResourcesByClientId(clientId);
        Set<String> clientGrants = clientRepository
                .findClientGrantsByClientId(clientId);
        Set<String> clientRedirectUris = clientRepository
                .findClientRedirectUrisByClientId(clientId);

        OAuthClientDetails clientDetails = new OAuthClientDetails();
        clientDetails.setClientId(client.getClientId());
        clientDetails.setClientSecret(client.getClientSecret());
        clientDetails.setAccessTokenValiditySeconds(client
                .getAccessTokenValidity());
        clientDetails.setRefreshTokenValiditySeconds(client
                .getRefreshTokenValidity());
        clientDetails.setResourceIds(clientResources);
        clientDetails.setScope(clientScopes);
        clientDetails.setAuthorizedGrantTypes(clientGrants);

        Set<String> redirectUris = new HashSet<>();
        String redirectUri;
        for (String uri : clientRedirectUris) {
            redirectUri = redirectHost + "/" + uri;
            logger.debug("Set client redirect uri : {}", redirectUri);
            redirectUris.add(redirectUri);
        }
        clientDetails.setRegisteredRedirectUri(redirectUris);

        clientDetails.setOauthClient(client);

        return clientDetails;
    }
}
