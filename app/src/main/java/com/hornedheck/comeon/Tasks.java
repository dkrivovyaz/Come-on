package com.hornedheck.comeon;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.ArraySet;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

class Tasks {
    private static final Tasks ourInstance = new Tasks();
    ArrayList<Task> allTasks;
    private SharedPreferences preferences;
    public final String PREF_TAG = "tasks";
    public static  final String DELIMETER = "-";
    static Tasks getInstance(Context context)
    {
        if(ourInstance.preferences == null){
            ourInstance.preferences = PreferenceManager.getDefaultSharedPreferences(context);
            ourInstance.allTasks = new ArrayList<>();
            ourInstance.refreshTasks();
        }
        return ourInstance;
    }

    private Tasks() {
    }
    public void refreshTasks(){
        allTasks.clear();
        HashSet<String> stringTasks = new HashSet<>(preferences.getStringSet(PREF_TAG, new HashSet<String>()));
        for (String stringTask : stringTasks) {
            String [] piaces = stringTask.split(DELIMETER);
            allTasks.add(new Task(piaces[0], piaces[1], piaces[2], piaces[3]));
        }
    }
    public void addTask(Task task){
        allTasks.add(task);
        SharedPreferences.Editor prefsEditor;
        prefsEditor = preferences.edit();
        HashSet<String> stringSet = new HashSet<>();
        for (Task stringTask : allTasks) {
            stringSet.add(stringTask.getString());
        }
        prefsEditor.putStringSet(PREF_TAG, stringSet);
        prefsEditor.commit();
    }
    public ArrayList<Task> getAllTasks(){
        return  allTasks;
    }
    public void removeTask(int index){
        allTasks.remove(index);
        SharedPreferences.Editor prefsEditor;
        prefsEditor = preferences.edit();
        HashSet<String> stringSet = new HashSet<>();
        for (Task stringTask : allTasks) {
            stringSet.add(stringTask.getString());
        }
        prefsEditor.putStringSet(PREF_TAG, stringSet);
        prefsEditor.commit();
    }

}
