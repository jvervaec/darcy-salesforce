package com.redhat.darcy.salesforce.testing.services.api;

import com.sforce.soap.metadata.Metadata;

public interface SalesforceService {

    <T extends Metadata> void createMetadata(T object);
    <T extends Metadata> void updateMetadata(T object);
    void deleteMetadata(String type, String[] fullNames);
//    <T extends SObject> void create(T object);
//    <T extends SObject> void update(T object);
    void delete(String id);

}
