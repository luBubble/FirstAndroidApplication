package com.zl.tesseract.scanner.bean;

public class Grade {
    private String studentName;
    private String teacherName;
    private String paperName;
    private String score;
    private String scanTime;
    private String wrong;
    public Grade(){

    }

    public Grade(String studentName, String teacherName, String paperName, String score, String scanTime, String wrong) {
        this.studentName = studentName;
        this.teacherName = teacherName;
        this.paperName = paperName;
        this.score = score;
        this.scanTime = scanTime;
        this.wrong = wrong;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public String getPaperName() {
        return paperName;
    }

    public void setPaperName(String paperName) {
        this.paperName = paperName;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getScanTime() {
        return scanTime;
    }

    public void setScanTime(String scanTime) {
        this.scanTime = scanTime;
    }

    public String getWrong() {
        return wrong;
    }

    public void setWrong(String wrong) {
        this.wrong = wrong;
    }
}
