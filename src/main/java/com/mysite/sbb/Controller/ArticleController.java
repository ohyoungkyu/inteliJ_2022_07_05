package com.mysite.sbb.Controller;

import com.mysite.sbb.Dao.ArticleRepository;
import com.mysite.sbb.Domain.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("usr/article")
public class ArticleController {
    @Autowired
    private ArticleRepository articleRepository;

    @RequestMapping("/list")
    @ResponseBody
    public List<Article> showList() {
        return articleRepository.findAll();
    }

    @RequestMapping("/detail")
    @ResponseBody
    public Article showDetail(Long id) {
        Article article = articleRepository.findById(id).get();
        return article;
    }

    @RequestMapping("/delete")
    @ResponseBody
    public String DoArticleDelete(Long id) {
        articleRepository.deleteById(id);

        return "%d번 게시물이 삭제되었습니다.".formatted(id);
    }
    @RequestMapping("/Modify")
    @ResponseBody
    public Article DoArticleModify(Long id, String title, String body) {
        Article article = articleRepository.findById(id).get();

        if(title != null) {
            article.setTitle(title);
        }
        if(body != null) {
            article.setBody(body);
        }
        articleRepository.save(article);
        return article;
    }


}
