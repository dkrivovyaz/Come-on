package com.hornedheck.comeon;

import android.app.TimePickerDialog;
import android.os.Bundle;
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

		//ToDo: две строки ниже должны брать значения из Preferences

		currentStartTime = findViewById(R.id.startDayTime);
		currentEndTime = findViewById(R.id.endDayTime);
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

    //ToDo: два метода ниже должны сохранять значение time.getTimeInMillis() в Preferences

    private void setInitialStartDayTime() {
        currentStartTime.setText(DateUtils.formatDateTime(this,
                time.getTimeInMillis(),
                DateUtils.FORMAT_SHOW_TIME));
    }

    private void setInitialEndDayTime() {
        currentEndTime.setText(DateUtils.formatDateTime(this,
                time.getTimeInMillis(),
                DateUtils.FORMAT_SHOW_TIME));
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

