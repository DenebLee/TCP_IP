package kr.nanoit.education.domain;

import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;

public class MessageThread implements Runnable{
    //

    LinkedBlockingQueue<List<SMS>> linkedBlockingQueue;
    LinkedBlockingQueue<byte[]> messageLinkedBlockingQueue;
    String id;
    String encryptpw;
    String enckey;


    // LinkedBlockingQueue에 데이터를 넣고 하나씩 순차적으로 뺄때 멀티작업 효율을 위해 thread사용

    public MessageThread(LinkedBlockingQueue linkedBlockingQueue, LinkedBlockingQueue messageLinkedBlockingQueue, String id, String encryptpw, String encKey){


        this.linkedBlockingQueue = linkedBlockingQueue;
        this.messageLinkedBlockingQueue = messageLinkedBlockingQueue;
        this.id = id;
        this.encryptpw = encryptpw;
        this.enckey = encKey;

    }
    @Override
    public void run() {
        while (true){
            try {
                MessageListGetData messageListGetData = new MessageListGetData(linkedBlockingQueue, messageLinkedBlockingQueue, id, encryptpw);
                messageListGetData.GetData(enckey);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}