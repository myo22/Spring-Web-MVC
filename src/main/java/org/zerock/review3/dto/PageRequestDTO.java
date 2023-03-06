package org.zerock.review3.dto;

import lombok.*;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Positive;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Array;
import java.net.URLEncoder;
import java.time.LocalDate;
import java.util.Arrays;

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
            StringBuilder builder = new StringBuilder(); // StringBuilder는 String과 문자열을 더할때 새로운 객체가 생성하는 것이 아니라 기존에 데이터에 더하는 방식을 사용하기 때문에 속도도 빠르고 상대적으로 부하가 적다.
            builder.append("page=" + this.page);
            builder.append("&size=" + this.size);

            if (finished){
                builder.append("&finished=on");   //모든 검색/필터링 조건을 쿼리 스트링으로 구성해야 하기 떄문에 지저분하다 이렇게 안하면 화면에서 모든 링크를 수정해야 해서 더 복잡해진다.
            }

            if (types != null && types.length > 0){
                for (int i = 0; i < types.length ; i++) {
                    builder.append("&types=" + types[i]);
                }
            }

            if (keyword != null){
                try {
                    builder.append("&keyword=" + URLEncoder.encode(keyword, "UTF-8"));  //한글이 가능한 keyword 부분은 URLEncoder를 이용해서 링크로 처리할 수 있도록 처리.
                } catch (UnsupportedEncodingException e){
                    e.printStackTrace();
                }
            }

            if (from != null){
                builder.append("&from=" + from.toString());
            }

            if (to != null){
                builder.append("&to=" + to.toString());
            }


        return builder.toString();
    }

    private String[] types;

    private String keyword;

    private boolean finished;

    private LocalDate from;

    private LocalDate to;

    public boolean checkType(String type) { //PageRequestDTO의 정보를 EL로 처리하지 않았기 때문에 검색 후에는 검색 부분이 초기화 되는 문제를 해결하기 위해서 만듬
        if (types == null || types.length == 0){
            return false;
        }

        return Arrays.stream(types).anyMatch(type::equals); //가장 문제가 되는 제목(t), 작성자(w)가 배열로 처리되고 있으므로 편리하게 사용하기 위함
    }
}
