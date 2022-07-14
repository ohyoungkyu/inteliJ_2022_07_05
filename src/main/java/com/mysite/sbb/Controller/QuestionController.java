package com.mysite.sbb.Controller;

import com.mysite.sbb.Dao.QuestionRepository;
import com.mysite.sbb.Domain.Question;
import com.mysite.sbb.Service.QuestionService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/question")
@AllArgsConstructor
public class QuestionController {

    private QuestionService questionService;

    @RequestMapping("/list")
    public String showQuestions(Model model) {
        List<Question> questionList =questionService.getList();

        model.addAttribute("questionList",questionList);
        return "question_List";
    }

    @RequestMapping("/detail/{id}")
    public String showQuestions(Model model, @PathVariable("id") Integer id) {
        Question question = this.questionService.getQuestion(id);
        model.addAttribute("question",question);
        return "question_detail";
    }
}
