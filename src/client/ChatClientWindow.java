package client;

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
    private final JTextArea message = new JTextArea("");
    private final JButton btnSend = new JButton("Send");

    public ChatClientWindow() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocation(WINDOW_POSX, WINDOW_POSY);
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        setTitle("Chat Client");
        setResizable(false);
        message.setBorder(new BevelBorder(0, Color.BLACK, Color.GRAY));
        message.setRows(3);
        message.setColumns(20);

        panel.add(message, BorderLayout.NORTH);
        panel.add(btnSend, BorderLayout.SOUTH);

        add(text, BorderLayout.CENTER);
        add(panel, BorderLayout.SOUTH);
    }
}
