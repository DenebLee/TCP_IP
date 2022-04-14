package kr.nanoit.education.domain;

import java.io.OutputStream;
import java.net.Socket;

public class LoginWriter {

    private Socket socket;
    private OutputStream os;
    private String id;
    private String encryptpw;
    private String version;

    public LoginWriter(String id, String encryptpw, String version, Socket socket, OutputStream os) {
        this.id = id;
        this.encryptpw = encryptpw;
        this.version = version;
        this.socket = socket;
        this.os = os;
    }
    public void LoginDataSend() {
        try {
            LoginPacket loginPacket = new LoginPacket();
            byte[] dataSend = loginPacket.login(id, encryptpw, version);
            os.write(dataSend);
            System.out.println(dataSend);
            System.out.println("Client 인증 데이터 Request 성공 [LoginWriter]");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    // LoginPacket에서 생성한 메소드를 가져와 사용
}
