package com.taisho6339.man.crawler.model;

import org.hibernate.validator.constraints.NotBlank;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

/**
 * Created by sakamohiroki on 2016/05/26.
 */
@Entity
@Table(name = "M_TAG")
public class Tag {
    @Id
    @GeneratedValue
    private Long id;

    @NotBlank
    private String tagName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }
}
