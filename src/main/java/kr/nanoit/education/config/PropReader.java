package kr.nanoit.education.config;

import java.io.*;
import java.util.HashMap;
import java.util.Properties;


// 특정 파일 있는지 없는지 체크
public class PropReader {

    public void read() {

    }

    HashMap<String, String> map = new HashMap();

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


        System.out.println("추출 데이터  : " + url + pw + id + enckey);

        return map;
    }


//        File path = new File("D:\\Java\\TCP_IP"); // 파일 경로 설정
//        final String search_fileName = "test"; // 검색에 필요한 파일 이름 담는 변수
//        String fileList[] = path.list(new FilenameFilter() {
//            @Override
//            public boolean accept(File dir, String name) {
//                return name.startsWith(search_fileName);
//            }
//        });
//
//        // 파일리스트 출력
//        if (fileList.length > 0) {
//            for (int i = 0; i < fileList.length; i++) {
//                System.out.println(fileList[i]);
//            }

    // 특정 파일 검색은 완료 다만 앞 test 이름을 단 파일은 전체 조회되도록 짜여져서 특정 파일 조회 될 수 있도록 수정 후
    // 해당 파일이 있는경우 파일 안 내용을 읽어와 값 추출

    // 설정 정보를 하드코딩 하지 않음 대신 설정 정보를 모아둔 문서를 외부에 ㅇ저장함으로 시스템 설정이나 버전 업글을 통한 설정값 변경이슈가 있을때
    // 재 컴파일을 하는것을 막는다

    // Properties 클래스는 기본적으로 지속적인 해시 테이블을 제공하며 그곳에서 모든 키와 값들은 스트링형태
    // 잠시 데이터를 가져오는 부분을 무시하고 key-value에 접근하는것은 해시 테이블에서의 작용하는 것과 유사하게 동작한다
    // -> Propertie 클래스는 해시 클래스의 서브 클래스 이기 때문
//
//            Properties properties = new Properties();
//            // Read properties file.
//            try {
//                properties.load(new FileInputStream("example01.properties"));
//            } catch (IOException e) {
//            }
//
//            String avalue = properties.getProperty("a");
//            System.out.println(avalue);
//            properties.setProperty("a", "properties test");
//
//            // Write properties file.
//            try {
//                properties.store(new FileOutputStream("example01.properties"), null);
//            } catch(IOException e) {
//            }
//        }
//        }
}
