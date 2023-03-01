package org.zerock.review3.mapper;

public interface TimeMapper2 { //@Select와 같은 어노테이션을 사용하기도 하지만 대부분은 별도의 파일로 분리하는 것을 권장합니다. 이떄 XML를 이용합니다.
    //namespace, 메소드 이름등은 같게 작성합니다.
    String getNow();
}
