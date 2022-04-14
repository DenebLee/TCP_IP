package kr.nanoit.education.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpConnection {

    private String ParamData;
    private String  UrlData;

    public HttpConnection(String url, String data) {

        this.UrlData = url;
        this.ParamData = data;
    }

    public String Connection() {
        String totalUrl = "";
        if(ParamData != null && ParamData.length() > 0 &&
                !ParamData.equals("") && !ParamData.contains("null")) { //파라미터 값이 널값이 아닌지 확인
            totalUrl = UrlData.trim().toString() + "?" + ParamData.trim().toString(); //쿼리스트링으로 넣을 값
        }
        else {
            totalUrl = UrlData.trim().toString(); // 문자열 앞뒤 공백처리
        }
        //http 통신을 하기위한 객체 선언 실시
        URL url = null;
        HttpURLConnection conn = null;

        //http 통신 요청 후 응답 받은 데이터를 담기 위한 변수
        String responseData = "";
        BufferedReader br = null;
        StringBuffer sb = null;

        //메소드 호출 결과값을 반환하기 위한 변수
        String returnData = "";


        try {
            //파라미터로 들어온 url을 사용해 connection 실시
            url = new URL(totalUrl);
            conn = (HttpURLConnection) url.openConnection();


            // POST형식으로 요청
            conn.setRequestMethod("POST");

            //http 요청 실시
            conn.connect();
            System.out.println("[http 요청 방식] : "+"POST");
            System.out.println("[http 요청 주소] : "+UrlData);
            System.out.println("[http 요청 데이터] : "+ParamData);
            System.out.println("");

            //http 요청 후 응답 받은 데이터를 버퍼에 쌓는다
            br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
            sb = new StringBuffer();
            while ((responseData = br.readLine()) != null) {
                sb.append(responseData); //StringBuffer에 응답받은 데이터 순차적으로 저장 실시
            }

            //메소드 호출 완료 시 반환하는 변수에 버퍼 데이터 삽입 실시
            returnData = sb.toString();

            //http 요청 응답 코드 확인 실시
            String responseCode = String.valueOf(conn.getResponseCode());
            System.out.println("[http 응답 코드] : "+responseCode);
            System.out.println("[http 응답 데이터] : "+returnData);





        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //http 요청 및 응답 완료 후 BufferedReader를 닫아줍니다
            try {
                if (br != null) {
                    br.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return returnData;
    }
}
