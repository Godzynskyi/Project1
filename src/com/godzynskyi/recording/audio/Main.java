package com.godzynskyi.recording.audio;

import com.godzynskyi.recording.audio.entity.*;
import com.godzynskyi.recording.audio.entity.albums.Album;
import com.godzynskyi.recording.audio.entity.albums.AlbumOfArtist;
import com.godzynskyi.recording.audio.entity.albums.CollectionAlbum;
import com.godzynskyi.recording.audio.exceptions.OutOfDiscSpaceException;
import com.godzynskyi.recording.audio.service.MyLibraryFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by Java Developer on 11.11.2015.
 */
public class Main {
    MusicLibrary myLibrary = new MusicLibrary();

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        Main main = new Main();
        main.mainMenu();
    }

    public void mainMenu() {
        System.out.println();
        System.out.println("11: open library from file.");
        System.out.println("12: save library to file.");
        System.out.println("13: new library");
        System.out.println("1: add Artist.");
        System.out.println("2: add Album.");
        System.out.println("3: add Track.");
        System.out.println("4: add track to Disc");
        System.out.println("5: show tracks of Album");
        System.out.println("6: listen Track");

        Scanner in = new Scanner(System.in);
        int res = in.nextInt();
        switch (res) {
            case 11:
                myLibrary = openFile();
                break;
            case 12:
                saveFile(this.myLibrary);
                break;
            case 13:
                newLibrary();
                break;
            case 1:
                addArtist();
                break;
            case 2:
                addAlbum();
                break;
            case 3:
                addTrack();
                break;
            case 4:
                addTrackToDisc();
                break;
            case 5:
                showAlbum();
                break;
            case 6:
                listenTrack();
                break;
            default:
                break;
        }
        mainMenu();
    }

    private void listenTrack() {
        Track track = chooseTrack();
        track.listen();
    }

    private void showAlbum() {
        Album album = chooseAlbum();
        for (Track t: album.getTracks()) {
            System.out.println(t);
        }
    }

    private Track chooseTrack() {
        System.out.println("Choose track.");
        Album album = chooseAlbum();
        Scanner in = new Scanner(System.in);
        System.out.println("Choose track: ");
        List<Track> tracks = album.getTracks();
        for (int i = 0; i < tracks.size(); i++) {
            System.out.println(i+1 + ": " + tracks.get(i));
        }
        int input = in.nextInt();
        return tracks.get(input-1);
    }

    private void addTrackToDisc() {
        Track track = chooseTrack();

        System.out.println("Choose collection album.");
        Album album1 = chooseAlbum();
        try {
            album1.addTrack(track);
            System.out.println("Track succesfully added to an Album.");
        } catch (OutOfDiscSpaceException e) {
            e.printStackTrace();
            System.out.println("This disc is full.");
        }
    }

    private void addTrack() {
        Album album = chooseAlbum();
        Track track = new Track();
        Scanner in = new Scanner(System.in);
        String artist = null;
        if (album instanceof CollectionAlbum) {
            System.out.println("Artist: ");
            artist = in.next();
        } else if (album instanceof AlbumOfArtist) {
            artist = ((AlbumOfArtist) album).getArtist().getName();
        }
        track.setArtist(artist);

        System.out.print("Title of the track: ");
        String title = in.next();
        track.setTitle(title);

        System.out.println("Format: ");
        for (int i = 0; i < Format.values().length; i++) {
            System.out.println(i+1 + ": " + Format.values()[i]);
        }
        int input = in.nextInt();
        track.setFormat(Format.values()[input-1]);

        System.out.println("Genre: ");
        for (int i = 0; i < Genre.values().length; i++) {
            System.out.println(i+1 + ": " + Genre.values()[i]);
        }
        input = in.nextInt();
        track.setGenre(Genre.values()[input-1]);

        System.out.print("Duration (3:57): ");
        String duration = in.next();
        track.setDuration(Track.convertDuration(duration));

        try {
            album.addTrack(track);
        } catch (OutOfDiscSpaceException e) {
            e.printStackTrace();
        }
    }


    public Artist chooseArtist() {
        System.out.println("Choose artist:");
        List<String> artistList = myLibrary.getArtistsList();
        for (int i = 0; i< artistList.size(); i++) {
            System.out.println(i+1 + ": " + artistList.get(i));
        }
        Scanner in = new Scanner(System.in);
        int input = in.nextInt();
        return myLibrary.getArtist(artistList.get(input-1));
    }

    private Album chooseAlbum() {
        System.out.println("Choose album: ");
        System.out.println("1 - Artist's Album");
        System.out.println("2 - Collection Album");
        Scanner in = new Scanner(System.in);
        int type = in.nextInt();
        List<Album> albums = null;
        switch (type) {
            case 1:
                Artist artist = chooseArtist();
                albums = artist.getAlbums();
                break;
            case 2:
                albums = myLibrary.getCollections();
                break;
            default:
                mainMenu();
                return null;
        }

        System.out.println("Choose album: ");
        for (int i = 0; i < albums.size(); i++) {
            System.out.println(i+1 + ": " + albums.get(i).getTitle());
        }
        int input = in.nextInt();
        return albums.get(input -1);
    }

    private void addAlbum() {
        System.out.println("1 - Artist's Album");
        System.out.println("2 - Collection Album");
        Scanner in = new Scanner(System.in);
        int type = in.nextInt();
        Album album = null;
        switch (type) {
            case 1:
                album = new AlbumOfArtist();
                Artist artist = chooseArtist();
                ((AlbumOfArtist) album).setArtist(artist);

                System.out.print("Year: ");
                int input = in.nextInt();
                ((AlbumOfArtist) album).setYear(input);
                break;
            case 2:
                album = new CollectionAlbum();
                System.out.print("Author of this collection: ");
                String author = in.next();
                ((CollectionAlbum) album).setAuthor(author);
                break;
            default:
                mainMenu();
                break;
        }

        System.out.print("Title of the Album: ");
        String title = in.next();
        album.setTitle(title);

        myLibrary.addAlbum(album);

    }

    private void addArtist() {
        Scanner in = new Scanner(System.in);
        System.out.print("Atrist name: ");
        String input = in.next();
        Artist newArtist = new Artist(input);

        System.out.print("Some words about '" + newArtist.getName() + "': ");
        input = in.next();
        newArtist.setAbout(input);

        for (int i = 0; i < Genre.values().length; i++) {
            System.out.println(i+1 + ": " + Genre.values()[i]);
        }
        System.out.print("What is main genre: ");
        int g = in.nextInt();
        if (g > 0 && g< Genre.values().length) newArtist.setGenre(Genre.values()[g - 1]);

        myLibrary.addArtist(newArtist);
    }

    private static void saveFile(MusicLibrary myLibrary) {
        Scanner in = new Scanner(System.in);
        System.out.print("Enter path: ");
        String path = in.next();
        try {
            MyLibraryFactory.saveToFile(path, myLibrary);
        } catch (IOException e) {
            System.out.println("Something wrong with IO");
            e.printStackTrace();

        }

    }

    private static MusicLibrary newLibrary() {
        return new MusicLibrary();
    }

    private static MusicLibrary openFile() {
        MusicLibrary result = null;
        Scanner in = new Scanner(System.in);
        System.out.print("Enter path: ");
        String path = in.next();
        try {
            result = MyLibraryFactory.openFromFile(path);
        } catch (IOException e) {
            System.out.println("Something wrong with IO");
            e.printStackTrace();
        }
        return result;
    }
}
