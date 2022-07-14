package com.mysite.sbb.Service;

import com.mysite.sbb.Dao.QuestionRepository;
import com.mysite.sbb.Domain.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionService {

    @Autowired
    private QuestionRepository questionRepository;

    public List<Question> getList() {
        return questionRepository.findAll();
    }


}
