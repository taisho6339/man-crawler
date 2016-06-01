package com.taisho6339.man.crawler.service;

import com.taisho6339.man.crawler.model.Tag;
import com.taisho6339.man.crawler.repository.TagRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.util.List;

/**
 * Created by sakamohiroki on 2016/05/30.
 */
@Service
public class TagService {
    @Autowired
    private TagRepository tagRepository;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Tag> findAll() {
        return tagRepository.findAll();
    }

    public Tag findByName(String name) {
        return tagRepository.findByTagName(name);
    }

    public Tag findById(Long id) {
        return tagRepository.findById(id);
    }

    public Tag save(Tag tag) {
        return tagRepository.save(tag);
    }

    public List<Tag> findByEmpId(Long emp_id) {
        return jdbcTemplate.query("SELECT * " +
                        "FROM M_TAG AS T " +
                        "INNER JOIN (SELECT * FROM T_EMP_TAG WHERE T_EMP_TAG.emp_id = ?) AS R " +
                        "ON T.id = R.tag_id", new Object[]{emp_id},
                (ResultSet rs, int i) -> {
                    Tag tag = new Tag();
                    tag.setId(rs.getLong("tag_id"));
                    tag.setTagName(rs.getString("tag_name"));
                    return tag;
                });
    }
}
