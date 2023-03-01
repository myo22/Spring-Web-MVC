package org.zerock.review3.dto;

import java.util.List;

public class PageResponseDTO<E> { //제네릭을 이용해서 설계합니다. 이용하는 이유는 나중에 다른 종류의 객체를 이용해서 PageResponseDTO를 구성할 수 있도록 하기 위해서 입니다.
                                  //예를들어 게시판이나 회원 정보 등도 페이징 처리가 필요할 수 있기 때문에 공통적인 처리를 위해서 제네릭으로 구성합니다.
    private int page;
    private int size;
    private int total;

    //시작 페이지 번호
    private int start;
    //끝 페이지 번호
    private int end;

    //이전 페이지의 존재 여부
    private boolean prev;
    //다음 페이지의 존재 여부
    private boolean next;

    private List<E> dtoList;
}
