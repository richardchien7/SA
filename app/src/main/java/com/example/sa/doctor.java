package com.example.sa;

public class doctor {

    private doctor[] records;
    private String id;
    private fields fields;


    public doctor(String id) {

        this.id = id;
    }

    public doctor[] getRecords() {
        return records;
    }

    public String getId(int i) {
        return records[i].id;
    }
    public String getdoctorname(int i){
        return records[i].getFields().getDoctor_name1();
    }

    public fields getFields() {
        return fields;
    }


}
