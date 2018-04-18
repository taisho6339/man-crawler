package com.taisho6339.man.crawler.repository;

import com.taisho6339.man.crawler.model.Article;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by taisho6339 on 16/05/30.
 */
public interface ArticleRepository extends JpaRepository<Article, Long> {
    public List<Article> findByEmpId(Long empId);
}
