package kr.nanoit.education.config;



import static org.assertj.core.api.Assertions.*;

import java.io.*;
import java.util.Properties;
import org.junit.After;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

class PropertiesReaderTest {


    /*
    given(준비): 어떠한 데이터가 준비되었을 때
    when(실행): 어떠한 함수를 실행하면
    then(검증): 어떠한 결과가 나와야 한다.
    */

    @TempDir
    private File tempDir;

    @Test
    @DisplayName("프로퍼티스 파일을 읽는다.")
    void Should_Read() throws IOException {
        // given expected
        Properties expected = new Properties();
        expected.setProperty("id", "whwjdgk");
        expected.setProperty("encryptKey", "aslkdjflkasjvlksdajlkf");

        // given file
        File expectedFile = create(tempDir, "test.properties", "id=whwjdgk\r\nencryptKey=aslkdjflkasjvlksdajlkf");

        // when
        Properties actual = PropertiesReader.read(expectedFile.getPath());

        // then
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    @DisplayName("프로퍼티스 파일에 id에 값이 없을경우 읽기가 실패한다.")
    void Should_Read_Fail() throws IOException {
        // given expected
        Properties expected = new Properties();
        expected.setProperty("id", "whwjdgk");

        // given file
        File expectedFile = create(tempDir, "test.properties", "id=");

        // when, then
        assertThatThrownBy(() -> PropertiesReader.read(expectedFile.getPath()))
                .isInstanceOf(IOException.class);
    }

    @Test
    @DisplayName("프로퍼티스 파일에 encryptKey에 값이 없을경우 읽기가 실패한다.")
    void Should_Read_Fail_2() throws IOException {
        // given expected
        Properties expected = new Properties();
        expected.setProperty("id", "whwjdgk");
        expected.setProperty("encryptKey", "asjdflkjasdclvkjsadlkf");

        // given file
        File expectedFile = create(tempDir, "test.properties", "id=whwjdgk\r\nencryptKey=");
        String id = expected.getProperty("id");
        System.out.println("아이디값 가져오기 : "+id);
        // when, then
        assertThatThrownBy(() -> PropertiesReader.read(expectedFile.getPath()))
                .isInstanceOf(IOException.class);
    }

    @Test
    @DisplayName("프로퍼티스 파일이 맞는지 테스트한다.")
    void Is_Properties_File() throws IOException {
        // given expected
        Properties expected = new Properties();
        expected.setProperty("test.test", "testproperties");
        expected.setProperty("test.test.2", "test2");


        // given file
        File expectedFile = create(tempDir, "test.properties", "sadflksdjvlkwjelkrjlkew");

        // when, then
        assertThatThrownBy(() -> PropertiesReader.read(expectedFile.getPath()))
                .isInstanceOf(IOException.class);
    }

    @Test
    @DisplayName("프토퍼티 값 가져오기 테스트")
    void GetProperties() throws IOException {
        // given expected
        Properties expected = new Properties();
        String propFileName = "test.properties";

        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);

        if (inputStream != null) {
            expected.load(inputStream);
        }else{
            throw new FileNotFoundException("property file'" + propFileName + "'not found in the classpath");
        }
        String id = expected.getProperty("client.id");
        System.out.println(id);

    }

    public File create(File directory, String name, String body) throws IOException {
        File file = new File(directory, name);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            writer.write(body);
        }
        return file;
    }
}