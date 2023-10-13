package com.example.QnA.controller;

import com.example.QnA.AnswerForm;
import com.example.QnA.Service.AnswerService;
import com.example.QnA.Service.QuestionService;
import com.example.QnA.domain.entity.Question;
import com.example.QnA.dto.QuestionDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

    @RequestMapping("/answer")
    @RequiredArgsConstructor
    @Controller
    public class AnswerController {
        private final QuestionService questionService;
        private final AnswerService answerService;

        @PostMapping("/create/{id}")
        public String createAnswer(Model model, @PathVariable("id") Integer id, @Valid AnswerForm answerForm, BindingResult bindingResult) {
            QuestionDto questionDto = this.questionService.getQuestion(id);
            if (bindingResult.hasErrors()) {
                model.addAttribute("questionDto", questionDto);
                return "question_detail";
            }
            this.answerService.create(questionDto.toQuestionEntity(), answerForm.getContent());
            return String.format("redirect:/question/detail/%s", id);
        }
    }

