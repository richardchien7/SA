package com.example.sa;

public class Reqconfirm {
    private Reqconfirm[] records;
    private String id;
    private fieldsconfirm fields;


    public Reqconfirm(fieldsconfirm fields) {

        this.fields = fields;
    }

    public Reqconfirm[] getRecords() {
        return records;
    }

    public String getId(int i) {
        return records[i].id;
    }


    public fieldsconfirm getFields(int i) {
        return records[i].fields;
    }
}
