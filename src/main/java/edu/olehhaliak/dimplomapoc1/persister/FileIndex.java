package edu.olehhaliak.dimplomapoc1.persister;


import java.io.*;
import java.util.HashMap;
import java.util.Scanner;

public class FileIndex {
    String fileName;
    HashMap<Integer, Integer> offsetFilePositions;

    public FileIndex(String fileName) {
        this.fileName = fileName;
        this.offsetFilePositions = new HashMap<>();
        if(new File(fileName).exists()){
            fillIndex();
        }
    }


    private void fillIndex(){
        try (var scanner = new Scanner(new File(fileName))){
            scanner.tokens()
                    .forEach(str ->{ //TODO: make index use binary
                        var kv = str.split(":");
                        offsetFilePositions.put(Integer.valueOf(kv[0]),Integer.valueOf(kv[1]));
                    });
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void append(int offset, int filePos) {
        writeToFile(offset, filePos);
        saveToLocalCache(offset, filePos); // TODO: refactor namings (write-save)
    }

    public int getFilePosition(int offset) {
        return offsetFilePositions.get(offset);
    }

    private void saveToLocalCache(int offset, int filePos) {
        offsetFilePositions.put(offset, filePos);
    }

    private void writeToFile(int offset, int filePos) {
        try (var fw = new PrintWriter(new FileOutputStream(fileName, true))) {
            fw.println(offset + ":" + filePos);
        } catch (IOException e) {
            // ignore
        }
    }
}
