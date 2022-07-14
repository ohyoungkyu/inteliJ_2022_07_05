package com.mysite.sbb.Controller;

import com.mysite.sbb.Dao.QuestionRepository;
import com.mysite.sbb.Domain.Question;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/question")
@AllArgsConstructor
public class QuestionController {

    private final QuestionRepository questionRepository;

    @RequestMapping("/list1")
    @ResponseBody
    public List<Question> showQuestions() {
        return questionRepository.findAll();
    }


    @RequestMapping("/list")
    public String QuestionList(Model model) {
        List<Question> questionList = questionRepository.findAll();
        model.addAttribute("questionList",questionList);
        return "question_list";
    }
}
