package com.godzynskyi.recording.audio.entity.albums;

import com.godzynskyi.recording.audio.entity.Artist;
import com.godzynskyi.recording.audio.entity.Track;

import java.io.Serializable;

/**
 * This is Album from discography of an Artist
 */
public class AlbumOfArtist extends Album implements Serializable{
    int year;
    Artist artist;

    /**
     * Add track to Album
     * @param track
     */
    @Override
    public void addTrack(Track track) {
        tracks.add(track);
    }

    /**
     * Compare by year of release.
     */
    @Override
    public int compareTo(Album o) {
        if (o instanceof AlbumOfArtist) {
            return this.year - ((AlbumOfArtist) o).year;
        }
        return 1;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public Artist getArtist() {
        return artist;
    }
}
