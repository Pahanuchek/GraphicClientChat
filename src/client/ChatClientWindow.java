package client;

import server.ServerWindow;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;

public class ChatClientWindow extends JFrame {
    private static final int WINDOW_HEIGHT = 400;
    private static final int WINDOW_WIDTH = 300;
    private static final int WINDOW_POSX = 100;
    private static final int WINDOW_POSY = 150;
    private static final JPanel panel = new JPanel();
    private final JTextArea text = new JTextArea();
    private final JTextArea message  =new JTextArea();
    private final JButton btnSend = new JButton("Send");
    private final Client client;
    private final ServerWindow serverWindow;
    private final LoginClientWindow loginClientWindow;

    public LoginClientWindow getLoginClientWindow() {
        return loginClientWindow;
    }

    public ChatClientWindow(Client client, ServerWindow serverWindow, LoginClientWindow loginClientWindow) {

        this.client = client;
        this.serverWindow = serverWindow;
        this.loginClientWindow = loginClientWindow;

        setupWindow();
        setupComponents();

        btnSend.addActionListener(e -> sendMessage());

        setVisible(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    public void setupWindow() {
        setLocation(WINDOW_POSX, WINDOW_POSY);
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        setTitle("Chat Client - " + client.getLogin());
        setResizable(false);
    }

    public void setupComponents() {
        text.setEditable(false);
        text.setLineWrap(true);
        text.setWrapStyleWord(true);

        message.setBorder(new BevelBorder(BevelBorder.LOWERED));
        message.setRows(3);
        message.setColumns(15);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.add(message, BorderLayout.CENTER);
        panel.add(btnSend, BorderLayout.EAST);

        add(new JScrollPane(text), BorderLayout.CENTER);
        add(panel, BorderLayout.SOUTH);
    }

    private void sendMessage() {
        String textMessage = message.getText().trim();
        String login = client.getLogin();
        if (!textMessage.isEmpty()) {
            serverWindow.receiveMessage(login, textMessage);
            message.setText("");
        }
    }

    public void addMessage(String textMessage) {
        text.append(textMessage + "\n");
        text.setCaretPosition(text.getDocument().getLength());
    }

    public void appendText(String text) {
        this.text.append(text + "\n\n");
    }
}
