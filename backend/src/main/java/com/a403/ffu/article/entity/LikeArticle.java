package com.a403.ffu.article.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import java.io.Serializable;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Entity
@Table(
        uniqueConstraints = @UniqueConstraint(columnNames = {"user_no", "article_no"})
)
@IdClass(LikeArticle.PK.class)
public class LikeArticle {

    @Id
    @Column(name = "user_no")
    private Long userNo;

    @Id
    @Column(name = "article_no")
    private Long articleNo;

    public static class PK implements Serializable {
        Long userNo;
        Long articleNo;
    }
}
