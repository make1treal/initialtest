package com.example.QnA.dto;

import com.example.QnA.domain.entity.Answer;
import com.example.QnA.domain.entity.Question;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class AnswerDto {
    private Integer id;
    private String content;
    private LocalDateTime createDate;
    @ManyToOne
    private Question question;


    //이건 Dto에서 Entity로 바꿔 써야 할 때 꺼내 쓸 method임
    public Answer toAnswerEntity() {
        //toEntity()는 DTO에서 필요한 부분을 빌더 패턴을 통해 Entity로 만드는 일을 함
        Answer build = Answer.builder()
                .id(id)
                .content(content)
                .createDate(createDate)
                .question(question)
                .build();
        return build;
    }
    @Builder
    //이거 생성자임...그래서 리턴타입 없음
    public AnswerDto(Integer id, String content, LocalDateTime createDate, Question question) {
        this.id = id;
        this.content = content;
        this.createDate = createDate;
        this.question = question;
    }
}
