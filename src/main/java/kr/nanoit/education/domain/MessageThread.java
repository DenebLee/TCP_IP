package kr.nanoit.education.domain;

import kr.nanoit.education.SMS;

import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;

public class MessageThread implements Runnable{

    LinkedBlockingQueue<List<SMS>> linkedBlockingQueue;
    LinkedBlockingQueue<byte[]> messageLinkedBlockingQueue;
    String id;
    String encryptpw;
    String enckey;

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