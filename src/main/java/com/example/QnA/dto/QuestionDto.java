package com.example.QnA.dto;

import com.example.QnA.domain.entity.Answer;
import com.example.QnA.domain.entity.Question;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class QuestionDto {
    private Integer id;
    private String subject;
    private String content;
    private LocalDateTime createDate;
    private List<Answer> answerList;

    public Question toQuestionEntity() {
        //toEntity()는 DTO에서 필요한 부분을 빌더 패턴을 통해 Entity로 만드는 일을 함
        Question build = Question.builder()
                .id(id)
                .subject(subject)
                .content(content)
                .createDate(createDate)
                .answerList(answerList)
                .build();
        return build;
    }

    @Builder
    //이거 생성자임...그래서 리턴타입 없음
    public QuestionDto(Integer id, String subject, String content, LocalDateTime createDate, List<Answer> answerList) {
        this.id = id;
        this.subject = subject;
        this.content = content;
        this.createDate = createDate;
        this.answerList = answerList;
    }
}
