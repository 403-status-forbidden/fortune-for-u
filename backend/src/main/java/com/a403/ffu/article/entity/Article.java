package com.a403.ffu.article.entity;

import com.a403.ffu.member.entity.Member;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "article_no")
    private int articleNo;

    @ManyToOne
    @JoinColumn(name = "member_no")
    private Member member;

    //    @Enumerated(EnumType.STRING)
    private String category;

    @Column(name = "article_title")
    private String articleTitle;

    @Column(name = "article_content")
    private String articleContent;

    @Column(name = "article_date")
    private LocalDateTime articleDate;

    @Builder
    public Article(Member member, String category, String articleTitle, String articleContent,
            LocalDateTime articleDate) {
        this.member = member;
        this.category = category;
        this.articleTitle = articleTitle;
        this.articleContent = articleContent;
        this.articleDate = articleDate;
    }
}
