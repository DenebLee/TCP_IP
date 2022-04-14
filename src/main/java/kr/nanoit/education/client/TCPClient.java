package kr.nanoit.education.client;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class TCPClient extends JFrame {
    public final static int SERVER_PORT = 9999;

    private Socket s;
    private JTextField messageField;
    private JTextArea messages;

    public TCPClient(){
        super(" TCP Client");
        messageField = new JTextField(40);
        messages = new JTextArea(10,50);

        messages.setBackground(Color.black);		//배경 검은색
        JScrollPane scrolledMessages = new JScrollPane(messages);	// 스크롤 가능하도록

        this.setLayout(new BorderLayout(10,10));
        this.add("North",messageField);
        this.add("Center",scrolledMessages);

        messages.setEnabled(false);		// TextArea disactive

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(620,400);
        this.setLocationRelativeTo(null);	//창 가운데 위치

        connectServer();

    }


    public void connectServer() {

        
        
        String serverIP = "192.168.0.48";

        try {
            Socket s = new Socket(serverIP,SERVER_PORT);
            messages.append("server port:"+ s.getPort()+", my port:"+ s.getLocalPort()+"\n");
            BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream()));
            messages.append(br.readLine());

            br.close();
            s.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String []ar) {
        new TCPClient();
    }

    }
