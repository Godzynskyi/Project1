package com.godzynskyi.recording.audio.entity;

import com.godzynskyi.recording.audio.entity.albums.Album;
import com.godzynskyi.recording.audio.entity.albums.AlbumOfArtist;
import com.godzynskyi.recording.audio.entity.albums.CollectionAlbum;
import com.godzynskyi.recording.audio.service.MyLibraryFactory;

import java.io.Serializable;
import java.util.*;

/**
 * This class has all tracks like in folders
 */
public class MusicLibrary implements Serializable{
    private static final long serialVersionUID = 229913068;

    Set<Artist> artistList = new HashSet<>();

    List<CollectionAlbum> collections = new ArrayList<>();

    /**
     *
     * @param artist Artist to add to MusicLibrary
     */
    public void addArtist(Artist artist) {
        if (artist == null) return;
        if (getArtist(artist.getName()) != null) return;

        artistList.add(artist);
    }

    /**
     * Get Artist by name
     * @param name Name of Artist
     * @return Artist from MusicLibrary
     * {@null} if there is no Artist with this name
     */
    public Artist getArtist(String name) {
        for (Artist a: artistList) {
            if (a.getName().equals(name)) return a;
        }
        return null;
    }

    /**
     * Music Library contains of Disc Collections.
     * @return Unmodifiable Collection from Library.
     */
    public List<Album> getCollections() {
        return Collections.unmodifiableList(collections);
    }

    /**
     * Music Library contains of Artist Collections.
     * @return Names or artists in this Library.
     */
    public List<String> getArtistsList() {
        List<String> result = new ArrayList<>();
        for (Artist a: artistList) {
            result.add(a.getName());
        }
        return result;
    }

    /**
     * Add album to Library.
     * @param album if album is CollectionAlbum, add to collections
     *              if album is Artist Album, add to artistList
     */
    public void addAlbum(Album album) {
        if (album instanceof CollectionAlbum) {
            collections.add((CollectionAlbum) album);
        }
        if (album instanceof AlbumOfArtist) {
            AlbumOfArtist albumOfArtist = (AlbumOfArtist) album;
            Artist artist = albumOfArtist.getArtist();
            artist.addAlbum(albumOfArtist);

        }
    }
}
