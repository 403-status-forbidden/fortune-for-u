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
        uniqueConstraints = @UniqueConstraint(columnNames = {"user_no", "comment_no"})
)
@IdClass(LikeComment.PK.class)
public class LikeComment {

    @Id
    @Column(name = "user_no")
    private Long userNo;

    @Id
    @Column(name = "comment_no")
    private Long commentNo;

    public static class PK implements Serializable {

        Long userNo;
        Long commentNo;
    }
}
