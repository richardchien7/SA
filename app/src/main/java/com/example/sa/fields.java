package com.example.sa;

public class fields {
    private String id;
    private String password;
    private String Notes;
    private String Name;
//因為我測試資料只放Notes,Name兩個欄位，可以自己加
    public fields(String notes, String name) {
        Notes = notes;
        Name = name;
    }

    public String getId() {
        return id;
    }
public String getPassword(){
        return password;
}
    public String getNotes() {
        return Notes;
    }

    public String getName() {
        return Name;
    }

    public void setNotes(String notes) {
        Notes = notes;
    }

    public void setName(String name) {
        Name = name;
    }
}
