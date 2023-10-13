package com.example.QnA.domain.entity;

import jakarta.persistence.*;
import jdk.jshell.execution.LocalExecutionControl;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor //이거 추가하니까 Builder관련 에러 사라짐(이유는 모름)
@Entity

public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)//IDENTITY: 기본키 생성을 DB에 위임한단 뜻
    private Integer id;

    @Column(length = 200)
    private String subject;

    @Column(columnDefinition = "TEXT")
    private String content;

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime createDate;

    @OneToMany(mappedBy = "question", cascade = CascadeType.REMOVE) //질문과 답변은 1:N관계
    //질문을 삭제하면 그에 달린 답변들도 함께 삭제되게
    private List<Answer> answerList;//question하나에 answer는 여러개니까
    //question엔티티에 답변 참조하려면 답변의 속성 List로 구성하기

    @Builder
    public Question(Integer id, String subject, String content, LocalDateTime createDate, List<Answer> answerList){
        this.id = id;
        this.subject = subject;
        this.content = content;
        this.createDate = createDate;
        this.answerList = answerList;
    }
}


