package org.zerock.review3.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.zerock.review3.domain.TodoVO;
import org.zerock.review3.dto.TodoDTO;
import org.zerock.review3.mapper.TodoMapper;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Log4j2
@RequiredArgsConstructor //@RequiredArgsConstructor를 사용하지 않으면 원래는 이렇게 생성자 주입을 해야한다 (ex. this.todoMapper = todoMapper;)
public class TodoServiceImpl implements TodoService{

    private final TodoMapper todoMapper;

    private final ModelMapper modelMapper;

    @Override
    public void register(TodoDTO todoDTO){

        log.info(modelMapper);

        TodoVO todoVO = modelMapper.map(todoDTO, TodoVO.class);  //TodoDTO를 TodoVO로 변환

        log.info(todoVO);

        todoMapper.insert(todoVO);
    }

    @Override
    public List<TodoDTO> getAll(){

        List<TodoDTO> dtoList = todoMapper.selectAll().stream() //TodoMapper가 반환하는 데이터 타입이 List<TodoVO>이기 때문에 이를 List<TodoDTO>로 변환하는 작업이 필요.
                .map(vo -> modelMapper.map(vo, TodoDTO.class))
                .collect(Collectors.toList()); //collect()를 이용해서 List<TodoDTO>로 묶어줍니다.

        return dtoList;
    }

    @Override
    public TodoDTO getOne(Long tno){

        TodoVO todoVO = todoMapper.selectOne(tno);

        TodoDTO todoDTO = modelMapper.map(todoVO, TodoDTO.class);

        return todoDTO;
    }

    @Override
    public void remove(Long tno){
        todoMapper.delete(tno);
    }

    @Override
    public void modify(TodoDTO todoDTO){

        TodoVO todoVO = modelMapper.map(todoDTO, TodoVO.class);

        todoMapper.update(todoVO);
    }
}
