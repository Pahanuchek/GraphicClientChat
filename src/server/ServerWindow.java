package server;

import client.ChatClientWindow;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ServerWindow extends JFrame {
    private static final int WINDOW_HEIGHT = 400;
    private static final int WINDOW_WIDTH = 300;
    private static final int WINDOW_POSX = 500;
    private static final int WINDOW_POSY = 150;

    private final JButton btnStart = new JButton("Start");
    private final JButton btnStop = new JButton("Stop");

    private JTextArea log = new JTextArea();
    private  boolean isServerWorking;
    private final List<ChatClientWindow> chatClientWindowList = new ArrayList<>();
    private final List<String> chatHistory = new ArrayList<>();

    public boolean getIsServerWorking() {
        return isServerWorking;
    }

    public ServerWindow() {
        isServerWorking = false;

        setupWindow();
        setupComponents();

        btnStart.addActionListener(e -> startServer());
        btnStop.addActionListener(e -> stopServer());

        setVisible(true);
    }

    public void setupWindow() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocation(WINDOW_POSX, WINDOW_POSY);
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        setTitle("Sever");
        setResizable(false);
    }

    public void setupComponents() {
        JPanel jPanel = new JPanel(new GridLayout(1, 2));
        jPanel.add(btnStart);
        jPanel.add(btnStop);

        add(jPanel, BorderLayout.SOUTH);
        add(log);
    }

    public void startServer() {
        if(!isServerWorking) {
            addLog("Сервер запущен\n");
            isServerWorking = true;
            loadChatHistory();
        } else {
            addLog("Сервер уже запущен\n");
        }
    }

    public void stopServer() {
        if(isServerWorking) {
            addLog("Сервер остановлен\n");
            isServerWorking = false;
            chatClientWindowList.forEach(chatClientWindow -> {
                chatClientWindow.getLoginClientWindow().setVisible(true);
                chatClientWindow.dispose();
            });
        } else {
            addLog("Сервер не запущен\n");
        }
    }

    public void addLog(String log) {
        this.log.append(log);
    }

    public void addChatClient(ChatClientWindow chatClientWindow) {
        chatClientWindowList.add(chatClientWindow);
    }

    public void receiveMessage(String login, String message) {
        if (isServerWorking) {
            addLog(login + ": " + message + "\n");
            chatHistory.add(login + ": " + message + "\n");
            chatClientWindowList.forEach(client -> client.addMessage(login + ": " + message));
            saveMessageToFile(login + ": " + message);
        }
    }

    private void saveMessageToFile(String message) {
        try (FileWriter writer = new FileWriter("chat.txt", true)) {
            writer.write(message + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadChatHistory() {
        try (BufferedReader reader = new BufferedReader(new FileReader("chat.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                chatHistory.add(line);
                addLog(line + "\n");
            }
            chatClientWindowList.forEach(this::sendChatHistory);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendChatHistory(ChatClientWindow chatClientWindow) {
        chatHistory.forEach(chatClientWindow::addMessage);
    }
}
