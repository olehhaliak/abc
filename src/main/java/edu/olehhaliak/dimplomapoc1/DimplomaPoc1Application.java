package edu.olehhaliak.dimplomapoc1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

//
//@SpringBootApplication
public class DimplomaPoc1Application {

    //    public static void main(String[] args) {
//        SpringApplication.run(DimplomaPoc1Application.class, args);
//    }
    public static void main(String[] args) throws IOException {
//        RandomAccessFile randomAccessFile = new RandomAccessFile("1.txt", "rw");
//        randomAccessFile.write("abc".getBytes(StandardCharsets.UTF_8));
//        randomAccessFile.close();
//
//
//        var reader = new RandomAccessFile("1.txt", "r");
//        var bytes = new byte[4];
//        reader.read(bytes,0,4);
//        System.out.println(Arrays.toString(bytes));
        write(10000,null,null);
        FileInputStream stream = new FileInputStream("log.txt");
    }

    public static void write(int offset, byte[] key, byte[] value)throws IOException{
        FileOutputStream stream = new FileOutputStream("log.txt");
        stream.write(intToByteArray(offset));
        stream.close();

    }
    public static final byte[] intToByteArray(int value) {
        return new byte[] {
                (byte)(value >>> 24),
                (byte)(value >>> 16),
                (byte)(value >>> 8),
                (byte)value};
    }
}


//Write a log, should contain:
// - metadata
//   - offset
//   - key size
//   - value size
// - payload
//   - key bytes
//   - value bytes