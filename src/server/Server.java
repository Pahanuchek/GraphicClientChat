package server;

import client.ChatClientWindow;
import server.gui.ServerGUI;
import server.gui.ServerGUIImpl;
import server.repository.ServerRepository;
import server.repository.ServerRepositoryImpl;

import java.util.ArrayList;
import java.util.List;

public class Server {
    private boolean isServerWorking;
    private final ServerGUI serverGUI;
    private final ServerRepository serverRepository;
    private final List<ChatClientWindow> chatClientList = new ArrayList<>();

    public Server() {
        this.serverGUI = new ServerGUIImpl(this);
        this.serverRepository = new ServerRepositoryImpl();
        this.isServerWorking = false;
    }

    public boolean isServerWorking() {
        return isServerWorking;
    }

    public void startServer() {
        if (!isServerWorking) {
            serverGUI.addLog("Сервер запущен\n");
            isServerWorking = true;
            serverRepository.loadChatHistory().forEach(serverGUI::addLog);
            serverGUI.sendChatHistory();
        } else {
            serverGUI.addLog("Сервер уже запущен\n");
        }
    }

    public void stopServer() {
        if (isServerWorking) {
            serverGUI.addLog("Сервер остановлен\n");
            isServerWorking = false;
            chatClientList.forEach(chatClientWindow -> {
                chatClientWindow.getLoginClientWindow().setVisible(true);
                chatClientWindow.dispose();
            });
        } else {
            serverGUI.addLog("Сервер не запущен\n");
        }
    }

    public void addChatClient(ChatClientWindow chatClientWindow) {
        chatClientList.add(chatClientWindow);
    }

    public void receiveMessage(String login, String message) {
        if (isServerWorking) {
            String fullMessage = login + ": " + message;
            serverGUI.addLog(fullMessage + "\n");
            chatClientList.forEach(client -> client.addMessage(fullMessage));
            serverRepository.saveMessage(fullMessage);
        }
    }

    public ServerRepository getServerRepository() {
        return serverRepository;
    }

    public List<ChatClientWindow> getChatClientList() {
        return chatClientList;
    }
}