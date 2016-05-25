package com.taisho6339.man.crawler.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.util.List;

/**
 * Created by sakamohiroki on 2016/05/25.
 */
@Component
public class BookJDBCComponent {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Book> searchByTitle(String title) {
        return jdbcTemplate.query("SELECT * FROM T_BOOK AS B WHERE B.TITLE LIKE ?",
                new Object[]{"%" + title + "%"},
                (ResultSet rs, int i) -> {
                    Book book = new Book();
                    book.setId(rs.getLong("id"));
                    book.setIsbn(rs.getString("isbn"));
                    book.setPrice(rs.getInt("price"));
                    book.setTitle(rs.getString("title"));
                    book.setSummary(rs.getString("summary"));
                    return book;
                });
    }
}
