package com.taisho6339.man.crawler.model;

import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 * Created by sakamohiroki on 2016/05/26.
 */
@Entity
@Table(name = "T_ARTICLE")
public class Article {
    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    private Long empId;

    private String summary;

    private String aboutUrl;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getEmpId() {
        return empId;
    }

    public void setEmpId(Long empId) {
        this.empId = empId;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getAboutUrl() {
        return aboutUrl;
    }

    public void setAboutUrl(String aboutUrl) {
        this.aboutUrl = aboutUrl;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Article article = (Article) o;

        if (id != null ? !id.equals(article.id) : article.id != null) return false;
        if (empId != null ? !empId.equals(article.empId) : article.empId != null)
            return false;
        if (summary != null ? !summary.equals(article.summary) : article.summary != null)
            return false;
        return aboutUrl != null ? aboutUrl.equals(article.aboutUrl) : article.aboutUrl == null;

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (empId != null ? empId.hashCode() : 0);
        result = 31 * result + (summary != null ? summary.hashCode() : 0);
        result = 31 * result + (aboutUrl != null ? aboutUrl.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Article{" +
                "id=" + id +
                ", empId=" + empId +
                ", summary='" + summary + '\'' +
                ", aboutUrl='" + aboutUrl + '\'' +
                '}';
    }
}
