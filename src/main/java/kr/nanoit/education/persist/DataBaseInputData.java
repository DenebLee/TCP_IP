package kr.nanoit.education.persist;

import kr.nanoit.education.domain.SMS;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DataBaseInputData {

    private final AtomicInteger pk;
    public DataBaseInputData(){
        this.pk = new AtomicInteger(0);
    }

    public List<SMS> InputData() {
        return Stream.generate(() -> new SMS("SMS",this.pk, "01011112222","154495959","This is Content",
                "","","","","","")).limit(2).collect(Collectors.toList());
    }
}
