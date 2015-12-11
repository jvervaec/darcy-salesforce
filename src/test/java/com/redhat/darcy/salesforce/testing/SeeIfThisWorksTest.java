package com.redhat.darcy.salesforce.testing;

import com.google.acai.Acai;
import com.redhat.darcy.salesforce.testing.doubles.vfStubs;
import com.redhat.darcy.salesforce.testing.modules.UnitTestModule;
import com.redhat.darcy.salesforce.testing.services.api.SalesforceService;

import com.sforce.soap.metadata.ApexPage;
import com.sforce.soap.metadata.CustomObject;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import javax.inject.Inject;
import java.nio.charset.Charset;

public class SeeIfThisWorksTest {

    @Rule
    public Acai acai = new Acai(UnitTestModule.class);


    @Inject
    SalesforceService service;

    private CustomObject c;

    @Before
    public void createVisualforceStubs() {
        System.out.println("In BeforeTest");
        this.c = vfStubs.randomObject();

        service.createMetadata(c);

        service.createMetadata(vfStubs.textField(c.getFullName()));
        service.createMetadata(vfStubs.checkbox(c.getFullName()));

        service.updateMetadata(c);

        ApexPage page = new ApexPage();

        String content = "<apex:page><h1>Hello World</h1></apex:page>";
        page.setContent(content.getBytes(Charset.forName("UTF-8")));
        page.setFullName("Automation Test page");
//        page.setMarkup("<apex:page><h1>Hello World</h1></apex:page>");
//
//        page.setName("Automation Test Page");

        service.createMetadata(page);


    }

    @After
    public void tearDown() {
        System.out.println("Deleting " + c.getFullName());
        service.deleteMetadata("CustomObject", new String[]{ c.getFullName() });
    }

    @Test
    public void testDoNothing() {
        System.out.println("Not doing anything");
    }

}
