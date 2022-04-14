package kr.nanoit.education.main;

import kr.nanoit.education.HttpConnection;
import kr.nanoit.education.XmlParser;
import kr.nanoit.education.domain.*;
import kr.nanoit.education.persist.DBSearching;
import kr.nanoit.education.persist.DataBaseInputData;
import kr.nanoit.education.util.Encryption;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.LinkedBlockingQueue;

public class Main {

    public static  void main(String[] args) throws UnsupportedEncodingException {
//
//        String resource = "test.properties";
//        Properties properties = new Properties();
        // 초기화
//        String url = "http://ntmdist.nanoit.kr";
//        String id = "ascagent01";
//        String pw = "yApdI4xWI2MKGM3";

        String url = "http://localhost:9080";
        String id = "test02";
        String pw = "Y4dV4Jl9VdF7Ooum";
        String enckey = "0iiyV0XbWaECjyotN4iWe";
        String encryptpw = "";
        String returnData = "";
        String version = "test";
        Map<String, String> parsedResult;
        String IP = "";
        String PORT = "";
        Socket socket;
        LinkedBlockingQueue linkedBlockingQueue;
        LinkedBlockingQueue<byte[]> messageLinkedBlockingQueue;

        Encryption encryption = new Encryption(enckey);
        encryption.initialize();
        try {
            encryptpw = encryption.DataEncrypt(pw);
            System.out.println("[ 암호화된 비밀번호 ]  " + encryptpw);
        }catch (Exception e){
            System.out.println("[ 비밀번호 암호화 실패 ]");
            System.out.println("DataEncrypt Exception Err : " + e);
        }

        // param으로 넣어줄 id & pw 값을 data로 묶기
        //URLEncoder.encode(encryptpw, StandardCharsets.UTF_8.toString());
        String data = "id=" + id + "&" + "password=" + URLEncoder.encode(encryptpw, StandardCharsets.UTF_8.toString());

        HttpConnection connection = new HttpConnection(url, data);
        returnData =  connection.Connection();

        System.out.println();
        System.out.println("[Xml file Parsing]");
        System.out.println(returnData);
        XmlParser xmlParser = new XmlParser(returnData);
        parsedResult = new HashMap<>();
        try {
            parsedResult = xmlParser.parsing();
        } catch (Exception e) {
            System.out.println("Parsing Exception Err : " + e);
            e.printStackTrace();
        }

        // 해쉬맵에서 키값을 통해 value값 가져오기 -> socket 통신시 ip와 port 값으로 사용
        // Ip값 Port 값 추출해서 socket에 넘기기
        IP = (String) parsedResult.get("ip");
        System.out.println("");
        System.out.println("[ 추출된 IP ] = "+ IP);
        PORT = (String) parsedResult.get("port");
        System.out.println();
        System.out.println("[ 추출된 Port ] = " + PORT);

        // Data Select ===================================================================================================================
        DataBaseInputData dataBaseInputData = new DataBaseInputData();
        linkedBlockingQueue = new LinkedBlockingQueue(1024);
        DBSearching dbSearching = new DBSearching(linkedBlockingQueue, dataBaseInputData);
        dbSearching.Searching();
        // Data Select ===================================================================================================================


        // Message 암호화 및 패킷 생성 ======================================================================================================
        /*
        LinkedBlockingQueue
        - 선택적으로 Bound가 가능한 Linked list로 구현한 Queue
        - capacity를 초기에 정해주지 않는경우 integer.MAX_VALUE로 자동설정
        - 용량을 초과하지 않는 한에서 node는 동적으로 삽입시마다 생성되며 초과 시 block.
        - Linked queue는 일반적으로 배열 기반 큐 보다 동시성 App에서 높은 throughput을 가짐.
         */
        messageLinkedBlockingQueue = new LinkedBlockingQueue(1024);
        MessageThread messageThread = new MessageThread(linkedBlockingQueue, messageLinkedBlockingQueue, id, encryptpw, enckey);
        Thread thread0 = new Thread(messageThread);
        thread0.start();
//        // Message 암호화 및 패킷 생성 =====================================================================================================
//
//
//        // TCP 소켓 통신 ===================================================================================================================
        System.out.println();
        System.out.println();
        System.out.println("TCP 소켓 통신");
        System.out.println("Client 인증");
//
        try {
            System.out.println("TCP 통신 시도");
            SocketAddress socketAddress = new InetSocketAddress(IP, Integer.parseInt(PORT));
            socket = new Socket();
            socket.connect(socketAddress, 60000);
            OutputStream os = socket.getOutputStream();
            InputStream is  = socket.getInputStream();


            LoginWriter loginWriter = new LoginWriter(id, encryptpw, version, socket, os);
            loginWriter.LoginDataSend();

            LoginListener loginReader = new LoginListener(socket, is);
            loginReader.LoginDataRead();

            MessageWriteTest writeThreading = new MessageWriteTest(socket, os, messageLinkedBlockingQueue);
            Thread thread1 = new Thread(writeThreading);
            thread1.start();

            MessageReadTest readThreading = new MessageReadTest(socket, is);
            Thread thread2 = new Thread(readThreading);
            thread2.start();

        } catch (Exception e) {
            System.out.println(e + " => TCP Socket Connect fail");
            e.printStackTrace();
        }
//        // TCP 소켓 통신 ===================================================================================================================
//
//    }
        // socket 연결완료
    }}
