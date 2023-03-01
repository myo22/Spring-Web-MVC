package org.zerock.review3.dto;

import lombok.*;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TodoDTO {

    private Long tno;

    @NotEmpty //null, 빈 문자열 불가(@Valid를 적용한 것이다)
    private String title;

    @Future //현재보다 미래인가?
    private LocalDate dueDate;

    private boolean finished;

    @NotEmpty
    private String writer; //새로 추가됨
}
