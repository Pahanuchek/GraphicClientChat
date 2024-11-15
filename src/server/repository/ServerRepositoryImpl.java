package server.repository;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ServerRepositoryImpl implements ServerRepository {
    private static final String FILE_NAME = "../resource/chat.txt";

    @Override
    public void saveMessage(String message) {
        try (FileWriter writer = new FileWriter(FILE_NAME, true)) {
            writer.write(message + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<String> loadChatHistory() {
        List<String> chatHistory = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                chatHistory.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return chatHistory;
    }
}