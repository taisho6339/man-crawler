package com.taisho6339.man.crawler.model;

import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by sakamohiroki on 2016/05/26.
 */
@Entity
@Table(name = "T_ARTICLE")
public class Article {
    @Id
    @GeneratedValue
    private Long id;

    @NotBlank
    private Long employee_id;

    private String summary;

    private String aboutUrl;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
    public String toString() {
        return "Article{" +
                "id=" + id +
                ", employee_id=" + employee_id +
                ", summary='" + summary + '\'' +
                ", aboutUrl='" + aboutUrl + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Article article = (Article) o;

        if (id != null ? !id.equals(article.id) : article.id != null) return false;
        if (employee_id != null ? !employee_id.equals(article.employee_id) : article.employee_id != null)
            return false;
        if (summary != null ? !summary.equals(article.summary) : article.summary != null)
            return false;
        return aboutUrl != null ? aboutUrl.equals(article.aboutUrl) : article.aboutUrl == null;

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (employee_id != null ? employee_id.hashCode() : 0);
        result = 31 * result + (summary != null ? summary.hashCode() : 0);
        result = 31 * result + (aboutUrl != null ? aboutUrl.hashCode() : 0);
        return result;
    }
}
