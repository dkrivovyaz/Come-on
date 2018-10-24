package com.hornedheck.comeon;

import android.util.Log;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;

public class Task {
    private String name;
    private String info;
    private Date date;
    private boolean done;
    final static DateFormat dateFormat = DateFormat.getDateTimeInstance();
    public Task(String name, String info, String done, String date){
        this.name = name;
        this.info = info;
        this.done = Boolean.parseBoolean(done);
        try {
            this.date = dateFormat.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
    public Task(String name, String info, boolean done, Date date){
        this.name = name;
        this.info = info;
        this.done = done;
        this.date = date;

    }

    public String getName() {
        return name;
    }

    public Date getDate() {
        return this.date;
    }
    public String getDateString() {
        return dateFormat.format(date);
    }
    public String getInfo() {
        return info;
    }
    public String getString() {
        return name + Tasks.DELIMETER + info + Tasks.DELIMETER + Boolean.toString(done) + Tasks.DELIMETER + getDateString();
    }

    public boolean isDone() {
        return done;
    }
}
