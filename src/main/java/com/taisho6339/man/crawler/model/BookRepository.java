package com.taisho6339.man.crawler.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by sakamohiroki on 2016/05/25.
 */
@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
}
