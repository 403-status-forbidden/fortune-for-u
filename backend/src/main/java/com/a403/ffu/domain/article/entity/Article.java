package com.ssafy.a403.domain.article.entity;

import com.ssafy.a403.domain.member.entity.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Entity
@NoArgsConstructor
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="article_no")
    private int articleNo;

    @ManyToOne
    @JoinColumn(name = "member_no")
    private Member member;



//    @Enumerated(EnumType.STRING)
    private String category;

    @Column(name="article_title")
    private String articleTitle;

    @Column(name="article_content")
    private String articleContent;

    @Column(name="article_date")
    private LocalDateTime articleDate;

    @Builder
    public Article(Member member, String category, String articleTitle, String articleContent, LocalDateTime articleDate){
        this.member = member;
        this.category = category;
        this.articleTitle = articleTitle;
        this.articleContent = articleContent;
        this.articleDate = articleDate;
    }

}
