package kr.nanoit.education.persist;

import kr.nanoit.education.domain.SMS;

import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;

public class DataBaseOutPutData implements Runnable{

    private DataBaseInputData dataBaseInputData;
    private LinkedBlockingQueue linkedBlockingQueue;

    public DataBaseOutPutData(DataBaseInputData dataBaseInputData, LinkedBlockingQueue linkedBlockingQueue) {
        this.dataBaseInputData = dataBaseInputData;
        this.linkedBlockingQueue = linkedBlockingQueue;
    }

    @Override
    public void run() {
        while (true) {

            List<SMS> messageList = null;

            try {
                Thread.sleep(1000);
                messageList = dataBaseInputData.InputData();

                linkedBlockingQueue.offer(messageList);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
