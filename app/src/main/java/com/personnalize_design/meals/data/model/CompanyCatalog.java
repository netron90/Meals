package com.personnalize_design.meals.data.model;

import java.util.List;

public class CompanyCatalog {

    /**
     * message : request successfull
     * userData : {"otherMenu":["5e7a1922cc0f9a00170b322a","5e7a1922cc0f9a00170b322b","5e7a1922cc0f9a00170b322c"],"accompagnement":["5e7a1922cc0f9a00170b3230","5e7a1922cc0f9a00170b3231","5e7a1922cc0f9a00170b3232"],"catalog":[{"mealsList":[{"_id":"5e7a2609a517cb001738537c","mealImg":"uploads/5e7a1922cc0f9a00170b3229-brun_coffee.png","mealName":"Cafe","mealPrice":"500f CFA","mealPath":"assets/uploads/5e7a1922cc0f9a00170b3229-brun_coffee.png","__v":0}],"_id":"5e7a2609a517cb001738537a","category":"Déjeuner","categoryRange":2,"__v":1}],"_id":"5e7a1922cc0f9a00170b3229","username":"Paf Delice","email":"fredyannra@gmail.com","contact":"+2296608384","category":"Menu Principal","localisation":"AKPAKPA, von face mairie SEGBEYA","mainMealImg":"/uploads/5e7a1922cc0f9a00170b3229-bowl-of-vegetable-salad-and-fruits-936611.jpg","mainMealName":"Salade d'Avocat","mainMealPrice":"1500f CFA","livraisonPrice":"1000","coverImage":"/NaN_2265718036_5993df4700_z.jpg","mainMealImagePath":"assets/uploads/5e7a1922cc0f9a00170b3229-bowl-of-vegetable-salad-and-fruits-936611.jpg","endOrderHour":"Tue Mar 24 2020 10:30:00 GMT+0000 (Coordinated Universal Time)","trialPlan":false,"subscription":"enable","planName":"waiter","subscriberEndTime":"2020-03-27T14:28:50.131Z","codeDacces":"Paf Delice","__v":3,"coverImagePath":"/css//NaN_2265718036_5993df4700_z.jpg"}
     */

    private String message;
    private UserDataBean userData;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public UserDataBean getUserData() {
        return userData;
    }

    public void setUserData(UserDataBean userData) {
        this.userData = userData;
    }

    public static class UserDataBean {
        /**
         * otherMenu : ["5e7a1922cc0f9a00170b322a","5e7a1922cc0f9a00170b322b","5e7a1922cc0f9a00170b322c"]
         * accompagnement : ["5e7a1922cc0f9a00170b3230","5e7a1922cc0f9a00170b3231","5e7a1922cc0f9a00170b3232"]
         * catalog : [{"mealsList":[{"_id":"5e7a2609a517cb001738537c","mealImg":"uploads/5e7a1922cc0f9a00170b3229-brun_coffee.png","mealName":"Cafe","mealPrice":"500f CFA","mealPath":"assets/uploads/5e7a1922cc0f9a00170b3229-brun_coffee.png","__v":0}],"_id":"5e7a2609a517cb001738537a","category":"Déjeuner","categoryRange":2,"__v":1}]
         * _id : 5e7a1922cc0f9a00170b3229
         * username : Paf Delice
         * email : fredyannra@gmail.com
         * contact : +2296608384
         * category : Menu Principal
         * localisation : AKPAKPA, von face mairie SEGBEYA
         * mainMealImg : /uploads/5e7a1922cc0f9a00170b3229-bowl-of-vegetable-salad-and-fruits-936611.jpg
         * mainMealName : Salade d'Avocat
         * mainMealPrice : 1500f CFA
         * livraisonPrice : 1000
         * coverImage : /NaN_2265718036_5993df4700_z.jpg
         * mainMealImagePath : assets/uploads/5e7a1922cc0f9a00170b3229-bowl-of-vegetable-salad-and-fruits-936611.jpg
         * endOrderHour : Tue Mar 24 2020 10:30:00 GMT+0000 (Coordinated Universal Time)
         * trialPlan : false
         * subscription : enable
         * planName : waiter
         * subscriberEndTime : 2020-03-27T14:28:50.131Z
         * codeDacces : Paf Delice
         * __v : 3
         * coverImagePath : /css//NaN_2265718036_5993df4700_z.jpg
         */

        private String _id;
        private String username;
        private String email;
        private String contact;
        private String category;
        private String localisation;
        private String mainMealImg;
        private String mainMealName;
        private String mainMealPrice;
        private String livraisonPrice;
        private String coverImage;
        private String mainMealImagePath;
        private String endOrderHour;
        private boolean trialPlan;
        private String subscription;
        private String planName;
        private String subscriberEndTime;
        private String codeDacces;
        private int __v;
        private String coverImagePath;
        private List<String> otherMenu;
        private List<String> accompagnement;
        private List<CatalogBean> catalog;

        public String get_id() {
            return _id;
        }

        public void set_id(String _id) {
            this._id = _id;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getContact() {
            return contact;
        }

        public void setContact(String contact) {
            this.contact = contact;
        }

        public String getCategory() {
            return category;
        }

        public void setCategory(String category) {
            this.category = category;
        }

        public String getLocalisation() {
            return localisation;
        }

        public void setLocalisation(String localisation) {
            this.localisation = localisation;
        }

        public String getMainMealImg() {
            return mainMealImg;
        }

        public void setMainMealImg(String mainMealImg) {
            this.mainMealImg = mainMealImg;
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

        public String getLivraisonPrice() {
            return livraisonPrice;
        }

        public void setLivraisonPrice(String livraisonPrice) {
            this.livraisonPrice = livraisonPrice;
        }

        public String getCoverImage() {
            return coverImage;
        }

        public void setCoverImage(String coverImage) {
            this.coverImage = coverImage;
        }

        public String getMainMealImagePath() {
            return mainMealImagePath;
        }

        public void setMainMealImagePath(String mainMealImagePath) {
            this.mainMealImagePath = mainMealImagePath;
        }

        public String getEndOrderHour() {
            return endOrderHour;
        }

        public void setEndOrderHour(String endOrderHour) {
            this.endOrderHour = endOrderHour;
        }

        public boolean isTrialPlan() {
            return trialPlan;
        }

        public void setTrialPlan(boolean trialPlan) {
            this.trialPlan = trialPlan;
        }

        public String getSubscription() {
            return subscription;
        }

        public void setSubscription(String subscription) {
            this.subscription = subscription;
        }

        public String getPlanName() {
            return planName;
        }

        public void setPlanName(String planName) {
            this.planName = planName;
        }

        public String getSubscriberEndTime() {
            return subscriberEndTime;
        }

        public void setSubscriberEndTime(String subscriberEndTime) {
            this.subscriberEndTime = subscriberEndTime;
        }

        public String getCodeDacces() {
            return codeDacces;
        }

        public void setCodeDacces(String codeDacces) {
            this.codeDacces = codeDacces;
        }

        public int get__v() {
            return __v;
        }

        public void set__v(int __v) {
            this.__v = __v;
        }

        public String getCoverImagePath() {
            return coverImagePath;
        }

        public void setCoverImagePath(String coverImagePath) {
            this.coverImagePath = coverImagePath;
        }

        public List<String> getOtherMenu() {
            return otherMenu;
        }

        public void setOtherMenu(List<String> otherMenu) {
            this.otherMenu = otherMenu;
        }

        public List<String> getAccompagnement() {
            return accompagnement;
        }

        public void setAccompagnement(List<String> accompagnement) {
            this.accompagnement = accompagnement;
        }

        public List<CatalogBean> getCatalog() {
            return catalog;
        }

        public void setCatalog(List<CatalogBean> catalog) {
            this.catalog = catalog;
        }

        public static class CatalogBean {
            /**
             * mealsList : [{"_id":"5e7a2609a517cb001738537c","mealImg":"uploads/5e7a1922cc0f9a00170b3229-brun_coffee.png","mealName":"Cafe","mealPrice":"500f CFA","mealPath":"assets/uploads/5e7a1922cc0f9a00170b3229-brun_coffee.png","__v":0}]
             * _id : 5e7a2609a517cb001738537a
             * category : Déjeuner
             * categoryRange : 2
             * __v : 1
             */

            private String _id;
            private String category;
            private int categoryRange;
            private int __v;
            private List<MealsListBean> mealsList;

            public String get_id() {
                return _id;
            }

            public void set_id(String _id) {
                this._id = _id;
            }

            public String getCategory() {
                return category;
            }

            public void setCategory(String category) {
                this.category = category;
            }

            public int getCategoryRange() {
                return categoryRange;
            }

            public void setCategoryRange(int categoryRange) {
                this.categoryRange = categoryRange;
            }

            public int get__v() {
                return __v;
            }

            public void set__v(int __v) {
                this.__v = __v;
            }

            public List<MealsListBean> getMealsList() {
                return mealsList;
            }

            public void setMealsList(List<MealsListBean> mealsList) {
                this.mealsList = mealsList;
            }

            public static class MealsListBean {
                /**
                 * _id : 5e7a2609a517cb001738537c
                 * mealImg : uploads/5e7a1922cc0f9a00170b3229-brun_coffee.png
                 * mealName : Cafe
                 * mealPrice : 500f CFA
                 * mealPath : assets/uploads/5e7a1922cc0f9a00170b3229-brun_coffee.png
                 * __v : 0
                 */

                private String _id;
                private String mealImg;
                private String mealName;
                private String mealPrice;
                private String mealPath;
                private int __v;

                public String get_id() {
                    return _id;
                }

                public void set_id(String _id) {
                    this._id = _id;
                }

                public String getMealImg() {
                    return mealImg;
                }

                public void setMealImg(String mealImg) {
                    this.mealImg = mealImg;
                }

                public String getMealName() {
                    return mealName;
                }

                public void setMealName(String mealName) {
                    this.mealName = mealName;
                }

                public String getMealPrice() {
                    return mealPrice;
                }

                public void setMealPrice(String mealPrice) {
                    this.mealPrice = mealPrice;
                }

                public String getMealPath() {
                    return mealPath;
                }

                public void setMealPath(String mealPath) {
                    this.mealPath = mealPath;
                }

                public int get__v() {
                    return __v;
                }

                public void set__v(int __v) {
                    this.__v = __v;
                }
            }
        }
    }
}
