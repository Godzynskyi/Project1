package com.godzynskyi.recording.audio.entity;

import java.io.Serializable;

/**
 * This class about rating of tracks. Rating is going up when track is listened more and more times.
 */
public class Rating implements Serializable {

    static int maxListeningOfSong = 1;
    int count = 0;

    /**
     * Increment count. Do maxListeningOfSong maximal.
     */
    public void listenOneTime() {
        count++;
        maxListeningOfSong = Math.max(maxListeningOfSong, count);
    }

    /**
     *
     * @return rating of particular track. It dependents of times that user listen this track
     */
    public int rating() {
        return Math.round((float)count / (float)maxListeningOfSong * 100);
    }
}
