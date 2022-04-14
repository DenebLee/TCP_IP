package kr.nanoit.education.persist;

import java.util.concurrent.LinkedBlockingQueue;

public class DBSearching{

    LinkedBlockingQueue  linkedBlockingQueue;
    DataBaseInputData dataBaseInputData;

    public DBSearching(LinkedBlockingQueue linkedBlockingQueue, DataBaseInputData dataBaseInputData) {
        this.linkedBlockingQueue = linkedBlockingQueue;
        this.dataBaseInputData = dataBaseInputData;
    }

    public void Searching() {

        DataBaseOutPutData dataBaseOutPutData = new DataBaseOutPutData(dataBaseInputData, linkedBlockingQueue);
        Thread thread = new Thread(dataBaseOutPutData);
        thread.start();
    }
}
