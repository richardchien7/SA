package com.example.sa;

public class Reqregist {

    public String id ;
    private patient patient;
    private fieldsregist fields;

    public String getId(){return id;}

    public Reqregist(fieldsregist fields)
    {
        this.fields = fields;
    }





    public fieldsregist getFields() {
        return fields;
    }
}
