package kr.nanoit.education.domain;

import java.io.OutputStream;
import java.net.Socket;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

public class MessageWriteTest implements Runnable {
    Socket socket;
    OutputStream os;
    LinkedBlockingQueue<byte[]> messageLinkedBlockingQueue;
    byte[] sendData;
    public MessageWriteTest(Socket socket, OutputStream os, LinkedBlockingQueue<byte[]> messageLinkedBlockingQueue){

        this.socket = socket;
        this.os = os;
        this.messageLinkedBlockingQueue = messageLinkedBlockingQueue;

    }

    @Override
    public void run(){
        while (true){
            try {
                sendData = messageLinkedBlockingQueue.poll(1000, TimeUnit.MILLISECONDS);

                if (sendData != null) {
                    Thread.sleep(1000);
                    System.out.println("쓰기");
                    System.out.println(new String(sendData));
                    os.write(sendData);
                }else{
                    System.out.println("사용할 수 있는 값 없음");
                }
            }catch (Exception e) {
                e.printStackTrace();
            }
    }


    }
}
