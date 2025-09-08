package ru.iprody.utils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileProcessor {

    public static String readFile(Path fullPath) throws IOException {
        if (Files.exists(fullPath) && !Files.isDirectory(fullPath)) {
            return Files.readString(fullPath);
        } else {
            throw new FileNotFoundException("File %s not found".formatted(fullPath.getFileName()));
        }
    }
}
