package server.gui;


import server.Server;
import client.ChatClientWindow;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class ServerGUIImpl extends JFrame implements ServerGUI {
    private static final int WINDOW_HEIGHT = 400;
    private static final int WINDOW_WIDTH = 300;
    private static final int WINDOW_POSX = 500;
    private static final int WINDOW_POSY = 150;

    private final JButton btnStart = new JButton("Start");
    private final JButton btnStop = new JButton("Stop");
    private final JTextArea log = new JTextArea();
    private final Server server;

    public ServerGUIImpl(Server server) {
        this.server = server;
        setupWindow();
        setupComponents();

        btnStart.addActionListener(e -> server.startServer());
        btnStop.addActionListener(e -> server.stopServer());

        setVisible(true);
    }

    private void setupWindow() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocation(WINDOW_POSX, WINDOW_POSY);
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        setTitle("Server");
        setResizable(false);
    }

    private void setupComponents() {
        JPanel jPanel = new JPanel(new GridLayout(1, 2));
        jPanel.add(btnStart);
        jPanel.add(btnStop);

        add(jPanel, BorderLayout.SOUTH);
        add(log);
    }

    @Override
    public void addLog(String message) {
        log.append(message);
    }

    @Override
    public void sendChatHistory() {
        server.getChatClientList().forEach(this::sendChatHistory);
    }

    private void sendChatHistory(ChatClientWindow chatClientWindow) {
        List<String> chatHistory = server.getServerRepository().loadChatHistory();
        chatHistory.forEach(chatClientWindow::addMessage);
    }
}
