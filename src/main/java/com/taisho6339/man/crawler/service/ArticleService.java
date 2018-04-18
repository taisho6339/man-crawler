package com.taisho6339.man.crawler.service;

import com.taisho6339.man.crawler.model.Article;
import com.taisho6339.man.crawler.repository.ArticleRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by taisho6339 on 16/05/30.
 */
@Service
public class ArticleService {
    @Autowired
    ArticleRepository repository;

    public Article save(Article article) {
        return repository.save(article);
    }

    public List<Article> findByEmpId(Long id) {
        return repository.findByEmpId(id);
    }
}
