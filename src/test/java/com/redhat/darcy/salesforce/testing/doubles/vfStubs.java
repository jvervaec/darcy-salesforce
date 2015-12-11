package com.redhat.darcy.salesforce.testing.doubles;

import com.sforce.soap.metadata.CustomField;
import com.sforce.soap.metadata.CustomObject;
import com.sforce.soap.metadata.DeploymentStatus;
import com.sforce.soap.metadata.FieldType;
import com.sforce.soap.metadata.SharingModel;

public class vfStubs {

    // Maybe some static methods that return vf markup?

    public static CustomObject randomObject() {
        CustomObject co = new CustomObject();

        co.setFullName("Automation_Object123__c");
        co.setDescription("Temporary object for darcy salesforce element functional testing");
        co.setEnableActivities(true);
        co.setLabel("Automation Object");
        co.setPluralLabel("Automation Objects");
        co.setDeploymentStatus(DeploymentStatus.Deployed);
        co.setSharingModel(SharingModel.ReadWrite);

        CustomField nf = new CustomField();
        nf.setFullName("Name");
        nf.setLabel("Name");
        nf.setType(FieldType.AutoNumber);
        co.setNameField(nf);

        return co;
    }

    public static CustomField textField(String objectName) {
        CustomField f = new CustomField();

        f.setType(FieldType.Text);
        f.setFullName(objectName + ".Text_Field__c");
        f.setLength(80);
        f.setLabel("Text field");
        System.out.println(f.getFullName());
        return f;
    }

    public static CustomField checkbox(String objectName) {
        CustomField f = new CustomField();

        f.setType(FieldType.Checkbox);
        f.setFullName(objectName + ".Checkbox__c");
        f.setLabel("Checkbox");
        f.setDefaultValue("false");
        System.out.println(f.getFullName());
        return f;
    }

    public static CustomField autoNumber(String objectName) {
        CustomField f = new CustomField();

        f.setType(FieldType.AutoNumber);
        f.setFullName(objectName + ".AutoNumber__c");
        f.setLabel("AutoNumber");
        System.out.println(f.getFullName());
        return f;
    }

}
