package kr.nanoit.education.config;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

class HttpConnect {

//    given(준비): 어떠한 데이터가 준비되었을 때
//    when(실행): 어떠한 함수를 실행하면
//    then(검증): 어떠한 결과가 나와야 한다.


    // given

    // 기존 urlData 는 url파라미터 이므로 테스트시 url 직접적으로 던져주게 짜는게 맞는가?

    private String id = "test02";
    private String pw = "Y4dV4Jl9VdF7Ooum";
    private String totalURl =  UrlData.trim().toString() + "?" + ParamData.trim().toString();
    HttpURLConnection conn = null;
    String Testurl = "http://localhost:9080";
    URL url = null;




    // when


    HttpURLConnection  httpcon = new HttpURLConnection(String Testurl) {
        @Override
        public void disconnect() {
            System.out.println("연결 실패");
        }

        @Override
        public boolean usingProxy() {
            return false;
        }

        @Override
        public void connect() throws IOException {

        }
    }


    // then
    @Test
    @DisplayName("HTTP연결에 대한 테스트")
    void should_HttpConnect() throws IOException {
        url = new URL(Testurl); // url객체에 url 실질적 데이터 삽입
        conn = (HttpURLConnection) url.openConnection(); // 연결 객체 conn 생성
        conn.setRequestMethod("POST"); // 요청 방식 
        conn.connect();
        System.out.println("");
        System.out.println("");
        System.out.println("");
    }
}
