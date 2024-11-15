package client.login.server;

import client.Client;
import client.ChatClientWindow;
import server.Server;
import server.gui.ServerGUI;

public class LoginServerLogic implements ServerLogic {
    private final Server server;
    private final ServerGUI serverGUI;

    public LoginServerLogic(Server server, ServerGUI serverGUI) {
        this.server = server;
        this.serverGUI = serverGUI;
    }

    @Override
    public boolean isServerWorking() {
        return server.isServerWorking();
    }

    @Override
    public void addClientToServer(Client client) {
        ChatClientWindow chatClientWindow = new ChatClientWindow(client, server, serverGUI);
        server.addChatClient(chatClientWindow);
        server.logMessage(client.getLogin() + " подключился к беседе.");
        chatClientWindow.appendText(client.getLogin() + " вы успешно подключились!");
        server.sendChatHistory(chatClientWindow);
    }

    @Override
    public void logMessage(String message) {
        server.logMessage(message);
    }
}