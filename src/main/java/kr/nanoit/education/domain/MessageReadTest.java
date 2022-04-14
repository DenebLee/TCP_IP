package kr.nanoit.education.domain;

import java.io.InputStream;
import java.net.Socket;
public class MessageReadTest implements Runnable {
    Socket socket;
    InputStream is;
    byte[] bytes = null;

    public MessageReadTest(java.net.Socket socket, InputStream is) {
        this.socket = socket;
        this.is = is;
    }

    @Override
    public void run() {
        while (true) {
            bytes = new byte[1024];
            try {
                Thread.sleep(1000);
                System.out.println("읽기");
                int readByteCount = is.read(bytes);
                String receiveData = new String(bytes, 0, readByteCount, "UTF-8");
                System.out.println(" Send 메시지, 데이터 Response 성공");

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
