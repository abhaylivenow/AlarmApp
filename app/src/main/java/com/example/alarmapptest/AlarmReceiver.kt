package com.example.alarmapptest

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.media.MediaPlayer
import android.widget.Toast


class AlarmReceiver : BroadcastReceiver() {
    private lateinit var music: MediaPlayer
    override fun onReceive(context: Context?, intent: Intent?) {
//        Toast.makeText(context, "Alarm is ringing", Toast.LENGTH_SHORT).show()
//        music = MediaPlayer.create(context, R.raw.alarm_music)
//        music.start()
        MusicUtil.playMusic(context!!)
    }
}