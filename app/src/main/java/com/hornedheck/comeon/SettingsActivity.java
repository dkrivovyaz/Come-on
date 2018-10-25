package com.hornedheck.comeon;

import android.app.TimePickerDialog;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;
import java.util.Locale;

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

	    // Считывание startTime и вывод его на экран:

        int hours = time.get(Calendar.HOUR_OF_DAY);
        int minutes = time.get(Calendar.MINUTE);
        String startTime = String.format(Locale.US, "%02d:%02d", hours, minutes);
        currentStartTime.setText(startTime);

        // Сохранение startTime в preferences:

        final String START_TIME = "startTime";
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        Editor editor = preferences.edit();
        editor.putString(START_TIME, String.valueOf(startTime));
        editor.apply();
    }

    private void setInitialEndDayTime() {

	    // Считывание endTime и вывод его на экран:

        int hours = time.get(Calendar.HOUR_OF_DAY);
        int minutes = time.get(Calendar.MINUTE);
        String endTime = String.format(Locale.US, "%02d:%02d", hours, minutes);
        currentEndTime.setText(endTime);

        // Сохранение endTime в preferences:

        final String END_TIME = "endTime";
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        Editor editor = preferences.edit();
        editor.putString(END_TIME, String.valueOf(endTime));
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

