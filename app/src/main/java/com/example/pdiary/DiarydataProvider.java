package com.example.pdiary;

public class DiarydataProvider {
    private String date;
    private String subject;
    private String description;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public DiarydataProvider(String date, String description, String subject) {
        this.date = date;
        this.subject = subject;
        this.description = description;
    }

}
