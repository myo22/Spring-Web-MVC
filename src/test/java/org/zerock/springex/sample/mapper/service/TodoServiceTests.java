package org.zerock.springex.sample.mapper.service;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.zerock.review3.dto.TodoDTO;
import org.zerock.review3.service.TodoService;

import java.time.LocalDate;

@Log4j2
@ExtendWith(SpringExtension.class) //확장을 선언적으로 등록하는데 사용됩니다. Extendtion 뒤에 인자로 확장할 Extension을 추가하여 사용합니다.
@ContextConfiguration(locations = "file:src/main/webapp/WEB-INF/root-context.xml") //생성된 스프링 컨테이너에 스프링 빈을 추가하기 위해서는
// application-context.xml 파일과 같은 설정 파일을 읽어야 하는데, 이런 설정파일을 로드하는 어노테이션이 ContextConfiguration이다.
public class TodoServiceTests {

    @Autowired
    private TodoService todoService;

    @Test
    public void testRegister(){

        TodoDTO todoDTO = TodoDTO.builder()
                .title("Test......")
                .dueDate(LocalDate.now())
                .writer("minhyung1")
                .build();

        todoService.register(todoDTO);
    }

}
