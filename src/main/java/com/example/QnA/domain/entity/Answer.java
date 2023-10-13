package com.example.QnA.domain.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class Answer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(columnDefinition = "TEXT")
    private String content;

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime createDate;

    @ManyToOne //하나의 질문에 여러 개의 답변 달리기 ㄱㄴ
    //답변과 질문은 N:1관계
    private Question question;

    @Builder
    public Answer(Integer id, String content, LocalDateTime createDate, Question question){
        this.id = id;
        this.content = content;
        this.createDate = createDate;
        this.question = question;
    }

}
