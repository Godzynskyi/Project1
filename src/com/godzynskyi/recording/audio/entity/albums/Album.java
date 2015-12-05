package com.godzynskyi.recording.audio.entity.albums;


import com.godzynskyi.recording.audio.entity.Track;
import com.godzynskyi.recording.audio.exceptions.OutOfDiscSpaceException;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * This abstract class consists of list of tracks and title of CD
 *
 * @see com.godzynskyi.recording.audio.entity.albums.AlbumOfArtist
 * @see com.godzynskyi.recording.audio.entity.albums.CollectionAlbum
 * @see com.godzynskyi.recording.audio.entity.Track
 */
abstract public class Album implements Serializable, Comparable<Album>{

    String title;
    List<Track> tracks = new LinkedList<>();

    /**
     * Returns position of track with specified title.
     * @param title title of a track we are looking for
     * @return first position of track with similar title
     *         <tt>-1</tt> if there are no tracks with this title
     */
    public int getPositionOfTrack(String title) {
        for(int i = 0; i < tracks.size(); i++) {
            if(tracks.get(i).getTitle().equals(title)) return i;
        }
        return -1;
    }

    /**
     *
     * @return sum of durations of all tracks in this album
     */
    public int getDuration() {
        int amount = 0;
        for (Track t: tracks) {
            amount += t.getDuration();
        }
        return amount;
    }

    /**
     * Add track to album
     *
     * @throws OutOfDiscSpaceException if duration of album more than 70 minutes
     */
    abstract public void addTrack(Track track) throws OutOfDiscSpaceException;


    /**
     * Remove first track with same title
     * @param track title of which compare with track from album
     */
    public void removeTrack(Track track) {
        int pos = getPositionOfTrack(track.getTitle());
        tracks.remove(pos);
    }

    /**
     *
     * @return unmodifiable List of tracks
     */
    public List<Track> getTracks() {
        return Collections.unmodifiableList(tracks);
    }

    /**
     *
     * @return title of Album
     */
    public String getTitle() {
        return title;
    }

    /**
     *  AlbumOfArtist always bigger than CollectionAlbum
     */
    @Override
    abstract public int compareTo(Album o);

    public void setTitle(String title) {
        this.title = title;
    }
}
