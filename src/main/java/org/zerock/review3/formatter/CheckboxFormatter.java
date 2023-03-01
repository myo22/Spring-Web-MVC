package org.zerock.review3.formatter;
import org.springframework.format.Formatter;

import java.text.ParseException;
import java.util.Locale;

public class CheckboxFormatter implements Formatter<Boolean> { //브라우저에선 체크박스가 클릭된 상태일때 전송되는 값은 'on'이라는 값을 전달하기 때문에
    //TodoDTO로 데이터를 수집할때 문자열 'on'을 boolean 타입으로 처리할 수 있어야 하므로 컨트롤러에서 데이터를 수집할때 타입을 변경해 주기 위해 이것을 개발한것이다.

    @Override
    public Boolean parse(String text, Locale locale) throws ParseException {
        if (text == null) {
            return false;
        }
        return text.equals("on");
    }

    @Override
    public String print(Boolean object, Locale locale) {
        return object.toString();
    }
}
