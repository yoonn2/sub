package com.example.firstproject.ioc2;

import org.springframework.stereotype.Component;

@Component
public class Chef2 {
    // 셰프는 식재료 공장을 알고 있음
    private IngredientFactory ingredientFactory;

    // 셰프가 식재료 공장과 협업하기 위한 DI
    public Chef2(IngredientFactory ingredientFactory) {
        this.ingredientFactory = ingredientFactory;
    }

    public String cook2(String menu) {
        // 재료 준비
        Ingredient ingredient = ingredientFactory.get(menu);
        // 요리 반환
        return ingredient.getName() + "으로 만든 " + menu;
    }
}
