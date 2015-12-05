package com.godzynskyi.recording.audio.entity;

import com.godzynskyi.recording.audio.entity.albums.Album;

import java.io.Serializable;

public class Track implements Serializable {
    String title;
    String artist;

    Genre genre;
    Format format;
    private int duration;       // in seconds
    Rating rating = new Rating();

    /**
     * Convert duration from String "mm:ss" to seconds
     * @param dur Must have ":" between minutes and seconds
     * @return duration in seconds
     */
    public static int convertDuration(String dur) {
        String[] durSplit = dur.split(":");
        int minutes = Integer.parseInt(durSplit[0]);
        int seconds = Integer.parseInt(durSplit[1]);
        return minutes * 60 + seconds;
    }

    /**
     * Convert duration in seconds to String "mm:ss"
     * @param seconds duration in seconds
     * @return String "mm:ss"
     */
    public static String convertDuration(int seconds) {
        return seconds/60 + ":" + (seconds%60);
    }

    /**
     * Easy realisation of playing music.
     * It has a rating listener.
     */
    public void listen() {
        System.out.println(title + " playing...");
        rating.listenOneTime();
    }

    public Format getFormat() {
        return format;
    }

    public void setFormat(Format format) {
        this.format = format;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public int getDuration() {
        return duration;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();

        result.append(artist)
                .append(" - ")
                .append(title)
                .append(" (")
                .append(String.format("%d:%02d",duration/60,duration%60))
                .append(")")
                .append(" ");
        for (int i=0; i < rating.count * 5 / Rating.maxListeningOfSong; i++) {
            result.append("*");
        }
        return result.toString();
    }
}
