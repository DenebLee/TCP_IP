package kr.nanoit.education.test;

import kr.nanoit.education.config.PropReader;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.HashMap;
import java.util.Properties;

import static javax.swing.text.html.HTML.Tag.P;


public class PropReaderTest {

    HashMap<String, String> map = new HashMap();

    @Test
    void Should_Read() {
        // given
        PropReader propReader = new PropReader();
        // when
        // then

    }


    public HashMap<String, String> test() {
        Properties ppt = new Properties();
        try {
            ppt.load(new FileInputStream("D:\\Java\\TCP_IP\\test.properties"));
        } catch (IOException e) {
            System.out.println("파일 불러오기 오류 -> " + e);
        }


        String url = ppt.getProperty("client.url");
        String pw = ppt.getProperty("client.id");
        String id = ppt.getProperty("client.password");
        String enckey = ppt.getProperty("client.enckey");
        map.put("url", url);
        map.put("pw", pw);
        map.put("id", id);
        map.put("enckey", enckey);

        return map;
    }

}
