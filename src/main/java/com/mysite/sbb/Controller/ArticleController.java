package com.mysite.sbb.Controller;

import com.mysite.sbb.Dao.ArticleRepository;
import com.mysite.sbb.Domain.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("/usr/article")
public class ArticleController {
    @Autowired
    private ArticleRepository articleRepository;


    @RequestMapping("/list")
    @ResponseBody
    public List<Article> findListTitleandBody(String title, String body) {
        if( title != null && body == null) {
           if(articleRepository.existsByTitle(title) == false) {
               System.out.println("제목과 일치하는 게시물이 없습니다.");
               return null;
           }
           return articleRepository.findByTitle(title);

        } else if( title == null && body != null) {
            if(articleRepository.existsByBody(body) == false) {
                System.out.println("내용과 일치하는 게시물이 없습니다.");
                return null;
            }
            return articleRepository.findByBody(body);

        } else if( title != null && body != null) {
            if(articleRepository.existsByTitleAndBody(title, body) == false) {
                System.out.println("제목과 내용이 일치하는 게시물이 없습니다.");
                return null;
            }
            return articleRepository.findByTitleAndBody(title, body);
        }
        return articleRepository.findAll();
    }

    @RequestMapping("/detail")
    @ResponseBody
    public Article showArticleDetail(@RequestParam Long id) {
        Article article = articleRepository.findById(id).get();
        return article;
    }

    @RequestMapping("/doDelete")
    @ResponseBody
    public String DoArticleDelete(@RequestParam Long id) {
        if( articleRepository.existsById(id) == false ) {
            return "%d번 게시물은 이미 삭제되었거나 없는 게시물 입니다.".formatted(id);
        }

        articleRepository.deleteById(id);
        return "%d번 게시물이 삭제되었습니다.".formatted(id);
    }

    @RequestMapping("/doModify")
    @ResponseBody
    public Article doModify(@RequestParam Long id, String title, String body) {
        Article article = articleRepository.findById(id).get();

        if( title != null) {
            article.setTitle(title);
        }
        if( body != null) {
            article.setBody(body);
        }

        article.setUpdateDate(LocalDateTime.now());

        articleRepository.save(article);
        return article;
    }

    @RequestMapping("/findByTitle")
    @ResponseBody
    public List<Article> findByTitle(String title) {
        List<Article> articles = articleRepository.findByTitle(title);
        return articles;
    }

    @RequestMapping("/doWrite")
    @ResponseBody
    public String doWrite(String title, String body) {
        if( title == null || title.trim().length() == 0 ) {
            return "제목을 입력해주세요.";
        }

        if( body == null || body.trim().length() == 0 ) {
            return "내용을 입력해주세요.";
        }

        title = title.trim();
        body = body.trim();

        Article article = new Article();
        article.setRegDate(LocalDateTime.now());
        article.setUpdateDate(LocalDateTime.now());
        article.setTitle(title);
        article.setBody(body);

        articleRepository.save(article);

        return "%d번 게시물이 생성되었습니다.".formatted(article.getId());
    }

}
