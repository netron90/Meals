package com.personnalize_design.meals.data.model;

import java.util.List;

public class BillClientInformation
{
    private String totalMealsPrices;
    private String companyName;
    private String clientName;
    private String clientContact;
    private String clientLocalisation;
    private List<MealFacture> listMealInfo;

    public BillClientInformation(String totalMealsPrices, String companyName, String clientName, String clientContact, String clientLocalisation, List<MealFacture> listMealInfo) {
        this.totalMealsPrices = totalMealsPrices;
        this.companyName = companyName;
        this.clientName = clientName;
        this.clientContact = clientContact;
        this.clientLocalisation = clientLocalisation;
        this.listMealInfo = listMealInfo;
    }

    public String getTotalMealsPrices() {
        return totalMealsPrices;
    }

    public void setTotalMealsPrices(String totalMealsPrices) {
        this.totalMealsPrices = totalMealsPrices;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getClientContact() {
        return clientContact;
    }

    public void setClientContact(String clientContact) {
        this.clientContact = clientContact;
    }

    public String getClientLocalisation() {
        return clientLocalisation;
    }

    public void setClientLocalisation(String clientLocalisation) {
        this.clientLocalisation = clientLocalisation;
    }

    public List<MealFacture> getListMealInfo() {
        return listMealInfo;
    }

    public void setListMealInfo(List<MealFacture> listMealInfo) {
        this.listMealInfo = listMealInfo;
    }
}
