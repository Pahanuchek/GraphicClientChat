package client.login.server;


import client.Client;

public interface ServerLogic {
    boolean isServerWorking();
    void addClientToServer(Client client);
    void sendChatHistoryToClient(String login);
    void logMessage(String message);
}