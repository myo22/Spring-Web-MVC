package org.zerock.review3.service;

import org.zerock.review3.domain.TodoVO;
import org.zerock.review3.dto.PageRequestDTO;
import org.zerock.review3.dto.PageResponseDTO;
import org.zerock.review3.dto.TodoDTO;

import java.util.List;

public interface TodoService {
    void register(TodoDTO todoDTO);

    //List<TodoDTO> getAll(); 기존의 getAll을 대체할것이다
    PageResponseDTO<TodoDTO> getList(PageRequestDTO pageRequestDTO);

    TodoDTO getOne(Long tno);

    void remove(Long tno);

    void modify(TodoDTO todoDTO);
}
