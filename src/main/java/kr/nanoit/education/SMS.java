package kr.nanoit.education;

import java.util.concurrent.atomic.AtomicInteger;

public class SMS {

    private String message_type;            //메세지 종류 : SMS,LMS,MMS
    private AtomicInteger message_id;              //고객사의 message 고유 id
    private String receive_number;          //수신자 전화번호(숫자만)
    private String callback_number;         //발신자 전화번호(숫자만)
    private String msg;                     //메세지 내용
    private String data_count;              //
    private String attachment_data_type;    //
    private String attachment_data_size;    //
    private String attachment_data;         //
    private String org_callback_number;     //원 발신자 전화번호(숫자만) 특수사항이며, 업는경우 공백
    private String bill_id;                 //개별 과금 ID 특수사항이며, 없는경우 공백
    public SMS(String message_type, AtomicInteger message_id, String receive_number, String callback_number, String msg, String data_count, String attachment_data_type, String attachment_data_size, String attachment_data,  String org_callback_number, String bill_id){
        this.message_type = message_type;
        this.message_id = message_id;
        this.receive_number = receive_number;
        this.callback_number = callback_number;
        this.msg = msg;
        this.data_count = data_count;
        this.attachment_data_type = attachment_data_type;
        this.attachment_data_size = attachment_data_size;
        this.attachment_data = attachment_data;
        this.org_callback_number = org_callback_number;
        this.bill_id = bill_id;

    }

    public String getMessage_type() {
        return message_type;
    }

    public AtomicInteger getMessage_id() {
        return message_id;
    }

    public String getReceive_number() {
        return receive_number;
    }

    public String getCallback_number() {
        return callback_number;
    }

    public String getMsg() {
        return msg;
    }

    public String getData_count() {
        return data_count;
    }

    public String getAttachment_data_type() {
        return attachment_data_type;
    }

    public String getAttachment_data_size() {
        return attachment_data_size;
    }

    public String getAttachment_data() {
        return attachment_data;
    }

    public String getOrg_callback_number() {
        return org_callback_number;
    }

    public String getBill_id() {
        return bill_id;
    }


    public void setMessage_type(String message_type) {
        this.message_type = message_type;
    }

    public void setMessage_id(AtomicInteger message_id) {
        this.message_id = message_id;
    }

    public void setReceive_number(String receive_number) {
        this.receive_number = receive_number;
    }

    public void setCallback_number(String callback_number) {
        this.callback_number = callback_number;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setData_count(String data_count) {
        this.data_count = data_count;
    }

    public void setAttachment_data_type(String attachment_data_type) {
        this.attachment_data_type = attachment_data_type;
    }

    public void setAttachment_data_size(String attachment_data_size) {
        this.attachment_data_size = attachment_data_size;
    }

    public void setAttachment_data(String attachment_data) {
        this.attachment_data = attachment_data;
    }

    public void setOrg_callback_number(String org_callback_number) {
        this.org_callback_number = org_callback_number;
    }

    public void setBill_id(String bill_id) {
        this.bill_id = bill_id;
    }
}