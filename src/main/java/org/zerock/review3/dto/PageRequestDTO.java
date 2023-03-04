package org.zerock.review3.dto;

import lombok.*;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Positive;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor             //Todo 데이터의 수가 많아진다면 목록 페이지를 가져올 때 많은 시간이 걸리고 이를 출력하는 데에도 많은 시간과 자원이 소모 되기 때문에 많은 데이터를 보요주는 작업은 페이징 처리를 해서 데이터베이스에서 필요한 만큼만 가져와서 성능 개선에 도움이 됩니다.
public class PageRequestDTO {  //select * from tbl_todo order by tno desc limit 10, 10; 쿼리인데 여기서 앞에 10은 스킵할숫자 뒤에는 가져올 숫자

    @Builder.Default //특정 필드를 특정 값으로 초기화
    @Min(value =  1)
    @Positive //양수만 가능하다는 어노테이션
    private int page = 1;

    @Builder.Default
    @Min(value = 10)
    @Max(value = 100)
    @Positive
    private int size = 10;

    public String link;

    public int getSkip(){ //#{skip}의 경우는 getSkip()을 호출하게 됩니다. Why? MyBatis는 기본적으로 getXXX, setXXX를 통해서 동작하기 때문에.
        return (page -1) * 10;
    }

    public String getLink() { //페이지 이동에 필요한 링크들을 생성합니다.
        if(link == null){
            StringBuilder builder = new StringBuilder(); // StringBuilder는 String과 문자열을 더할때 새로운 객체가 생성하는 것이 아니라 기존에 데이터에 더하는 방식을 사용하기 때문에 속도도 빠르고 상대적으로 부하가 적다.
            builder.append("page=" + this.page);
            builder.append("&size=" + this.size);
            link = builder.toString();
        }
        return link;
    }
}
