package kr.nanoit.education;

public class Test {

    class ConfigLoader {

    }

    public static void main(String[] args) {
        System.getProperties()
                .forEach((s, s2) -> System.out.printf("%s=%s\r\n", s, s2));
        System.out.println(System.getProperty("user.dir"));
    }
}

