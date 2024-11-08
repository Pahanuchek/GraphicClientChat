package client;

import server.ServerWindow;

import javax.swing.*;
import java.awt.*;

public class LoginClientWindow extends JFrame {
    private static final int WINDOW_HEIGHT = 400;
    private static final int WINDOW_WIDTH = 300;
    private static final int WINDOW_POSX = 100;
    private static final int WINDOW_POSY = 150;

    private final JTextArea log = new JTextArea();
    private final JPanel panel = new JPanel(new GridLayout(8, 1));
    private final JLabel ipAddressLabel = new JLabel("IP address");
    private final JLabel portLabel = new JLabel("Port");
    private final JLabel loginLabel = new JLabel("Login");
    private final JLabel passwordLabel = new JLabel("Password");
    private final JTextField ipAddress = new JTextField("192.168.1.1");
    private final JTextField port = new JTextField("8080");
    private final JTextField login = new JTextField("");
    private final JPasswordField password = new JPasswordField("");
    private final JButton btnLogin = new JButton("Login");
    private final ChatClientWindow chatClientWindow = new ChatClientWindow();
    private final ServerWindow serverWindow;

    public LoginClientWindow(ServerWindow serverWindow) {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocation(WINDOW_POSX, WINDOW_POSY);
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        setTitle("Login");
        setResizable(false);
        this.serverWindow = serverWindow;

        panel.add(ipAddressLabel);
        panel.add(ipAddress);
        panel.add(portLabel);
        panel.add(port);
        panel.add(loginLabel);
        panel.add(login);
        panel.add(passwordLabel);
        panel.add(password);
        add(panel, BorderLayout.NORTH);
        add(log, BorderLayout.CENTER);
        add(btnLogin, BorderLayout.SOUTH);

        btnLogin.addActionListener(e -> login());

        setVisible(true);
    }

    public Client createClient() {
        if(login.getText().isEmpty()) {
            log.append("Не введен логин\n");
            return null;
        }
        if(password.getText().isEmpty()) {
            log.append("Не введен пароль\n");
            return null;
        }
        return new Client(login.getText(), password.getText());
    }

    public void login() {
        Client client = createClient();
        if(client != null && serverWindow.isServerWorking) {
            log.append("Вы успешно подключились!\n");
            serverWindow.log.append(client.getLogin() + " подключился к беседе.\n");
            chatClientWindow.setVisible(true);
            this.setVisible(false);
        } else {
            log.append("Сервер недоступен!\n");
        }
    }

}
