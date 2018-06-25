package com.zl.tesseract.scanner.bean;

public class Student {
    private String name;
    private String id;
    private String psw;
    private String school;
    private String studentClass;
    private String age;
    private String phone;
    private String sex;
    private String examName;
    private String operateMan;
    private String grade;
    private int num;

    public Student()
    {

    }
//正式学生信息
    public Student( int num,String name, String id,String psw, String school, String studentClass, String age, String phone, String sex) {
        this.num=num;
        this.name = name;
        this.id = id;
        this.psw = psw;
        this.school = school;
        this.studentClass = studentClass;
        this.age = age;
        this.phone = phone;
        this.sex = sex;
    }
    public Student(String name, String id,String psw, String school, String studentClass, String age, String phone, String sex) {
        this.name = name;
        this.id = id;
        this.psw = psw;
        this.school = school;
        this.studentClass = studentClass;
        this.age = age;
        this.phone = phone;
        this.sex = sex;
    }

    public Student(String name, String school) {
        this.name = name;
        this.school = school;
    }

    //老师身份看得学生名单
    public Student(String name, String id, String studentClass) {
        this.name = name;
        this.id = id;
        this.studentClass = studentClass;
    }
    //老师身份看的学生成绩
    public Student(String name, String examName, String operateMan,String grade) {
        this.name = name;
        this.examName = examName;
        this.operateMan = operateMan;
        this.grade = grade;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNum() { return num;}

    public void setNum(int num) { this.num = num;}

    public String getStudentClass() {
        return studentClass;
    }

    public void setStudentClass(String studentClass) {
        this.studentClass = studentClass;
    }

    public String getExamName() {
        return examName;
    }

    public void setExamName(String examName) {
        this.examName = examName;
    }

    public String getOperateMan() {
        return operateMan;
    }

    public void setOperateMan(String operateMan) {
        this.operateMan = operateMan;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPsw() {
        return psw;
    }

    public void setPsw(String psw) {
        this.psw = psw;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }
}
