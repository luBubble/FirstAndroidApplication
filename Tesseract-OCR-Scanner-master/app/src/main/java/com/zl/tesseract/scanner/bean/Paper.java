package com.zl.tesseract.scanner.bean;

public class Paper {
    private String paperId;
    private String paperName;
    private String paperBuildTime;

    public Paper(){

    }


    public Paper(String paperId, String paperName, String paperBuildTime) {
        this.paperId = paperId;
        this.paperName = paperName;
        this.paperBuildTime = paperBuildTime;
    }

    public String getPaperId() {
        return paperId;
    }

    public void setPaperId(String paperId) {
        this.paperId = paperId;
    }

    public String getPaperName() {
        return paperName;
    }

    public void setPaperName(String paperName) {
        this.paperName = paperName;
    }

    public String getPaperBuildTime() {
        return paperBuildTime;
    }

    public void setPaperBuildTime(String paperBuildTime) {
        this.paperBuildTime = paperBuildTime;
    }
}
