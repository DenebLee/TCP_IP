package kr.nanoit.education.domain;

import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MessagePacketMaker {

    Map<String, String> message_data;
    String id;
    String password;

    public static final String TYPE_SEND_MESSAGE = "SEND";
    public static final int SIZE_HEADER_PACKET_TYPE = 10;
    public static final int SIZE_HEADER_BODY_LENGTH = 10;
    public static final int SIZE_SEND_MESSAGE_BODY_LENGTH = 437;
    public static final int SIZE_SEND_MESSAGE_BODY_MESSAGE_TYPE = 5;
    public static final int SIZE_SEND_MESSAGE_BODY_MESSAGE_ID = 20;
    public static final int SIZE_SEND_MESSAGE_BODY_RECEIBVE_NUMBER = 50;
    public static final int SIZE_SEND_MESSAGE_BODY_CALLBACK_NUMBER = 50;
    public static final int SIZE_SEND_MESSAGE_BODY_DATA_COUNT = 1;
    public static final int SIZE_SEND_MESSAGE_BODY_ATTACHMENT_DATA_TYPE = 1;
    public static final int SIZE_SEND_MESSAGE_BODY_ATTACHMENT_DATA_SIZE = 10;
    public static final int SIZE_SEND_MESSAGE_BODY_ATTACHMENT_DATA = 0;
    public static final int SIZE_SEND_MESSAGE_BODY_MSG = 200;
    public static final int SIZE_SEND_MESSAGE_BODY_ORG_CALLBACK_NUMBER = 50;
    public static final int SIZE_SEND_MESSAGE_BODY_BILL_ID = 50;
    public static final int SIZE_SEND_MESSAGE_FULL_SIZE = SIZE_HEADER_PACKET_TYPE + SIZE_HEADER_BODY_LENGTH + SIZE_SEND_MESSAGE_BODY_LENGTH; //457

    MessagePacketMaker(Map<String, String> message_data, String id, String encryptpw){
        this.message_data = message_data;
        this.id = id;
        this.password = encryptpw;
    }



    //패킷 만드는 요청을 받는 메소드들
    public byte[] messagePacket() {
        String message_type = message_data.get("message_type");
        String message_id = message_data.get("message_id");
        String receive_number = message_data.get("receive_number");
        String callback_number = message_data.get("callback_number");
        String msg = message_data.get("msg");
        String data_count = message_data.get("data_count");
        String attachment_data_type = message_data.get("attachment_data_type");
        String attachment_data_size = message_data.get("attachment_data_size");
        String attachment_data = message_data.get("attachment_data");
        String org_callback_number = message_data.get("org_callback_number");
        String bill_id = message_data.get("bill_id");

        return makeMessagePacket(message_type, message_id, receive_number, callback_number, msg, data_count, attachment_data_type, attachment_data_size, attachment_data, org_callback_number, bill_id).getBytes(StandardCharsets.UTF_8);
    }

    private String makeMessagePacket(String message_type, String message_id, String receive_number, String callback_number, String msg, String data_count, String attachment_data_type, String attachment_data_size, String attachment_data, String org_callback_number, String bill_id) {
//        System.out.println("전송 패킷 :" + makeMessagePacketHeader().concat(makeMessagePacketBody(message_type, message_id, receive_number, callback_number, msg, org_callback_number, bill_id)));
        return makeMessagePacketHeader().concat(makeMessagePacketBody(message_type, message_id, receive_number, callback_number, msg, data_count, attachment_data_type, attachment_data_size, attachment_data, org_callback_number, bill_id));//concat는 두개를 더할수 있는 메소드임( + 연산자는 보안에 위험하다고 함)
    }

    //LOGIN 패킷에 헤더 부분을 만드는 메소드
    private String makeMessagePacketHeader() {
        return stringAppendPadding(TYPE_SEND_MESSAGE, SIZE_HEADER_PACKET_TYPE).concat(stringAppendPadding(String.valueOf(SIZE_SEND_MESSAGE_BODY_LENGTH), SIZE_HEADER_BODY_LENGTH));
    }

    //LOGIN 패킷에 바디 부분을 만드는 메소드
    private String makeMessagePacketBody(String message_type, String message_id, String receive_number, String callback_number, String msg, String data_count, String attachment_data_type, String attachment_data_size, String attachment_data, String org_callback_number, String bill_id) {
        return stringAppendPadding(message_type,SIZE_SEND_MESSAGE_BODY_MESSAGE_TYPE).
                concat(stringAppendPadding(message_id, SIZE_SEND_MESSAGE_BODY_MESSAGE_ID).
                        concat(stringAppendPadding(receive_number, SIZE_SEND_MESSAGE_BODY_RECEIBVE_NUMBER).
                                concat(stringAppendPadding(callback_number, SIZE_SEND_MESSAGE_BODY_CALLBACK_NUMBER)).
                                concat(stringAppendPadding(msg, SIZE_SEND_MESSAGE_BODY_MSG)).
                                concat(stringAppendPadding(data_count, SIZE_SEND_MESSAGE_BODY_DATA_COUNT).
                                        concat(stringAppendPadding(attachment_data_type, SIZE_SEND_MESSAGE_BODY_ATTACHMENT_DATA_TYPE).
                                                concat(stringAppendPadding(attachment_data_size, SIZE_SEND_MESSAGE_BODY_ATTACHMENT_DATA_SIZE).
                                                        concat(stringAppendPadding(attachment_data, SIZE_SEND_MESSAGE_BODY_ATTACHMENT_DATA).
                                                                concat(stringAppendPadding(org_callback_number, SIZE_SEND_MESSAGE_BODY_ORG_CALLBACK_NUMBER).
                                                                        concat(stringAppendPadding(bill_id, SIZE_SEND_MESSAGE_BODY_BILL_ID)))))))));
    }

    //문자열의 길이가 FINAL로 선언된 Byte의 크기보다 작을때 나머지 공간을 띄워쓰기로 채우는 메소드
    private String stringAppendPadding(String appendString, int count) {
        return appendString.concat(Stream.generate(() -> " ").limit(count - appendString.length()).collect(Collectors.joining()));
    }

}