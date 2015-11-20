package com.redhat.darcy.salesforce.testing.modules;

import com.google.inject.AbstractModule;
import com.google.inject.Scopes;
import com.redhat.darcy.salesforce.testing.services.api.SalesforceService;
import com.redhat.darcy.salesforce.testing.services.impl.WscMetadataService;

public class UnitTestModule extends AbstractModule {

    @Override
    protected void configure() {

        bind(SalesforceService.class).to(WscMetadataService.class).in(Scopes.SINGLETON);
    }
}
