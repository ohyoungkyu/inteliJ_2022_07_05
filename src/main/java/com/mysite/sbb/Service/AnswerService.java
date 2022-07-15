package com.mysite.sbb.Service;

import com.mysite.sbb.Dao.AnswerRepository;
import com.mysite.sbb.Dao.QuestionRepository;
import com.mysite.sbb.Domain.Answer;
import com.mysite.sbb.Domain.Question;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class AnswerService {

    private final AnswerRepository answerRepository;

    public void create(Question question, String content) {
        Answer answer = new Answer();
        answer.setContent(content);
        answer.setCreateDate(LocalDateTime.now());
        answer.setQuestion(question);
        this.answerRepository.save(answer);
    }

}
