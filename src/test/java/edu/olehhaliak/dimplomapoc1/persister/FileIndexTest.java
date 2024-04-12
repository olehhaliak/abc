package edu.olehhaliak.dimplomapoc1.persister;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FileIndexTest {

    @Test
    void name() {
        FileIndex fileIndex = new FileIndex("001.idx");
//        fileIndex.append(0,0);
//        fileIndex.append(1,20);
//        fileIndex.append(2,40);
        System.out.println(fileIndex.getFilePosition(1));
    }
}