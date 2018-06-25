package com.zl.tesseract.scanner.bean;

public class Teacher {
    private String id;
    private String num;
    private String phone;
    private String name;
    private String school;
    private String description;

    public Teacher(String num, String phone, String name, String school, String description) {
        this.num = num;
        this.phone = phone;
        this.name = name;
        this.school = school;
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
