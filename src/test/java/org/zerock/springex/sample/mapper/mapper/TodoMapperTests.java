package org.zerock.springex.sample.mapper.mapper;

import lombok.ToString;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.zerock.review3.domain.TodoVO;
import org.zerock.review3.dto.PageRequestDTO;
import org.zerock.review3.mapper.TodoMapper;

import java.time.LocalDate;
import java.util.List;

@Log4j2
@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = "file:src/main/webapp/WEB-INF/root-context.xml")
public class TodoMapperTests {

    @Autowired(required = false) //required = false 는 해당 객체를 주입 받지 못하더라고 예외가 발생하지 않는다
    private TodoMapper todoMapper;

    @Test
    public void testGetTime(){
        log.info(todoMapper.getTime());
    }

    @Test
    public void testInsert(){

        TodoVO todoVO = TodoVO.builder()
                .title("스프링 테스트")
                .dueDate(LocalDate.of(2023,02,23))
                .writer("minhyung")
                .build();

        todoMapper.insert(todoVO);
    }

    @Test
    public void testSelectAll(){
        List<TodoVO> voList = todoMapper.selectAll();

        voList.forEach(vo -> log.info(vo));
    }

    @Test
    public void testSelectOne(){
        TodoVO todoVO = todoMapper.selectOne(3L);

        log.info(todoVO);
    }

    @Test
    public void testSelectList() {

        PageRequestDTO pageRequestDTO = PageRequestDTO.builder()
                .page(1)
                .size(10)
                .build();

        List<TodoVO> voList = todoMapper.selectList(pageRequestDTO);

        voList.forEach(vo -> log.info(vo));
    }
}
