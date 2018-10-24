package com.hornedheck.comeon;

import android.app.TimePickerDialog;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.text.format.DateUtils;
import android.view.View;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;

public class SettingsActivity
  extends AppCompatActivity {


    TextView currentStartTime;
    TextView currentEndTime;
    Calendar time = Calendar.getInstance();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_settings);
		currentStartTime = findViewById(R.id.startDayTime);
		currentEndTime = findViewById(R.id.endDayTime);
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);

        // Проверка наличия установок времени в preferences, в случае истинности вывод значений в Activity

		if (preferences.contains("startTime")) {
            String savedStartTime = preferences.getString("startTime", "");
            currentStartTime.setText(savedStartTime);
        }
        if (preferences.contains("endTime")) {
            String savedEndTime = preferences.getString("endTime", "");
            currentEndTime.setText(savedEndTime);
        }
	}


    // Обработчики кликов на вкладки настроек:

    public void onClickStartDaySetting(View v) {
        new TimePickerDialog(SettingsActivity.this, startTime,
                time.get(Calendar.HOUR_OF_DAY),
                time.get(Calendar.MINUTE), true)
                .show();

    }

    public void onClickEndDaySetting(View v) {
        new TimePickerDialog(SettingsActivity.this, endTime,
                time.get(Calendar.HOUR_OF_DAY),
                time.get(Calendar.MINUTE), true)
                .show();
    }


    // Изменения TextView значений времени:

    private void setInitialStartDayTime() {

	    //считывание start time

        currentStartTime.setText(DateUtils.formatDateTime(this,
                time.getTimeInMillis(),
                DateUtils.FORMAT_SHOW_TIME));

        //сохранение start time в preferences

        final String START_TIME = "startTime";
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        Editor editor = preferences.edit();
        editor.putString(START_TIME, String.valueOf(DateUtils.formatDateTime(this,time.getTimeInMillis(), DateUtils.FORMAT_SHOW_TIME)));
        editor.apply();
    }

    private void setInitialEndDayTime() {

	    //считывание end time

        currentEndTime.setText(DateUtils.formatDateTime(this,
                time.getTimeInMillis(),
                DateUtils.FORMAT_SHOW_TIME));

        //сохранение end time в preferences

        final String END_TIME = "endTime";
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        Editor editor = preferences.edit();
        editor.putString(END_TIME, String.valueOf(DateUtils.formatDateTime(this,time.getTimeInMillis(), DateUtils.FORMAT_SHOW_TIME)));
        editor.apply();
    }



    // Обработка выбора нового времени в TimePicker-диалоге:

    TimePickerDialog.OnTimeSetListener startTime=new TimePickerDialog.OnTimeSetListener() {
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            time.set(Calendar.HOUR_OF_DAY, hourOfDay);
            time.set(Calendar.MINUTE, minute);
            setInitialStartDayTime();
        }
    };

    TimePickerDialog.OnTimeSetListener endTime=new TimePickerDialog.OnTimeSetListener() {
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            time.set(Calendar.HOUR_OF_DAY, hourOfDay);
            time.set(Calendar.MINUTE, minute);
            setInitialEndDayTime();
        }
    };
}

