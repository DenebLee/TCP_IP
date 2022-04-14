package kr.nanoit.education.domain;

import java.io.InputStream;
import java.net.Socket;

public class LoginListener {

    private Socket socket;
    private InputStream is;
    byte[] bytes = null;
    private String receiveData = null;

    public LoginListener(Socket socket, InputStream is) {
        this.is = is;
        this.socket = socket;
    }

    public String LoginDataRead() {
        try {
            bytes = new byte[1024];
            int readByteCount = is.read(bytes);
            receiveData = new String(bytes, 0, readByteCount, "UTF-8");
            System.out.println("[데이터받음]" + receiveData);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return receiveData;
    }
}
