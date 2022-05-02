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
                //offer 메서드는 큐가 가득차서 더이상 추가할 수 없는 경우 false를 반환하고
                //요소가 추가되면 true를 반환하며 특정 예외를 throw하지 않는다

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
