package com.project.doctorapp;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;


public class reminderPatient extends AppCompatActivity implements View.OnClickListener{

        private int notificationId = 1;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_reminder_patient);

            //Set onClick Listener
            findViewById(R.id.setBtn).setOnClickListener(this);
            findViewById(R.id.cancelBtn).setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            EditText editText = findViewById(R.id.editText);
            TimePicker timePicker = findViewById(R.id.timePicker);

            //Set notificationId and message
            Intent intent = new Intent(reminderPatient.this, AlarmReceiver.class);
            intent.putExtra("notificationId",notificationId);
            intent.putExtra("message",editText.getText().toString());

            //PendingIntent
            PendingIntent alarmIntent = PendingIntent.getBroadcast(
                    reminderPatient.this,0,intent,PendingIntent.FLAG_CANCEL_CURRENT
            );

            //AlarmManager
            AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);

            switch(v.getId()){
                case R.id.setBtn:
                    //Set alarm
                    int hour = timePicker.getCurrentHour();
                    int minute = timePicker.getCurrentMinute();

                    //Create time
                    Calendar startTime = Calendar.getInstance();
                    startTime.set(Calendar.HOUR_OF_DAY,hour);
                    startTime.set(Calendar.MINUTE,minute);
                    startTime.set(Calendar.SECOND,0);
                    long alarmStartTime = startTime.getTimeInMillis();

                    //Set alarm
                    alarmManager.set(AlarmManager.RTC_WAKEUP,alarmStartTime,alarmIntent);

                    Toast.makeText(this,"Done",Toast.LENGTH_SHORT).show();
                    break;

                case R.id.cancelBtn:
                    //Cancel alarm
                    alarmManager.cancel(alarmIntent);
                    Toast.makeText(this,"Cancelled", Toast.LENGTH_SHORT).show();
            }
        }
    }