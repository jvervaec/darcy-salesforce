package com.redhat.darcy.salesforce.testing.services.impl;

import com.redhat.darcy.salesforce.testing.SalesforceException;
import com.redhat.darcy.salesforce.testing.services.SalesforceConnection;
import com.redhat.darcy.salesforce.testing.services.api.SalesforceService;
import com.sforce.soap.enterprise.sobject.SObject;
import com.sforce.soap.metadata.DeleteResult;
import com.sforce.soap.metadata.Metadata;
import com.sforce.soap.metadata.SaveResult;
import com.sforce.ws.ConnectionException;

import javax.inject.Inject;
import java.util.stream.Stream;

import static java.util.stream.Collectors.joining;

public class WscMetadataService implements SalesforceService {

    @Inject
    SalesforceConnection connection;

    @Override
    public <T extends Metadata> void createMetadata(T object) {
        try {

            SaveResult[] sr = connection.get().createMetadata(new Metadata[]{object});
            if (sr.length > 1) {
                throw new IllegalStateException("Created 1 object, but SaveResult contained " + sr.length + " results.  That shouldn't happen...");
            }
            if (!sr[0].isSuccess()) {
                String e = Stream.of(sr[0].getErrors())
                        .map(err -> err.getStatusCode() + ": " + err.getMessage())
                        .collect(joining(", "));
                throw new SalesforceException(e);
            }

        } catch (ConnectionException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public <T extends Metadata> void updateMetadata(T object) {
        try {
            SaveResult[] sr = connection.get().updateMetadata(new Metadata[]{object});
            if (sr.length > 1) {
                throw new IllegalStateException("Updated 1 object, but SaveResult contained " + sr.length + " results.  That shouldn't happen...");
            }
            if (!sr[0].isSuccess()) {
                String e = Stream.of(sr[0].getErrors())
                        .map(err -> err.getStatusCode() + ": " + err.getMessage())
                        .collect(joining(", "));
                throw new SalesforceException(e);
            }

        } catch (ConnectionException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteMetadata(String type, String[] fullNames) {
        try {

            DeleteResult[] dr = connection.get().deleteMetadata(type, fullNames);
            if (!dr[0].isSuccess()) {
                String e = Stream.of(dr[0].getErrors())
                        .map(err -> err.getMessage())
                        .collect(joining(", "));
                throw new SalesforceException(e);
            }
        } catch (ConnectionException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public <T extends SObject> void create(T object) {
        try {
            com.sforce.soap.enterprise.SaveResult[] sr = connection.get().create(new SObject[]{object});
            if (!sr[0].isSuccess()) {
                String e = Stream.of(sr[0].getErrors())
                        .map(err -> err.getStatusCode() + ": " + err.getMessage())
                        .collect(joining(", "));
                throw new SalesforceException(e);
            }
        } catch (ConnectionException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public <T extends SObject> void update(T object) {

    }

    @Override
    public void delete(String id) {

    }

}
