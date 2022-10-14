package com.example.alarmapptest

import android.content.Context
import android.media.MediaPlayer
import android.widget.Toast

object MusicUtil {
    private lateinit var music: MediaPlayer
    fun playMusic(context: Context) {
        Toast.makeText(context, "Alarm is ringing", Toast.LENGTH_SHORT).show()
        music = MediaPlayer.create(context, R.raw.alarm_music)
        music.start()
    }

    fun stopMusic() {
        music.stop()
    }
}