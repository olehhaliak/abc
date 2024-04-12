package edu.olehhaliak.dimplomapoc1.persister;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;

public class FileLogPersister implements LogPersister {
    int currentOffset = 0;
    int currentPos = 0;

    public static void main(String[] args) {
        var flp = new FileLogPersister();
        var log = new Log.LogBuilder()
                .partition(1)
                .key("key")
                .value("payload")
                .build();
        flp.persistLog(log);
        flp.persistLog(log);
    }

    @Override
    public Log persistLog(Log log) {
        log.setOffset(currentOffset++);
        log.setUnixTimestamp(System.currentTimeMillis());
        var bytes = serialize(log);
        appendToLogFile(bytes);
        currentPos += bytes.length;
        addToIndex(currentOffset, currentPos);
        return log;
    }

    private void addToIndex(int currentOffset, int currentPos) {
        try (var fw = new PrintWriter(new FileOutputStream("000.idx", true))){
            fw.println(currentOffset + ":" +currentPos);
        }catch (IOException e){
            // ignore
        }
    }

    @Override
    public Log readLog(String offset) {
        return null;
    }

    private byte[] serialize(Log log) {
        var key = log.getKey().getBytes(StandardCharsets.UTF_8);
        var payload = log.getValue().getBytes(StandardCharsets.UTF_8);
        return ByteBuffer.allocate(
                        4 +//offset
                                8 + //timestamp
                                4 + // key size
                                4 + // payload size
                                key.length +
                                payload.length)
                .putInt(0, log.getOffset())
                .putLong(4, log.unixTimestamp)
                .putInt(12, key.length)
                .putInt(16, payload.length)
                .put(20, key)
                .put(20 + key.length, payload)
                .array();

    }

    private void appendToLogFile(byte[] bytes) {
        try (var fos = new FileOutputStream("000.log", true)) {
            fos.write(bytes);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
