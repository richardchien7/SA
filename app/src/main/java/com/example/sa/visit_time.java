package com.example.sa;

public class visit_time {

    private visit_time[] records;
    private String id;
    private fieldsvisit fields;


    public visit_time[] getRecords()
    {
        return records;
    }

    public String getId(int i) {
        return records[i].id;
    }

    public fieldsvisit getFields(int i)
    {
        return records[i].fields;
    }
}
