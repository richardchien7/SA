package com.example.sa;

public class Infor {
    private Infor[] records;
    private String id = "1";
    private fields fields;
    private String createTime;

    public Infor(String id, fields fields1, String createTime) {
        this.id = id;
        this.fields = fields1;
        this.createTime = createTime;
    }

    public Infor[] getRecords() {
        return records;
    }

    public String getId(int i) {
        return records[i].id;
    }

    public String getfieldsName(int i) {
        return records[i].getFields().getName();
    }
    public String getfieldsName() {
        return getFields().getName();
    }
    public String getfieldsNote(){
        return fields.getNotes();
    }
    public String getfieldsid(int i){
        return records[i].getFields().getId();
    }
    public String getfieldspassword(int i){
        return records[i].getFields().getPassword();
    }
    public fields getFields() {
        return fields;
    }

    public String getCreateTime() {
        return createTime;
    }

}
