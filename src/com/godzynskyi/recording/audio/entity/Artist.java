package com.godzynskyi.recording.audio.entity;

import com.godzynskyi.recording.audio.entity.albums.Album;
import com.godzynskyi.recording.audio.entity.albums.CollectionAlbum;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Class of Artist entity.
 *
 */
public class Artist implements Serializable {
    private String name;
    private List<Album> albums = new ArrayList<>();
    private Genre genre;
    private String about;

    /**
     * Add album to the list of Artist's Albums.
     *
     * @param album     Album that is going to be added.
     * @return          true if album was added otherwise false.
     */
    public boolean addAlbum(Album album) {
        if (album == null) return false;
        for (Album a: albums) {
            if (a.getTitle().equals(album.getTitle())) return false;
        }

        albums.add(album);
        Collections.sort(albums);
        return true;
    }

    /**
     *
     * @return unmodifiable list of albums
     */
    public List<Album> getAlbums() {
        return Collections.unmodifiableList(albums);
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public Artist(String name) {
        this.name = name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Artist artist = (Artist) o;

        if (name != null ? !name.equals(artist.name) : artist.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return name != null ? name.hashCode() : 0;
    }
}
