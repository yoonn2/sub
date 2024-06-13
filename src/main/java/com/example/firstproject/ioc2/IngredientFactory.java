package com.example.firstproject.ioc2;

import org.springframework.stereotype.Component;

@Component // 해당 클래스의 객체를 만들고, 이를 IOC 컨테이너에 등록
public class IngredientFactory {
    public Ingredient get(String menu) {
        switch (menu) {
            case "돈까스":
                return new Pork2("한돈 등심");
            case "스테이크":
                return new Beef2("한우 꽃등심");
            case "크리스피 치킨":
                return new Chicken("국내산 10호 닭");
            default:
                return null;
        }
    }
}
