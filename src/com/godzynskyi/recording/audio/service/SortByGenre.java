package com.godzynskyi.recording.audio.service;

import com.godzynskyi.recording.audio.entity.Genre;
import com.godzynskyi.recording.audio.entity.Track;

import java.util.Comparator;

/**
 * Comparator to compare tracks by Genre.
 * If genres are different compare by genre,
 * If genres are same compare by track title.
 */
public class SortByGenre implements Comparator<Track> {

    @Override
    public int compare(Track o1, Track o2) {
        Genre genre1 = o1.getGenre();
        Genre genre2 = o2.getGenre();
        int res = genre1.compareTo(genre2);
        if (res != 0) return res;
        return o1.getTitle().compareTo(o2.getTitle());
    }
}