package com.example.firstproject.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;


@Entity
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Pizza {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Column
    String name;
    @Column
    double price;
}
/*
피자 데이터의 생성: (POST) /pizzas
단건 조회: (GET) /pizzas/{id}
목록 조회: (GET) /pizzas
수정: (PATCH) /pizzas/{id}
삭제: (DELETE) /pizzas/{id}
 */
