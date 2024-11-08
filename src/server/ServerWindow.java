package server;

import javax.swing.*;
import java.awt.*;

public class ServerWindow extends JFrame {
    private static final int WINDOW_HEIGHT = 400;
    private static final int WINDOW_WIDTH = 300;
    private static final int WINDOW_POSX = 500;
    private static final int WINDOW_POSY = 150;

    private final JButton btnStart = new JButton("Start");
    private final JButton btnStop = new JButton("Stop");
    public JTextArea log = new JTextArea();
    public boolean isServerWorking;
    public ServerWindow() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocation(WINDOW_POSX, WINDOW_POSY);
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        setTitle("Sever");
        setResizable(false);
        isServerWorking = false;

        btnStart.addActionListener(e -> startServer());
        btnStop.addActionListener(e -> stopServer());


        JPanel jPanel = new JPanel(new GridLayout(1, 2));
        jPanel.add(btnStart);
        jPanel.add(btnStop);
        add(jPanel, BorderLayout.SOUTH);
        add(log);
        setVisible(true);
    }


    public void startServer() {
        if(!isServerWorking) {
            log.append("Сервер запущен\n");
            isServerWorking = true;
        } else {
            log.append("Сервер уже запущен\n");
        }
    }

    public void stopServer() {
        if(isServerWorking) {
            log.append("Сервер остановлен\n");
            isServerWorking = false;
        } else {
            log.append("Сервер не запущен\n");
        }
    }
}
