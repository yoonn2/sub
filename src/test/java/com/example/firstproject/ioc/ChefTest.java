package com.example.firstproject.ioc;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ChefTest {

        IngredientFactory ingredientFactory;
        @Test
        void 돈까스_요리하기(){
            //준비

            Chef chef = new Chef(ingredientFactory);
            String menu = "돈까스";
            //수행
            String food = chef.cookCutlet(menu);
            //예상
            String expected = "한돈 등심으로 만든 돈까스";
            //검증
            Assertions.assertEquals(expected,food);
            System.out.println(food);

        }

}