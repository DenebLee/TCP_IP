package kr.nanoit.education.config;

import org.w3c.dom.Document; //HTML 문서 또는 XML 문서 전체를 나타냄
import org.w3c.dom.Element; // HTML 문서 또는 XML 문서내의 요소
import org.w3c.dom.Node; // XML 문서내의 태그 요소
import org.w3c.dom.NodeList; // list로 노트 저장
import org.xml.sax.InputSource; //엔티티의  단일의 입력 소스 -> SAX 어플리케이션은 이 클래스를 이용해 입력 소스에 관한 정보를 단일의 객체에 캡슐화
import org.xml.sax.SAXException;

// 직렬화 : 객체를 직렬화하여 전송 가능한 형태로 만드는 것을 의미 객체들의 데이터를 연속적인 데이터로 변형하여 Stream을 통해 데이터를 읽도록 한다
// 주로 객체들을 통째로 파일로 저장하거나 전송하고 싶을 때 주로 사용

// 역직렬화 : 직렬화된 파일등을 역으로 직렬화하여 다시 객체의 형태로 만드는 것을 의미
// 저장된 파일을 읽거나 전송된 스트림 데이터를 읽어 원래 객체의 형태로 복원

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.StringReader;
import java.util.HashMap;
import java.util.Map;

public class XmlParser {
    DocumentBuilderFactory documentBuilderFactory = null;
    DocumentBuilder documentBuilder = null;
    Document document = null;
    Element serverInfo = null;
    Node severInfoChildNodes = null;
    NodeList List = null;
    Map<String, String> parsedData = new HashMap<>();

    // doc.getDocumentElement().getNodeName())를 출력하면 위 XML의 최상위 tag값을 가져온다
    // 파싱할 데이터는 <baseinfo> tag안에 있음 여기로 접근해야됨
    // 하나의 xml태그가 하나의 node객체에 담김



    String xmlString = "";
    public XmlParser(String xmlString){
        this.xmlString = xmlString;
    }

    // Map은 키와 값으로 구성된 Entry객체를 저장하는 구조를 가지고 있는 자료구조, 키와 값은 모두 객체
    // 값은 중복 저장될 수 있지만 키는 중복저장 x
    // Hashmap은 이름 그래도 해싱을 사용하기에 많은 양의 데이터를 검색하는데 있어 뛰어난 성능가짐
    // 복습 ; 해싱이란 어떤 항목의 탐색 키만을 가지고 바로 항목이 저장되어 있는 배열의 인덱스를 결정하는 기법

    public Map<String, String> parsing() throws ParserConfigurationException, IOException, SAXException {

        // 자신의 static 메서드를 가지고 객체를 생성 : 싱글톤 패턴
        documentBuilderFactory = DocumentBuilderFactory.newInstance();
        // 다른 클래스의 객체를 가지고, 객체를 생성하면 팩토리 패턴.
        documentBuilder = documentBuilderFactory.newDocumentBuilder();
        document = documentBuilder.parse(new InputSource(new StringReader(xmlString)));
        //팩 토리 메서드 패턴  공장에서 찍어줌
        List = document.getElementsByTagName("IP");



        serverInfo = document.getDocumentElement(); // xml을 메모리에 펼쳐놓고 루트를 elemnt에 저장
        System.out.println(serverInfo.getNodeName()); // < ServerInfo가 찍힘
        severInfoChildNodes = (Node) serverInfo.getChildNodes(); // ServerInfo의 자식 요소들을 담음 (IP, PORT)
        List = severInfoChildNodes.getChildNodes();
        // 파싱할 태그의 리스트를 찾아온다
        // tmx 태그 전체를 list에 저장
        //


        for (int i=0; i < List.getLength(); i++){
            Node item = List.item(i);
            if(item.getNodeType() == Node.ELEMENT_NODE){ // 이 조건식을 넣는 이유는 XML 파일의 빈 공백도 Node로 인식하기 때문에 노드의 타입이 Element일 경우(공백이 아닌 경우)에만 가져가겠다는 조건
                //
                System.out.println("Key : " + item.getNodeName());//Key
                System.out.println("value : " + item.getTextContent());//value
                parsedData.put(item.getNodeName(), item.getTextContent()); //결과값을 return해주기 위해 Map으로 만들기


            }
        }

        return parsedData;
    }
}