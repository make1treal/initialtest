package com.example.QnA.Service;

import com.example.QnA.domain.entity.Question;
import com.example.QnA.dto.QuestionDto;
import com.example.QnA.repository.QuestionRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class QuestionService {

    private final QuestionRepository questionRepository;

    public List<QuestionDto> getDtoList() { //질문 목록 조회
        List<Question> questionList = questionRepository.findAll();
        List<QuestionDto> questionDtoList = new ArrayList<>();

        for(Question question : questionList) {
           QuestionDto questionDto = QuestionDto.builder()
                    .id(question.getId())
                    .subject(question.getSubject())
                    .content(question.getContent())
                    .createDate(question.getCreateDate())
                    .answerList(question.getAnswerList())
                    .build();
            questionDtoList.add(questionDto);
        }
        return questionDtoList;
    }

    public QuestionDto getQuestion(Integer id) {
        Optional<Question> question = this.questionRepository.findById(id);
        if (question.isPresent()) {
            QuestionDto questionDto = QuestionDto.builder()
                    .id(question.get().getId())
                    .subject(question.get().getSubject())
                    .content(question.get().getContent())
                    .createDate(question.get().getCreateDate())
                    .answerList(question.get().getAnswerList())
                    .build();
            return questionDto;
//            return question.get();
        } else {
            throw new DataNotFoundException("question not found");
        }
    }
    public void create(String subject, String content) {
        QuestionDto q = new QuestionDto();
        q.setSubject(subject);
        q.setContent(content);
        q.setCreateDate(LocalDateTime.now());
        this.questionRepository.save(q.toQuestionEntity());
    }
}
