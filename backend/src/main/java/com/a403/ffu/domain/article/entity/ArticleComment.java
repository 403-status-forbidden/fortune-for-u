package com.ssafy.a403.domain.article.entity;


import com.ssafy.a403.domain.member.entity.Member;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Entity
@NoArgsConstructor
public class ArticleComment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="comment_no")
    private int commentNo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "article_no")
    private Article article;


    @ManyToOne
    @JoinColumn(name = "member_no")
    private Member member;


    //댓글 내용
    @Column(name="comment_content")
    private String commentContent;

    @Column(name="comment_date")
    private LocalDateTime commentDate;


}
