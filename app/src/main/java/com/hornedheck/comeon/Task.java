package com.hornedheck.comeon;

import android.util.Log;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;

public class Task {
    private String name;
    private String info;
    private Date date;
    final static DateFormat dateFormat = DateFormat.getDateTimeInstance();
    public Task(String name, String info, String date){
        this.name = name;
        this.info = info;
        try {
            this.date = dateFormat.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
    public Task(String name, String info, Date date){
        this.name = name;
        this.info = info;
        this.date = date;

    }

    public String getName() {
        return name;
    }

    public Date getDate() {
        return this.date;
    }
    public String getDateString() {
        Date d = date;
        Log.d("Meesage", date.toString());
        return dateFormat.format(date);
    }
    public String getInfo() {
        return info;
    }
    public String getString() {
        return name + Tasks.DELIMETER + info + Tasks.DELIMETER + getDateString();
    }

}
