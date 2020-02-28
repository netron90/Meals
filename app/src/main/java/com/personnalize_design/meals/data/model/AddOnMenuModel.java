package com.personnalize_design.meals.data.model;

import java.util.List;

public class AddOnMenuModel {


    /**
     * message : request success
     * data : {"otherMenu":["5e4e3ecc050cc60017d6a319","5e4e3ecc050cc60017d6a31a","5e4e3ecc050cc60017d6a31b"],"accompagnement":[{"_id":"5e4e3ecd050cc60017d6a320","category":"Boissons ou autres accompagnements","addOnImg":"/images/logo.png","addOnName":"","addOnPrice":"","addOnPath":"","__v":0}],"_id":"5e4e3ecc050cc60017d6a318","username":"Paf Delice","email":"fredyannra@gmail.com","contact":"+2296608384","category":"Menu Principal","mainMealImg":"/uploads/bowl-of-vegetable-salad-and-fruits-936611.jpg","mainMealName":"Salade d'Avocat","mainMealPrice":"5000f CFA","coverImage":"","livraisonPrice":"1000 f CFA","mainMealImagePath":"assets/uploads/bowl-of-vegetable-salad-and-fruits-936611.jpg","timeRegister":"","__v":2}
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
         * otherMenu : ["5e4e3ecc050cc60017d6a319","5e4e3ecc050cc60017d6a31a","5e4e3ecc050cc60017d6a31b"]
         * accompagnement : [{"_id":"5e4e3ecd050cc60017d6a320","category":"Boissons ou autres accompagnements","addOnImg":"/images/logo.png","addOnName":"","addOnPrice":"","addOnPath":"","__v":0}]
         * _id : 5e4e3ecc050cc60017d6a318
         * username : Paf Delice
         * email : fredyannra@gmail.com
         * contact : +2296608384
         * category : Menu Principal
         * mainMealImg : /uploads/bowl-of-vegetable-salad-and-fruits-936611.jpg
         * mainMealName : Salade d'Avocat
         * mainMealPrice : 5000f CFA
         * coverImage :
         * livraisonPrice : 1000 f CFA
         * mainMealImagePath : assets/uploads/bowl-of-vegetable-salad-and-fruits-936611.jpg
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
        private List<String> otherMenu;
        private List<AccompagnementBean> accompagnement;

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

        public List<String> getOtherMenu() {
            return otherMenu;
        }

        public void setOtherMenu(List<String> otherMenu) {
            this.otherMenu = otherMenu;
        }

        public List<AccompagnementBean> getAccompagnement() {
            return accompagnement;
        }

        public void setAccompagnement(List<AccompagnementBean> accompagnement) {
            this.accompagnement = accompagnement;
        }

        public static class AccompagnementBean {
            /**
             * _id : 5e4e3ecd050cc60017d6a320
             * category : Boissons ou autres accompagnements
             * addOnImg : /images/logo.png
             * addOnName :
             * addOnPrice :
             * addOnPath :
             * __v : 0
             */

            private String _id;
            private String category;
            private String addOnImg;
            private String addOnName;
            private String addOnPrice;
            private String addOnPath;
            private int __v;

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

            public String getAddOnImg() {
                return addOnImg;
            }

            public void setAddOnImg(String addOnImg) {
                this.addOnImg = addOnImg;
            }

            public String getAddOnName() {
                return addOnName;
            }

            public void setAddOnName(String addOnName) {
                this.addOnName = addOnName;
            }

            public String getAddOnPrice() {
                return addOnPrice;
            }

            public void setAddOnPrice(String addOnPrice) {
                this.addOnPrice = addOnPrice;
            }

            public String getAddOnPath() {
                return addOnPath;
            }

            public void setAddOnPath(String addOnPath) {
                this.addOnPath = addOnPath;
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
