package org.zerock.review3.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.List;
import java.util.Map;

@Getter
@ToString
public class PageResponseDTO<E> { //제네릭을 이용해서 설계합니다. 이용하는 이유는 나중에 다른 종류의 객체를 이용해서 PageResponseDTO를 구성할 수 있도록 하기 위해서 입니다.
                                  //예를들어 게시판이나 회원 정보 등도 페이징 처리가 필요할 수 있기 때문에 공통적인 처리를 위해서 제네릭으로 구성합니다.
                                  //그런데 String 타입도 지원하고싶고 Integer타입도 지원하고 싶고 많은 타입을 지원하고 싶다. 그러면 String에 대한 클래스, Integer에 대한 클래스 등 하나하나 타입에 따라 만들 것인가? 그건 너무 비효율적이다. 이러한 문제를 해결하기 위해 우리는 제네릭이라는 것을 사용한다.
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

    @Builder(builderMethodName = "withAll") //만약 요구되는 필드를 특정화할 필요가 있을 때에는 builderMethodName을 사용해 빌더에 이름을 부여하고 그에 따른 책임을 부여할 수 있다. (간단하게 빌더의 이름을 변경하는것)
    public PageResponseDTO(PageRequestDTO pageRequestDTO, List<E> dtoList, int total){

        this.page = pageRequestDTO.getPage();
        this.size = pageRequestDTO.getSize();

        this.total = total;
        this.dtoList = dtoList;

        this.end = (int)(Math.ceil(this.page/10.0)) * 10; //page를 10으로 나눈 값을 올림 처리 한 후 * 10

        this.start = this.end - 9;

        int last = (int)(Math.ceil((total/(double)size))); //마지막 페이지는 전체 개수를 고려 ex) 75라면 마지막 페이지는 8이어야함

        this.end = end > last ? last: end;

        this.prev = this.start > 1;

        this.next = total > this.end * this.size;


    }
}
