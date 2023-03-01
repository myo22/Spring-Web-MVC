package org.zerock.review3.mapper;

import org.apache.ibatis.annotations.Select;

public interface TimeMapper { //MyBatis는 SQL 파일을 별도로 철리할 수 있지만 인터페이스와 어노테이션만으로도 처리 가능하다.

    @Select("select now()")
    String getTime();
    //어떤 메퍼 인터페이스를 설정했는지 root-context.xml에 등록해 주어야 합니다.
}
