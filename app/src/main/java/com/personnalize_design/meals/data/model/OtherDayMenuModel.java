package com.personnalize_design.meals.data.model;

import java.util.List;

public class OtherDayMenuModel {


    /**
     * message : request success
     * data : {"otherMenu":[{"_id":"5e4bdc4af699ab13a8fa1202","companyName":"Paf Delice","category":"Autres Menus","mealImg":"/images/logo.png","mealName":"","mealPrice":"","mealPath":"","__v":0},{"_id":"5e4bdc4af699ab13a8fa1203","companyName":"Paf Delice","category":"Autres Menus","mealImg":"/images/logo.png","mealName":"","mealPrice":"","mealPath":"","__v":0},{"_id":"5e4bdc4af699ab13a8fa1204","companyName":"Paf Delice","category":"Autres Menus","mealImg":"/images/logo.png","mealName":"","mealPrice":"","mealPath":"","__v":0}],"accompagnement":["5e4bdc4af699ab13a8fa1205","5e4bdc4af699ab13a8fa1206","5e4bdc4af699ab13a8fa1207"],"_id":"5e4bdc49f699ab13a8fa1201","username":"Paf Delice","email":"fredyannra@gmail.com","contact":"+2296608384","category":"Menu Principal","mainMealImg":"/images/logo.png","mainMealName":"","mainMealPrice":"","coverImage":"","livraisonPrice":"1000 f CFA","mainMealImagePath":"","timeRegister":"","__v":2}
     */

    private String message;
    private DataBean data;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * otherMenu : [{"_id":"5e4bdc4af699ab13a8fa1202","companyName":"Paf Delice","category":"Autres Menus","mealImg":"/images/logo.png","mealName":"","mealPrice":"","mealPath":"","__v":0},{"_id":"5e4bdc4af699ab13a8fa1203","companyName":"Paf Delice","category":"Autres Menus","mealImg":"/images/logo.png","mealName":"","mealPrice":"","mealPath":"","__v":0},{"_id":"5e4bdc4af699ab13a8fa1204","companyName":"Paf Delice","category":"Autres Menus","mealImg":"/images/logo.png","mealName":"","mealPrice":"","mealPath":"","__v":0}]
         * accompagnement : ["5e4bdc4af699ab13a8fa1205","5e4bdc4af699ab13a8fa1206","5e4bdc4af699ab13a8fa1207"]
         * _id : 5e4bdc49f699ab13a8fa1201
         * username : Paf Delice
         * email : fredyannra@gmail.com
         * contact : +2296608384
         * category : Menu Principal
         * mainMealImg : /images/logo.png
         * mainMealName :
         * mainMealPrice :
         * coverImage :
         * livraisonPrice : 1000 f CFA
         * mainMealImagePath :
         * timeRegister :
         * __v : 2
         */

        private String _id;
        private String username;
        private String email;
        private String contact;
        private String category;
        private String mainMealImg;
        private String mainMealName;
        private String mainMealPrice;
        private String coverImage;
        private String livraisonPrice;
        private String mainMealImagePath;
        private String timeRegister;
        private int __v;
        private List<OtherMenuBean> otherMenu;
        private List<String> accompagnement;

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

        public String getCoverImage() {
            return coverImage;
        }

        public void setCoverImage(String coverImage) {
            this.coverImage = coverImage;
        }

        public String getLivraisonPrice() {
            return livraisonPrice;
        }

        public void setLivraisonPrice(String livraisonPrice) {
            this.livraisonPrice = livraisonPrice;
        }

        public String getMainMealImagePath() {
            return mainMealImagePath;
        }

        public void setMainMealImagePath(String mainMealImagePath) {
            this.mainMealImagePath = mainMealImagePath;
        }

        public String getTimeRegister() {
            return timeRegister;
        }

        public void setTimeRegister(String timeRegister) {
            this.timeRegister = timeRegister;
        }

        public int get__v() {
            return __v;
        }

        public void set__v(int __v) {
            this.__v = __v;
        }

        public List<OtherMenuBean> getOtherMenu() {
            return otherMenu;
        }

        public void setOtherMenu(List<OtherMenuBean> otherMenu) {
            this.otherMenu = otherMenu;
        }

        public List<String> getAccompagnement() {
            return accompagnement;
        }

        public void setAccompagnement(List<String> accompagnement) {
            this.accompagnement = accompagnement;
        }

        public static class OtherMenuBean {
            /**
             * _id : 5e4bdc4af699ab13a8fa1202
             * companyName : Paf Delice
             * category : Autres Menus
             * mealImg : /images/logo.png
             * mealName :
             * mealPrice :
             * mealPath :
             * __v : 0
             */

            private String _id;
            private String companyName;
            private String category;
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

            public String getCompanyName() {
                return companyName;
            }

            public void setCompanyName(String companyName) {
                this.companyName = companyName;
            }

            public String getCategory() {
                return category;
            }

            public void setCategory(String category) {
                this.category = category;
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
