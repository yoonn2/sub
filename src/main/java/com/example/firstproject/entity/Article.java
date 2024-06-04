package com.example.firstproject.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;


@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Getter
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)//DB가 id 자동 생성
/*
    기본키를 자동으로 생성하는 방법 4가지
    기본키를 자동으로 생성할 때에는 @Id와 @GeneratedValue 어노테이션이 함께 사용되어야 한다.

    1. IDENTITY - @GeneratedValue(strategy = GenerationType.IDENTITY)
        기본키 생성을 데이터베이스에세 위임하는 방식으로 id값을 따로 할당하지 않아도
        데이터베이스가 자동으로 AUTO_INCREMENT를 하여 기본키를 생성해준다.
    2. SEQUENCE - @GeneratedValue(strategy = GenerationType.SEQUENCE)
    3. TABLE - @GeneratedValue(strategy = GenerationType.TABLE)
    4. AUTO - @GeneratedValue(strategy = GenerationType.AUTO)

*/

    private Long id;
    @Column
    private String title;
    @Column
    private String content;


    public void patch(Article article) {
        if(article.title != null)
            this.title=article.title;
        if (article.content != null)
            this.content=article.content;
    }
}