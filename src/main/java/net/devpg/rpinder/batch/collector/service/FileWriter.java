package net.devpg.rpinder.batch.collector.service;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class FileWriter {
    @Value("${collect.filePath}")
    private String filePath;

    public void write(String jsonData) {
        Path path = Paths.get(filePath);
        checkFile(path);

        try (FileChannel channel = FileChannel.open(path, StandardOpenOption.WRITE,
            StandardOpenOption.APPEND)) {
            ByteBuffer byteBuffer = StandardCharsets.UTF_8.encode(jsonData + "\n");
            channel.write(byteBuffer);
        } catch (IOException e) {
            log.error("FileWriter-write error..!", e);
        }
    }

    @SneakyThrows(IOException.class)
    private void checkFile(Path path) {
        if (!Files.exists(path)) {
            Files.createFile(path);
        }
    }
}
