package kr.nanoit.education.domain;


import kr.nanoit.education.SMS;
import kr.nanoit.education.util.Encryption;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

public class MessageListGetData {

    LinkedBlockingQueue<List<SMS>> linkedBlockingQueue;
    LinkedBlockingQueue<byte[]> messageLinkedBlockingQueue;
    String id;
    String encryptpw;
    List<SMS> messageList;
    Map<String, String> map = new HashMap<String, String>();
    SMS message;
    MessageListGetData(LinkedBlockingQueue linkedBlockingQueue, LinkedBlockingQueue messageLinkedBlockingQueue, String id, String encryptpw){
        this.linkedBlockingQueue = linkedBlockingQueue;
        this.id = id;
        this.encryptpw = encryptpw;
        this.messageLinkedBlockingQueue = messageLinkedBlockingQueue;
    }

    public void GetData(String enckey){

        try {
            messageList = linkedBlockingQueue.poll(1000,TimeUnit.MILLISECONDS);
            if(messageList != null && !messageList.isEmpty()){

                for(int i = 0; i < messageList.size(); i++){

                    message = null;
                    message  = messageList.get(i);

                    String message_type = message.getMessage_type();
                    String message_id = message.getMessage_id().toString();
                    String receive_number = message.getReceive_number();
                    String callback_number = message.getCallback_number();
                    String msg = message.getMsg();
                    String data_count = message.getData_count();
                    String attachment_data_type = message.getAttachment_data_type();
                    String attachment_data_size = message.getAttachment_data_size();
                    String attachment_data = message.getAttachment_data();
                    String org_callback_number = message.getOrg_callback_number();
                    String bill_id = message.getBill_id();

                    map.put("message_type", message_type);
                    map.put("message_id", message_id);
                    map.put("receive_number", receive_number);
                    map.put("callback_number", callback_number);
                    map.put("msg", msg);
                    map.put("data_count", data_count);
                    map.put("attachment_data_type", attachment_data_type);
                    map.put("attachment_data_size", attachment_data_size);
                    map.put("attachment_data", attachment_data);
                    map.put("org_callback_number", org_callback_number);
                    map.put("bill_id", bill_id);

                    if(map != null && !map.isEmpty()){

                        Encryption encryption = new Encryption(enckey);
                        encryption.initialize();

                        map.replace("receive_number", encryption.MessageDataEncrypt(map.get("receive_number")));                           //receive_number 암호화
                        map.replace("callback_number", encryption.MessageDataEncrypt(map.get("callback_number")));                         //callback_number 암호화
                        map.replace("msg", encryption.MessageDataEncrypt(map.get("msg")));                                                 //msg 암호화
                        map.replace("org_callback_number", encryption.MessageDataEncrypt(map.get("org_callback_number")));                 //org_callback_number 암호화
                        map.replace("bill_id", encryption.MessageDataEncrypt(map.get("bill_id")));                                         //bill_id 암호화

                        MessagePacketMaker messagePacketMaker = new MessagePacketMaker(map, id, encryptpw);
                        byte[] sendData = messagePacketMaker.messagePacket();

                        messageLinkedBlockingQueue.offer(sendData);
//                        messageLinkedBlockingQueue.poll(1000, TimeUnit.MILLISECONDS);
                    }
                }
            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}