package org.zerock.review3.service;

import org.zerock.review3.domain.TodoVO;
import org.zerock.review3.dto.TodoDTO;

import java.util.List;

public interface TodoService {
    void register(TodoDTO todoDTO);

    List<TodoDTO> getAll();

    TodoDTO getOne(Long tno);

    void remove(Long tno);

    void modify(TodoDTO todoDTO);
}
