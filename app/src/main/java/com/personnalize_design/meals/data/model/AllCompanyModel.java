package com.personnalize_design.meals.data.model;

import java.util.List;

public class AllCompanyModel {

    /**
     * message : request success
     * data : [{"workinDay":[{"_id":"5e903ce6c6efb5475408b9c1","dayName":"Lundi","__v":0},{"_id":"5e903ce6c6efb5475408b9c2","dayName":"Mercredi","__v":0},{"_id":"5e903ce6c6efb5475408b9c3","dayName":"Jeudi","__v":0},{"_id":"5e903ce6c6efb5475408b9c4","dayName":"Vendredi","__v":0},{"_id":"5e903ce6c6efb5475408b9c5","dayName":"Samedi","__v":0}],"_id":"5e787f5e6109b31fb8154055","username":"Paf Delice","email":"fredyannra@gmail.com","contact":"+2296608384","category":"Menu Principal","localisation":"AKPAKPA, von face mairie SEGBEYA","mainMealImg":"http://res.cloudinary.com/dawhgtx0h/image/upload/v1585153811/syimkby0ikptkmyb5zbi.jpg","mainMealName":"Salade d'Avocat","mainMealPrice":"1500f CFA","livraisonPrice":"1000","coverImage":"/uploads/5e787f5e6109b31fb8154055\\2265718036_5993df4700_z.jpg","coverImagePath":"/uploads/5e787f5e6109b31fb8154055\\2265718036_5993df4700_z.jpg"}]
     */

    private String message;
    private List<DataBean> data;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * workinDay : [{"_id":"5e903ce6c6efb5475408b9c1","dayName":"Lundi","__v":0},{"_id":"5e903ce6c6efb5475408b9c2","dayName":"Mercredi","__v":0},{"_id":"5e903ce6c6efb5475408b9c3","dayName":"Jeudi","__v":0},{"_id":"5e903ce6c6efb5475408b9c4","dayName":"Vendredi","__v":0},{"_id":"5e903ce6c6efb5475408b9c5","dayName":"Samedi","__v":0}]
         * _id : 5e787f5e6109b31fb8154055
         * username : Paf Delice
         * email : fredyannra@gmail.com
         * contact : +2296608384
         * category : Menu Principal
         * localisation : AKPAKPA, von face mairie SEGBEYA
         * mainMealImg : http://res.cloudinary.com/dawhgtx0h/image/upload/v1585153811/syimkby0ikptkmyb5zbi.jpg
         * mainMealName : Salade d'Avocat
         * mainMealPrice : 1500f CFA
         * livraisonPrice : 1000
         * coverImage : /uploads/5e787f5e6109b31fb8154055\2265718036_5993df4700_z.jpg
         * coverImagePath : /uploads/5e787f5e6109b31fb8154055\2265718036_5993df4700_z.jpg
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
        private String coverImagePath;
        private List<WorkinDayBean> workinDay;

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

        public String getCoverImagePath() {
            return coverImagePath;
        }

        public void setCoverImagePath(String coverImagePath) {
            this.coverImagePath = coverImagePath;
        }

        public List<WorkinDayBean> getWorkinDay() {
            return workinDay;
        }

        public void setWorkinDay(List<WorkinDayBean> workinDay) {
            this.workinDay = workinDay;
        }

        public static class WorkinDayBean {
            /**
             * _id : 5e903ce6c6efb5475408b9c1
             * dayName : Lundi
             * __v : 0
             */

            private String _id;
            private String dayName;
            private int __v;

            public String get_id() {
                return _id;
            }

            public void set_id(String _id) {
                this._id = _id;
            }

            public String getDayName() {
                return dayName;
            }

            public void setDayName(String dayName) {
                this.dayName = dayName;
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
