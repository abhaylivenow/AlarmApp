package com.example.alarmapptest

import android.app.AlarmManager
import android.app.PendingIntent
import android.app.TimePickerDialog
import android.content.Context
import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.TimePicker
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.text.DateFormat
import java.util.*


class MainActivity : AppCompatActivity(), TimePickerDialog.OnTimeSetListener {

    private lateinit var textView: TextView
    private lateinit var btnTimePicker: Button
    private lateinit var btnCancelAlarm: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textView = findViewById(R.id.textView)
        btnTimePicker = findViewById(R.id.btn_timepicker)
        btnCancelAlarm = findViewById(R.id.btn_cancel)

        btnTimePicker.setOnClickListener {
            val timePickerFragment = TimePickerFragment()
            timePickerFragment.show(supportFragmentManager, "time picker")
        }

        btnCancelAlarm.setOnClickListener {
            cancelAlarm()
        }
    }

    override fun onTimeSet(timePickerView: TimePicker?, hourOfDay: Int, minute: Int) {
        val c = Calendar.getInstance()
        c.set(Calendar.HOUR_OF_DAY, hourOfDay)
        c.set(Calendar.MINUTE, minute)
        c.set(Calendar.SECOND, 0)

        updateTimeText(c)
        startAlarm(c)
    }

    private fun updateTimeText(c: Calendar) {
        var timeText = "Alarm set for: "
        timeText += DateFormat.getTimeInstance(DateFormat.SHORT).format(c.time)
        textView.text = timeText
    }

    private fun startAlarm(c: Calendar) {
        val alarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val intent = Intent(this, AlarmReceiver::class.java)
        val pendingIntent = PendingIntent.getBroadcast(this, 1, intent, 0)

        if (c.before(Calendar.getInstance())) {
            c.add(Calendar.DATE, 1)
        }

        alarmManager.setExact(AlarmManager.RTC_WAKEUP, c.timeInMillis, pendingIntent)
    }

    private fun cancelAlarm() {
        MusicUtil.stopMusic()
        val alarmManager = getSystemService(ALARM_SERVICE) as AlarmManager
        val intent = Intent(this, AlarmReceiver::class.java)
        val pendingIntent = PendingIntent.getBroadcast(this, 1, intent, 0)

        alarmManager.cancel(pendingIntent)
        textView.text = "Alarm canceled"
    }
}