package com.example.sa;

public class Req {
    public String id ;
    private patient patient;
    private fields fields;

    public String getId(){return id;}
    public Req(patient patient) {
        this.patient = patient;
    }
    public Req(fields fields)
    {
        this.fields = fields;
    }

    public fields getFields() {
        return fields;
    }

}