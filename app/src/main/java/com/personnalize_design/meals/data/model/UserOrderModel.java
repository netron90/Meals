package com.personnalize_design.meals.data.model;


import java.util.List;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;


public class UserOrderModel {


    /**
     * message : user order success
     * todayDate : 25/Feb/2020
     * companyName : Paf Delice
     * companyContact : +2296608384
     * companyLocalisation : AKPAKPA, von face mairie SEGBEYA
     * mealList : [{"nomDeLaBouf":"Vin Mousseux","prixUnitaireDeLaBouf":"2500f CFA","imageDeLaBouf":"/uploads/restaurant-love-romantic-dinner-3044.jpg","quantiteDeLaBouf":"1"},{"nomDeLaBouf":"Jus d'orange","prixUnitaireDeLaBouf":"250f CFA","imageDeLaBouf":"/uploads/kk.png","quantiteDeLaBouf":"2"}]
     */


    private int id;
    private String message;
    private String todayDate;
    private String companyName;
    private String companyContact;
    private String companyLocalisation;
    private List<MealListBean> mealList;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTodayDate() {
        return todayDate;
    }

    public void setTodayDate(String todayDate) {
        this.todayDate = todayDate;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyContact() {
        return companyContact;
    }

    public void setCompanyContact(String companyContact) {
        this.companyContact = companyContact;
    }

    public String getCompanyLocalisation() {
        return companyLocalisation;
    }

    public void setCompanyLocalisation(String companyLocalisation) {
        this.companyLocalisation = companyLocalisation;
    }

    public List<MealListBean> getMealList() {
        return mealList;
    }

    public void setMealList(List<MealListBean> mealList) {
        this.mealList = mealList;
    }

    @Entity(tableName = "user_order")
    public static class UserOrder{
        /**
         * message : user order success
         * todayDate : 25/Feb/2020
         * companyName : Paf Delice
         * companyContact : +2296608384
         * companyLocalisation : AKPAKPA, von face mairie SEGBEYA
         * mealList : [{"nomDeLaBouf":"Vin Mousseux","prixUnitaireDeLaBouf":"2500f CFA","imageDeLaBouf":"/uploads/restaurant-love-romantic-dinner-3044.jpg","quantiteDeLaBouf":"1"},{"nomDeLaBouf":"Jus d'orange","prixUnitaireDeLaBouf":"250f CFA","imageDeLaBouf":"/uploads/kk.png","quantiteDeLaBouf":"2"}]
         */


        @PrimaryKey(autoGenerate = true)
        public int id;

        @ColumnInfo(name = "today_date")
        public String todayDate;
        @ColumnInfo(name = "company_name")
        public String companyName;
        @ColumnInfo(name = "company_contact")
        public String companyContact;
        @ColumnInfo(name = "company_localisation")
        public String companyLocalisation;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getTodayDate() {
            return todayDate;
        }

        public void setTodayDate(String todayDate) {
            this.todayDate = todayDate;
        }

        public String getCompanyName() {
            return companyName;
        }

        public void setCompanyName(String companyName) {
            this.companyName = companyName;
        }

        public String getCompanyContact() {
            return companyContact;
        }

        public void setCompanyContact(String companyContact) {
            this.companyContact = companyContact;
        }

        public String getCompanyLocalisation() {
            return companyLocalisation;
        }

        public void setCompanyLocalisation(String companyLocalisation) {
            this.companyLocalisation = companyLocalisation;
        }
    }

    @Entity(tableName = "user_meal_list", foreignKeys = @ForeignKey(entity = UserOrder.class, parentColumns = "id", childColumns = "id_list"))
    public static class MealListBean {
        /**
         * nomDeLaBouf : Vin Mousseux
         * prixUnitaireDeLaBouf : 2500f CFA
         * imageDeLaBouf : /uploads/restaurant-love-romantic-dinner-3044.jpg
         * quantiteDeLaBouf : 1
         */

        @PrimaryKey(autoGenerate = true)
        public int id;

        @ColumnInfo(name = "id_list")
        public int idList;

        @ColumnInfo(name = "nom_nourriture")
        public String nomDeLaBouf;

        @ColumnInfo(name = "prix_nourriture")
        public String prixUnitaireDeLaBouf;

        @ColumnInfo(name = "image_nourriture")
        public String imageDeLaBouf;

        @ColumnInfo(name = "quantity_nourriture")
        public String quantiteDeLaBouf;

        public String getNomDeLaBouf() {
            return nomDeLaBouf;
        }

        public void setNomDeLaBouf(String nomDeLaBouf) {
            this.nomDeLaBouf = nomDeLaBouf;
        }

        public String getPrixUnitaireDeLaBouf() {
            return prixUnitaireDeLaBouf;
        }

        public void setPrixUnitaireDeLaBouf(String prixUnitaireDeLaBouf) {
            this.prixUnitaireDeLaBouf = prixUnitaireDeLaBouf;
        }

        public String getImageDeLaBouf() {
            return imageDeLaBouf;
        }

        public void setImageDeLaBouf(String imageDeLaBouf) {
            this.imageDeLaBouf = imageDeLaBouf;
        }

        public String getQuantiteDeLaBouf() {
            return quantiteDeLaBouf;
        }

        public void setQuantiteDeLaBouf(String quantiteDeLaBouf) {
            this.quantiteDeLaBouf = quantiteDeLaBouf;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getIdList() {
            return idList;
        }

        public void setIdList(int idList) {
            this.idList = idList;
        }
    }
}
