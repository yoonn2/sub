package com.example.firstproject.ioc2;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Chef2Test {

    @Test
    void 돈까스_요리하기() {
        // 준비
        IngredientFactory ingredientFactory = new IngredientFactory();
        Chef2 chef2 = new Chef2(ingredientFactory);
        String menu = "돈까스";

        // 수행
        String food = chef2.cook2(menu);

        // 예상
        String expected = "한돈 등심으로 만든 돈까스";

        // 검증
        assertEquals(expected, food);
        System.out.println(food);

    }
    @Test
    void 스테이크_요리하기() {
        // 준비
        IngredientFactory ingredientFactory = new IngredientFactory();
        Chef2 chef2 = new Chef2(ingredientFactory);
        String menu = "스테이크";

        // 수행
        String food = chef2.cook2(menu);

        // 예상
        String expected = "한우 꽃등심으로 만든 스테이크";

        // 검증
        assertEquals(expected, food);
        System.out.println(food);

    }
    @Test
    void 크리스피_치킨_요리하기() {
        // 준비
        IngredientFactory ingredientFactory = new IngredientFactory();
        Chef2 chef2 = new Chef2(ingredientFactory);
        String menu = "크리스피 치킨";

        // 수행
        String food = chef2.cook2(menu);

        // 예상
        String expected = "국내산 10호 닭으로 만든 크리스피 치킨";

        // 검증
        assertEquals(expected, food);
        System.out.println(food);

    }
}