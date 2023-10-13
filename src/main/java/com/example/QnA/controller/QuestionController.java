package com.example.QnA.controller;

import com.example.QnA.AnswerForm;
import com.example.QnA.QuestionForm;
import com.example.QnA.Service.QuestionService;
import com.example.QnA.domain.entity.Question;
import com.example.QnA.dto.QuestionDto;
import com.example.QnA.repository.QuestionRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/question")
@RequiredArgsConstructor//questionRepository (final 붙은) 속성 포함하는 생성자 자동 생성
// -> questionRepository객체가 자동으로 주입됨

public class QuestionController {

    private final QuestionService questionService;

    @GetMapping("/list")
    public String list(Model model) {
        List<QuestionDto> questionDtoList = this.questionService.getDtoList();
        model.addAttribute("questionDtoList", questionDtoList);
        return "question_list";
    }

    @GetMapping(value = "/detail/{id}")
    public String detail(Model model, @PathVariable("id") Integer id, AnswerForm answerForm) {//변하는 id값을 얻을 때는 @PathVariable 써야함
        QuestionDto questionDto = this.questionService.getQuestion(id);
        model.addAttribute("questionDto", questionDto);
        return "question_detail";
    }

    @GetMapping("/create") //신규 질문 등록 폼 페이지로 이동하기
    public String questionCreate(QuestionForm questionForm) {
        return "question_form";
    }

    @PostMapping("/create")//질문 폼 페이지에서 새 질문 등록&저장 후 /question/list로 돌아오기
    // +) questionCreate로 메서드 이름 같아도 파라미터 개수,타입 달라서 ㄱㅊ(메서드 오버로딩)
    public String questionCreate(@Valid QuestionForm questionForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {//에러 있으면 다시 question_form(작성화면) 띄우기
            // 빈칸 상태로 저장 시도하면 QuestionForm의 @NotEmpty에 의해 Validation 실패해서 다시 작성화면으로 돌아오게 됨
            return "question_form";
        }
        this.questionService.create(questionForm.getSubject(), questionForm.getContent());
        return "redirect:/question/list";
    }
}
