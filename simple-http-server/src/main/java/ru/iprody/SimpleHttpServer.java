package ru.iprody;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Path;

public class SimpleHttpServer {

    private static final int PORT = 8080;
    private static final String STATIC_DIR = "simple-http-server/static";

    public static void main(String[] args) throws IOException {
        while (true) {
            try (ServerSocket serverSocket = new ServerSocket(PORT)) {
                System.out.println("Server started on http://localhost:8080");
                getConnection(serverSocket);
            }
        }
    }

    private static void getConnection(ServerSocket serverSocket) throws IOException {
        try (Socket connection = serverSocket.accept()) {
            String path = readRequest(connection);
            writeResponse(connection, path);
        }
    }

    private static String readRequest(Socket connection) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String line;
        String path = "";

        while ((line = bufferedReader.readLine()) != null && !line.isEmpty()) {
            if (line.startsWith("GET")) {
                String[] lineParts = line.split(" ");
                if (lineParts.length > 2) {
                    path = lineParts[1];
                }
            }
            System.out.println(line);
        }
        System.out.println();

        return path;
    }

    private static void writeResponse(Socket connection, String path) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(connection.getOutputStream()));

        String filePath = path.substring(1); // remove slash
        Path fullPath = Path.of(STATIC_DIR, filePath);

        if (Files.exists(fullPath) && !Files.isDirectory(fullPath)) {
            String content = Files.readString(fullPath);
            writeContent(bufferedWriter, content, 200, "OK");
        } else {
            String notFoundContent = "<h1>404 Not Found</h1>";
            writeContent(bufferedWriter, notFoundContent, 404, "Not Found");
        }

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
