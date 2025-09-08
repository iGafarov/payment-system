package ru.iprody.utils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static ru.iprody.utils.ContentType.APPLICATION_JS;
import static ru.iprody.utils.ContentType.DEFAULT;
import static ru.iprody.utils.ContentType.IMAGE_JPEG;
import static ru.iprody.utils.ContentType.IMAGE_PNG;
import static ru.iprody.utils.ContentType.TEXT_CSS;
import static ru.iprody.utils.ContentType.TEXT_HTML;

public class FileProcessor {

    public static byte[] readFile(Path fullPath) throws IOException {
        if (Files.exists(fullPath) && !Files.isDirectory(fullPath)) {
            return Files.readAllBytes(fullPath);
        } else {
            throw new FileNotFoundException("File %s not found".formatted(fullPath.getFileName()));
        }
    }

    public static String getContentType(String fileName) {
        if (fileName.endsWith(".html")) return TEXT_HTML.getType();
        if (fileName.endsWith(".css")) return TEXT_CSS.getType();
        if (fileName.endsWith(".js")) return APPLICATION_JS.getType();
        if (fileName.endsWith(".png")) return IMAGE_PNG.getType();
        if (fileName.endsWith(".jpg") || fileName.endsWith(".jpeg")) return IMAGE_JPEG.getType();
        return DEFAULT.getType();
    }
}
