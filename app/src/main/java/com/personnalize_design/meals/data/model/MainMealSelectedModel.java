package com.personnalize_design.meals.data.model;


import android.os.Parcel;
import android.os.Parcelable;

public class MainMealSelectedModel implements Parcelable {

    private String companyNameService;
    private String mainMealImage;
    private String mainMealName;
    private String mainMealPrice;
    private String companyCoverImage;
    private String companyDeliveryPrice;

    public MainMealSelectedModel() {

    }

    public MainMealSelectedModel(String mainMealName, String mainMealImage, String mainMealPrice, String companyDeliveryPrice) {
        this.mainMealName = mainMealName;
        this.mainMealImage = mainMealImage;
        this.mainMealPrice = mainMealPrice;
        this.companyDeliveryPrice = companyDeliveryPrice;
    }

    public MainMealSelectedModel(String companyNameService, String mainMealImage, String mainMealName, String mainMealPrice, String companyCoverImage) {
        this.companyNameService = companyNameService;
        this.mainMealImage = mainMealImage;
        this.mainMealName = mainMealName;
        this.mainMealPrice = mainMealPrice;
        this.companyCoverImage = companyCoverImage;
    }


    protected MainMealSelectedModel(Parcel in) {
        companyNameService = in.readString();
        mainMealImage = in.readString();
        mainMealName = in.readString();
        mainMealPrice = in.readString();
        companyCoverImage = in.readString();
        companyDeliveryPrice = in.readString();
    }

    public static final Creator<MainMealSelectedModel> CREATOR = new Creator<MainMealSelectedModel>() {
        @Override
        public MainMealSelectedModel createFromParcel(Parcel in) {
            return new MainMealSelectedModel(in);
        }

        @Override
        public MainMealSelectedModel[] newArray(int size) {
            return new MainMealSelectedModel[size];
        }
    };

    public String getCompanyNameService() {
        return companyNameService;
    }

    public void setCompanyNameService(String companyNameService) {
        this.companyNameService = companyNameService;
    }

    public String getMainMealImage() {
        return mainMealImage;
    }

    public void setMainMealImage(String mainMealImage) {
        this.mainMealImage = mainMealImage;
    }

    public String getMainMealName() {
        return mainMealName;
    }

    public void setMainMealName(String mainMealName) {
        this.mainMealName = mainMealName;
    }

    public String getMainMealPrice() {
        return mainMealPrice;
    }

    public void setMainMealPrice(String mainMealPrice) {
        this.mainMealPrice = mainMealPrice;
    }

    public String getCompanyCoverImage() {
        return companyCoverImage;
    }

    public void setCompanyCoverImage(String companyCoverImage) {
        this.companyCoverImage = companyCoverImage;
    }

    public String getCompanyDeliveryPrice() {
        return companyDeliveryPrice;
    }

    public void setCompanyDeliveryPrice(String companyDeliveryPrice) {
        this.companyDeliveryPrice = companyDeliveryPrice;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(companyNameService);
        parcel.writeString(mainMealImage);
        parcel.writeString(mainMealName);
        parcel.writeString(mainMealPrice);
        parcel.writeString(companyCoverImage);
        parcel.writeString(companyDeliveryPrice);
    }
}
