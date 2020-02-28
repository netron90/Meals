package com.personnalize_design.meals.data.model;

import java.util.List;

public class AllMealSelected {

    private static AllMealSelected instance;

    private List<MainMealSelectedModel> list;
    private String companyName;


    public AllMealSelected() {
    }

    public List<MainMealSelectedModel> getList() {
        return list;
    }

    public void setList(List<MainMealSelectedModel> list) {
        this.list = list;
    }

    public static AllMealSelected getInstance(){
        if(instance == null){
            instance = new AllMealSelected();
        }
        return instance;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
}
