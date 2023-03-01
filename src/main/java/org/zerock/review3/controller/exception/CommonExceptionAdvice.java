package org.zerock.review3.controller.exception;

import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.util.Arrays;

@Log4j2
@ControllerAdvice //컨트롤러에서 발생하는 예외를 처리하는 가장 일반적인 방식 이것 역시 bean으로 처리됩니다.
public class CommonExceptionAdvice {


    @ResponseBody //문자열(or JSON데이터)을 그대로 브라우저에 전송하는 방식을 이용했습니다.
    @ExceptionHandler(NumberFormatException.class) //ExceptionHandler를 가진 메소드는 해당 타입의 예외를 파라미터로 전달받을 수 있습니다.
    public String exceptNumber(NumberFormatException numberFormatException){

        log.error("--------------------------");
        log.error(numberFormatException);

        return "NUMBER FORMAT EXCEPTION";
    }


    @ResponseBody
    @ExceptionHandler(Exception.class) //위에와 달리 저것에 국한된 것이 아니라 어디선가 문제가 발생하고 메시지로 받고싶다면 예외 처리의 상위타입인 Exception 타입을 처리 사실상 거의 모든 예외처리 가능
    public String exceptCommon(Exception exception){

        log.error("--------------------------");
        log.error(exception.getMessage());

        StringBuffer buffer = new StringBuffer("<ul>");

        buffer.append("<li>" + exception.getMessage()+ "</li>");

        Arrays.stream(exception.getStackTrace()).forEach(stackTraceElement -> {
            buffer.append("<li>" + stackTraceElement + "</li>");
        });

        buffer.append("</ul>");

        return buffer.toString();
    }

    @ExceptionHandler(NoHandlerFoundException.class) //시작부터 잘못된 URL을 호출하게 되면 404예외가 발생할때 상태에 맞는 별도의 화면 작성하기
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String notFound(){  //물론 web.xml에다가 DispatcherServlet 설정을 조정해야합니다.

        return "error404";
    }
}
