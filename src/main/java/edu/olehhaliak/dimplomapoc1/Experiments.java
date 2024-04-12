package edu.olehhaliak.dimplomapoc1;

import lombok.*;

import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;

public class Experiments {
    public static void main(String[] args) {
        var log = Log.builder()
                .value("abc")
                .build();
        writeLog(log);
    }

    static int relOff = 0;

    @SneakyThrows
    public static Log writeLog(Log log){
        var currentOffset = relOff++;
        var timestamp = System.currentTimeMillis();
        System.out.println(System.currentTimeMillis());
        FileOutputStream fileOutputStream = new FileOutputStream("000.log");
        var payload = log.value.getBytes(StandardCharsets.US_ASCII) ;
        var bytes = ByteBuffer.allocate(4 + 4 + 8 + 4  + payload.length)
                .putInt(0,relOff) // partitionOffset
                .putInt(4,relOff) // relativeOffset
                .putLong(8,timestamp)     //timestamp
                .putInt(16,payload.length)
                .put(20,payload)
                .array();

        fileOutputStream.write(bytes);

        fileOutputStream.close();
        return log;
    }


    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    @ToString
    private static class Log{
        Integer offset;
        Long unixTimestamp;
        String value;
    }
}
