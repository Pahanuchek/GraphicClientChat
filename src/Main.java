
import client.LoginClientWindow;
import server.ServerWindow;


public class Main {
    public static void main(String[] args) {
        ServerWindow serverWindow = new ServerWindow();
        new LoginClientWindow(serverWindow);
        new LoginClientWindow(serverWindow);
    }
}