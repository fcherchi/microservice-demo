package com.fcherchi.demos.infra;

import org.springframework.boot.autoconfigure.security.oauth2.resource.ResourceServerProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;
import org.springframework.security.oauth2.client.token.grant.code.AuthorizationCodeResourceDetails;

/**
 * Created by Fernando Cherchi on 29/10/2016.
 */
public class ClientResources {
    //this is accessed in the yml as facebook.client (or github.client)
    @NestedConfigurationProperty
    private AuthorizationCodeResourceDetails client = new AuthorizationCodeResourceDetails();

    //this is accessed in the yml as facebook.resource (or github.resource)
    @NestedConfigurationProperty
    private ResourceServerProperties resource = new ResourceServerProperties();

    public AuthorizationCodeResourceDetails getClient() {
        return client;
    }

    public ResourceServerProperties getResource() {
        return resource;
    }
}