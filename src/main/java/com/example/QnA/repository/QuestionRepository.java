package com.example.QnA.repository;

import com.example.QnA.domain.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuestionRepository extends JpaRepository<Question, Integer> {

    Question findBySubject(String subject);// Subject로 데이터 조회하기 위해 메서드 만들기
    Question findBySubjectAndContent(String subject, String content);
    // Subject와 Content로 함께 조회하기 위해 만든 메서드

    //findBySubjectLike의 리턴자료형은 List<Question>
    List<Question> findBySubjectLike(String subject);
}
