package server.repository;

import java.util.List;

public interface ServerRepository {
    void saveMessage(String message);
    List<String> loadChatHistory();
}
