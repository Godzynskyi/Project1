package com.godzynskyi.recording.audio.entity.albums;

import com.godzynskyi.recording.audio.entity.Track;
import com.godzynskyi.recording.audio.exceptions.OutOfDiscSpaceException;
import com.godzynskyi.recording.audio.service.SortByGenre;

import java.io.Serializable;

/**
 * This Album is for Collections of tracks of different Artists
 */
public class CollectionAlbum extends Album implements Serializable{

    String author;

    public String getAuthor() {
        return author;
    }

    /**
     *
     * @param author Who create this collection.
     */
    public void setAuthor(String author) {
        this.author = author;
    }

    /**
     * Add track to list.
     * @param track Track that could be added to the list.
     * @throws OutOfDiscSpaceException if sum of durations of the tracks more than 70 minutes.
     */
    @Override
    public void addTrack(Track track) throws OutOfDiscSpaceException {
        if (track.getDuration() + getDuration() > 70*60) {
            throw new OutOfDiscSpaceException(new Exception("Total duration of a disc must be less than 70 minutes."));
        }
        tracks.add(track);
        tracks.sort(new SortByGenre());
    }

    /**
     * Compare by title.
     */
    @Override
    public int compareTo(Album o) {
        if (o instanceof CollectionAlbum) {
            return this.title.compareTo(((CollectionAlbum) o).title);
        }
        return -1;
    }
}
