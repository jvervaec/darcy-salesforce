package com.redhat.darcy.salesforce.testing.services;

import com.sforce.soap.metadata.MetadataConnection;
import com.sforce.soap.partner.LoginResult;
import com.sforce.soap.partner.PartnerConnection;
import com.sforce.ws.ConnectionException;
import com.sforce.ws.ConnectorConfig;

import javax.inject.Inject;
import javax.inject.Provider;

public class DarcySalesforceMetadataConnection implements Provider<MetadataConnection> {

    private final MetadataConnection connection;

    @Inject
    public DarcySalesforceMetadataConnection() {

        try {

            final String USERNAME = "you@yoursandbox.*0";
            final String PASSWORD = "******";
            final String URL = "https://test.salesforce.com/services/Soap/c/34.0";
            final LoginResult loginResult = loginToSalesforce(USERNAME, PASSWORD, URL);

            final ConnectorConfig config = new ConnectorConfig();

            config.setServiceEndpoint(loginResult.getMetadataServerUrl());
            config.setSessionId(loginResult.getSessionId());
            this.connection = new MetadataConnection(config);

        } catch (ConnectionException e) {
            throw new RuntimeException(e);
        }

    }

    private static LoginResult loginToSalesforce(
            final String username,
            final String password,
            final String loginUrl) throws ConnectionException {
        final ConnectorConfig config = new ConnectorConfig();
        config.setAuthEndpoint(loginUrl);
        config.setServiceEndpoint(loginUrl);
        config.setManualLogin(true);
        return (new PartnerConnection(config)).login(username, password);
    }


    @Override
    public MetadataConnection get() {
        return connection;
    }
}
