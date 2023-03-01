package org.zerock.review3.mapper;

import org.zerock.review3.domain.TodoVO;
import org.zerock.review3.dto.PageRequestDTO;

import java.util.List;

public interface TodoMapper {  //XML을 작성할 때 namespace의 값은 인터페이스의 이름, 메소드의 이름은 <select>태그의 id와 반드시 일치시켜야한다
                               //SQL의 실행 로그를 좀 더 자세하게 보고 싶다면 org.zerock.reiview3.mapper 패키지 로그는 TRACE 레벨로 기록하도록 log4j2.xml 코드에 추가합니다.
    String getTime();

    void insert(TodoVO todoVO);  //MyBatis를 이용하면 '?' 대신에 #{title}과 같이 파라미터를 처리합니다.
                                 // #{title}은 PreparedStatement로 다시 변경되면서 '?'로 처리되고 주어진 객체의 getTitle()을 호출합니다.

    List<TodoVO> selectAll();

    TodoVO selectOne(Long tno);

    void delete(Long tno);

    void update(TodoVO todoVO);

    List<TodoVO> selectList(PageRequestDTO pageRequestDTO);

}
