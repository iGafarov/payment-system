package ru.iprody;

import ru.iprody.exception.BadRequestException;
import ru.iprody.exception.HttpException;
import ru.iprody.exception.NotFoundException;
import ru.iprody.utils.StatusCode;
import ru.iprody.utils.FileProcessor;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Path;

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
                String path = exctractHttpPath(connection);
                String content = readContent(path);
                writeResponse(connection, StatusCode.OK, content);
            } catch (HttpException e) {
                writeResponse(connection, e.getStatusCode(), e.getContent());
            }
        }
    }

    private static String exctractHttpPath(Socket connection) throws IOException, BadRequestException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String path = "";

        String line = bufferedReader.readLine();
        if (line != null && line.startsWith("GET")) {
            String[] lineParts = line.split(" ");
            if (lineParts.length > 2) {
                path = lineParts[1];
            }
            else {
                throw new BadRequestException("Wrong HTTP start line");
            }
        }

        return path;
    }

    private static String readContent(String path) throws NotFoundException {
        if ("/".equals(path)) {
            return "<h1>Hello World!</h1>";
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
                                      String content) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(connection.getOutputStream()));

        writeContent(bufferedWriter, content, statusCode.getCode(), statusCode.getStatus());

        bufferedWriter.flush();
    }

    private static void writeContent(BufferedWriter bufferedWriter,
                                     String content,
                                     int code,
                                     String status) throws IOException {
        bufferedWriter.write("""
                HTTP/1.1 %d %s
                Content-Type: text/html; charset=UTF-8
                Content-Length: %d
                
                %s
                """.formatted(code, status, content.length(), content));
    }
}
