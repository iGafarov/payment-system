package ru.iprody;

import ru.iprody.exception.BadRequestException;
import ru.iprody.exception.HttpException;
import ru.iprody.exception.NotFoundException;
import ru.iprody.utils.StatusCode;
import ru.iprody.utils.FileProcessor;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Path;

import static ru.iprody.utils.ContentType.DEFAULT;

public class SimpleHttpServer {

    private static final int PORT = 8080;
    private static final String STATIC_DIR = "simple-http-server/static";

    public static void main(String[] args) throws IOException {
        while (true) {
            try (ServerSocket serverSocket = new ServerSocket(PORT)) {
                System.out.println("Server started on http://localhost:8080");
                establishConnection(serverSocket);
            }
        }
    }

    private static void establishConnection(ServerSocket serverSocket) throws IOException {
        try (Socket connection = serverSocket.accept()) {
            try {
                String path = extractHttpPath(connection);
                byte[] content = readContent(path);
                String contentType = FileProcessor.getContentType(path);

                writeResponse(connection, StatusCode.OK, contentType, content);
            } catch (HttpException e) {
                writeResponse(connection, e.getStatusCode(), DEFAULT.getType(), e.getContent().getBytes());
            }
        }
    }

    private static String extractHttpPath(Socket connection) throws IOException, BadRequestException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

        String line = bufferedReader.readLine();
        if (line != null && line.startsWith("GET")) {
            String[] lineParts = line.split(" ");
            if (lineParts.length > 2) {
                return lineParts[1];
            }
        }

        throw new BadRequestException("Wrong HTTP start line");
    }

    private static byte[] readContent(String path) throws NotFoundException {
        if ("/".equals(path)) {
            return "<h1>Hello World!</h1>".getBytes();
        }
        String filePath = path.substring(1); // remove slash
        Path fullPath = Path.of(STATIC_DIR, filePath);

        try {
            return FileProcessor.readFile(fullPath);
        } catch (IOException e) {
            e.printStackTrace();
            throw new NotFoundException(e.getMessage());
        }
    }

    private static void writeResponse(Socket connection,
                                      StatusCode statusCode,
                                      String contentType,
                                      byte[] content) throws IOException {
        BufferedOutputStream output = new BufferedOutputStream(connection.getOutputStream());

        writeContent(output, statusCode.getCode(), statusCode.getStatus(), contentType, content);

        output.flush();
    }

    private static void writeContent(BufferedOutputStream output,
                                     int code,
                                     String status,
                                     String contentType,
                                     byte[] content) throws IOException {
        output.write("""
                HTTP/1.1 %d %s
                Content-Type: %s
                Content-Length: %d
                
                """.formatted(code, status, contentType, content.length).getBytes());
        output.write(content);
    }
}
