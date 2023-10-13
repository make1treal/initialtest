package com.example.QnA.Service;

import com.example.QnA.domain.entity.Answer;
import com.example.QnA.domain.entity.Question;
import com.example.QnA.dto.AnswerDto;
import com.example.QnA.dto.QuestionDto;
import com.example.QnA.repository.AnswerRepository;
import com.example.QnA.repository.AnswerRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class AnswerService {

    private final AnswerRepository answerRepository;

    public List<AnswerDto> getDtoList() { //질문 목록 조회
        List<Answer> answerList = answerRepository.findAll();
        List<AnswerDto> answerDtoList = new ArrayList<>();

        for(Answer answer : answerList) {
            AnswerDto answerDto = AnswerDto.builder()
                    .id(answer.getId())
                    .content(answer.getContent())
                    .createDate(answer.getCreateDate())
                    .question(answer.getQuestion())
                    .build();
            answerDtoList.add(answerDto);
        }
        return answerDtoList;
    }

    public void create(Question question, String content){
        AnswerDto answerDto = new AnswerDto();
        answerDto.setContent(content);
        answerDto.setCreateDate(LocalDateTime.now());
        answerDto.setQuestion(question);
        this.answerRepository.save(answerDto.toAnswerEntity());;
    }
}