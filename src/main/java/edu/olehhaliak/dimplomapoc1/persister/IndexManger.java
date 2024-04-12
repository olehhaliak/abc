package edu.olehhaliak.dimplomapoc1.persister;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;

public class IndexManger {

    public void append(int offset,String file, int filePos){

        try (var fw = new PrintWriter(new FileOutputStream(file, true))){
            fw.println(offset + ":" +filePos);
        }catch (IOException e){
            // ignore
        }
    }
}
