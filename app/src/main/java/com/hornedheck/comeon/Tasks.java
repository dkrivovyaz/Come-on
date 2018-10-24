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
    SharedPreferences preferences;
    public final String PREF_TAG = "tasks";
    public static  final String DELIMETER = "-";
    static Tasks getInstance()
    {
        return ourInstance;
    }

    private Tasks() {
        allTasks = new ArrayList<>();
    }
    public void refreshTasks(Context context){
        allTasks.clear();
        preferences = PreferenceManager.getDefaultSharedPreferences(context);
        HashSet<String> stringTasks = new HashSet<>(preferences.getStringSet(PREF_TAG, new HashSet<String>()));
        for (String stringTask : stringTasks) {
            String [] piaces = stringTask.split(DELIMETER);
            allTasks.add(new Task(piaces[0], piaces[1], piaces[2], piaces[3]));
        }
    }
    public void addTask(Task task, Context context){
        refreshTasks(context);
        allTasks.add(task);
        preferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor prefsEditor;
        prefsEditor = preferences.edit();
        HashSet<String> stringSet = new HashSet<>();
        for (Task stringTask : allTasks) {
            stringSet.add(stringTask.getString());
        }
        prefsEditor.putStringSet(PREF_TAG, stringSet);
        prefsEditor.commit();
    }
    public ArrayList<Task> getAllTasks(Context context){
        refreshTasks(context);
        return  allTasks;
    }
    public void removeTask(int index, Context context){
        refreshTasks(context);
        allTasks.remove(index);
        preferences = PreferenceManager.getDefaultSharedPreferences(context);
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
