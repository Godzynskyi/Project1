package com.godzynskyi.recording.audio.service;

import com.godzynskyi.recording.audio.entity.MusicLibrary;

import java.io.*;

/**
 * Class to save and open MusicLibrary to (from) file
 */
public class MyLibraryFactory {

    /**
     * Returns MusicLibrary from file
     * @param path String like "c:\\library.txt"
     * @return MyLibrary Object from file
     * @throws java.io.IOException if there no object in file or no file at that @path
     */
    public static MusicLibrary openFromFile(String path) throws IOException {
        MusicLibrary res;
        FileInputStream fis = new FileInputStream(path);
        BufferedInputStream bis = new BufferedInputStream(fis);
        ObjectInputStream ois = new ObjectInputStream(bis);
        try {
            res = (MusicLibrary) ois.readObject();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new IOException();
        } finally {
            ois.close();
        }

        return res;
    }

    /**
     * Save MusicLibrary to file on the disc
     * @param path          String like "c:\\library.txt"
     * @param myLibrary     Object to save
     * @throws java.io.IOException  if there no access to file or to memory
     */
    public static void saveToFile(String path, MusicLibrary myLibrary) throws IOException {
        FileOutputStream fos = new FileOutputStream(path);
        BufferedOutputStream bos = new BufferedOutputStream(fos);
        ObjectOutputStream oos = new ObjectOutputStream(bos);
        oos.writeObject(myLibrary);
        oos.flush();
        oos.close();
    }
}
