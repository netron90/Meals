package com.personnalize_design.meals.data.model;

import java.util.List;

public class OneCompanySearchModel {



    /**
     * message : request success
     * data : [{"companyName":"Paf Delice","companyCoverImage":"/uploads/5e787f5e6109b31fb8154055\\2265718036_5993df4700_z.jpg","companyMail":"fredyannra@gmail.com","companyContact":"+2296608384","companyLocalisation":"AKPAKPA, von face mairie SEGBEYA","companyWorkinDay":[{"_id":"5e903ce6c6efb5475408b9c1","dayName":"Lundi","__v":0},{"_id":"5e903ce6c6efb5475408b9c2","dayName":"Mercredi","__v":0},{"_id":"5e903ce6c6efb5475408b9c3","dayName":"Jeudi","__v":0},{"_id":"5e903ce6c6efb5475408b9c4","dayName":"Vendredi","__v":0},{"_id":"5e903ce6c6efb5475408b9c5","dayName":"Samedi","__v":0}],"companyCatalog":["5e904f874181be187c751d38"]}]
     */

    private String message;
    private List<OneCompanySearchModel.CompanyData> data;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<OneCompanySearchModel.CompanyData> getData() {
        return data;
    }

    public void setData(List<OneCompanySearchModel.CompanyData> data) {
        this.data = data;
    }

    public static class CompanyData {
        /**
         * companyName : Paf Delice
         * companyCoverImage : /uploads/5e787f5e6109b31fb8154055\2265718036_5993df4700_z.jpg
         * companyMail : fredyannra@gmail.com
         * companyContact : +2296608384
         * companyLocalisation : AKPAKPA, von face mairie SEGBEYA
         * companyWorkinDay : [{"_id":"5e903ce6c6efb5475408b9c1","dayName":"Lundi","__v":0},{"_id":"5e903ce6c6efb5475408b9c2","dayName":"Mercredi","__v":0},{"_id":"5e903ce6c6efb5475408b9c3","dayName":"Jeudi","__v":0},{"_id":"5e903ce6c6efb5475408b9c4","dayName":"Vendredi","__v":0},{"_id":"5e903ce6c6efb5475408b9c5","dayName":"Samedi","__v":0}]
         * companyCatalog : ["5e904f874181be187c751d38"]
         */

        private String companyName;
        private String companyCoverImage;
        private String companyMail;
        private String companyContact;
        private String companyLocalisation;
        private List<AllCompanySearch.CompanyData.CompanyWorkinDayBean> companyWorkinDay;
        private List<String> companyCatalog;

        public String getCompanyName() {
            return companyName;
        }

        public void setCompanyName(String companyName) {
            this.companyName = companyName;
        }

        public String getCompanyCoverImage() {
            return companyCoverImage;
        }

        public void setCompanyCoverImage(String companyCoverImage) {
            this.companyCoverImage = companyCoverImage;
        }

        public String getCompanyMail() {
            return companyMail;
        }

        public void setCompanyMail(String companyMail) {
            this.companyMail = companyMail;
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

        public List<AllCompanySearch.CompanyData.CompanyWorkinDayBean> getCompanyWorkinDay() {
            return companyWorkinDay;
        }

        public void setCompanyWorkinDay(List<AllCompanySearch.CompanyData.CompanyWorkinDayBean> companyWorkinDay) {
            this.companyWorkinDay = companyWorkinDay;
        }

        public List<String> getCompanyCatalog() {
            return companyCatalog;
        }

        public void setCompanyCatalog(List<String> companyCatalog) {
            this.companyCatalog = companyCatalog;
        }

        public static class CompanyWorkinDayBean {
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
