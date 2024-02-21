package com.ssafy.a403.domain.article.entity;

import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@NoArgsConstructor
@Entity
@Table(
        uniqueConstraints = @UniqueConstraint(columnNames = {"user_no", "comment_no"})
)
@IdClass(LikeComment.PK.class)
public class LikeComment {

    @Id
    @Column(name = "user_no")
    private Long userNo;

    @Id
    @Column(name="comment_no")
    private Long commentNo;

    public static class PK implements Serializable {
        Long userNo;
        Long commentNo;
    }

}
