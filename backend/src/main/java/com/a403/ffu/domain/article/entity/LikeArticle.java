package com.ssafy.a403.domain.article.entity;

import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

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
    @Column(name="article_no")
    private Long articleNo;

    public static class PK implements Serializable {
        Long userNo;
        Long articleNo;
    }


}
