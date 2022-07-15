package com.mysite.sbb.Controller;

import com.mysite.sbb.Dao.AnswerRepository;
import com.mysite.sbb.Domain.Answer;
import com.mysite.sbb.Domain.Question;
import com.mysite.sbb.Service.AnswerService;
import com.mysite.sbb.Service.QuestionService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/answer")
@AllArgsConstructor
public class AnswerController {

    private final QuestionService questionService;
    private final AnswerService answerService;

    @PostMapping("/create/{id}")
    public String createAnswer(Model model, @PathVariable("id") Integer id, @RequestParam String content) {
        Question question = this.questionService.getQuestion(id);
        // 질문만들기
        this.answerService.create(question, content);
        return String.format("redirect:/question/detail/%s", id);
    }
}
