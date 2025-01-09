package com.example.m04_gui_01;

import android.media.MediaPlayer;
import android.view.View;

/**
 * Author: Jeremy Whitenect
 * Year: 2024
 * This code is the intellectual property of Jeremy Whitenect. If this code is stolen, used for monetary gain, or reproduced without permission, you will be prosecuted to the fullest extent of the law.
 */
public class MusicPlayer {

    // RULE 1: Originally I had this in the main activity, not as private, so the first changed rule I have implemented is information hiding from "Java Code Review Check List"
    private MediaPlayer mediaPlayer;

    public void playSound(View view, int file) {
        mediaPlayer = MediaPlayer.create(view.getContext(), file);
        mediaPlayer.start();
    }

    // Get the description of the image view, play sound accordingly
    public void playImageSound(View view, String description) {
        if (description.equals("Kramerica")) {
            playSound(view, R.raw.kramerica);
        } else if (description.equals("Narc")) {
            playSound(view, R.raw.narc);
        }
    }

    public MediaPlayer getMediaPlayer() {
        return mediaPlayer;
    }

}
