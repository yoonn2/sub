package com.example.firstproject.ioc;

public class Chef {

    IngredientFactory ingredientFactory;

    public Chef(IngredientFactory ingredientFactory){
        this.ingredientFactory = ingredientFactory;
    }

    public String cookCutlet(String menu){
        //재료 준비
        Pork pork = new Pork("한돈 등심");
        //요리 반환
        return pork.getName() + "으로 만든 " + menu;
    }

    public String cookSteak(String menu){
        Beef beef = new Beef("미국 소");
        //요리 반환
        return beef.getName() + "으로 만든 " + menu;
    }

}

