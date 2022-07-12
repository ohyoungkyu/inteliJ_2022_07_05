package com.mysite.sbb.Controller;

import com.mysite.sbb.Dao.ArticleRepository;
import com.mysite.sbb.Domain.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/usr/article")
public class ArticleController {
    @Autowired
    private ArticleRepository articleRepository;

    @RequestMapping("/test")
    @ResponseBody
    public String testFunc() {
        return "test";
    }

    @RequestMapping("/list")
    @ResponseBody
    public List<Article> showList() {
        return articleRepository.findAll();
    }

    @RequestMapping("/detail")
    @ResponseBody
    public Article showDetail(Long id) {
        Optional<Article> article = articleRepository.findById(id);
        return article.orElse(null);
    }

    @RequestMapping("/doDelete")
    @ResponseBody
    public String doDelete(Long id) {
        if(articleRepository.existsById(id) == false) {
            return "%d번 게시물은 이미 삭제되었거나 존재하지 않습니다.".formatted(id);
        }

        articleRepository.deleteById(id);

        return "%d번 게시물이 삭제되었습니다.".formatted(id);

    }

    @RequestMapping("/findByTitle")
    @ResponseBody
    public List<Article> findByTitle(String title) {
        List<Article> articles = articleRepository.findByTitle(title);
        return articles;
    }

    @RequestMapping("/doModify")
    @ResponseBody
    public Article doModify(Long id, String title, String body) {
        Article article = articleRepository.findById(id).get();

        if(title != null) {
            article.setTitle(title);
        }
        if(body != null) {
            article.setBody(body);
        }

        article.setUpdateDate(LocalDateTime.now());

        articleRepository.save(article);
        return article;
    }


}
