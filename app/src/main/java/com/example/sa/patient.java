package com.example.sa;

public class patient {

    private patient[] records;
    private String id;
    private fields fields;


    public patient(String id) {

        this.id = id;
    }

    public patient[] getRecords() {
        return records;
    }

    public String getId(int i) {
        return records[i].id;
    }


    public fields getFields(int i) {
        return records[i].fields;
    }
}