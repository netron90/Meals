package com.personnalize_design.meals.data.model;

import java.util.List;

public class CompanyAccessCode {


    /**
     * message : Compagnie trouvee
     * companyData : {"workinDay":[],"otherMenu":[],"accompagnement":[],"catalog":[],"pub":[],"_id":"5e8c6e5cee150911b452910b","username":"Jo","email":"jo@live.com","contact":"66008384","type":"userapp","subscription":"enable","subscriberEndTime":"2020-05-07T12:13:16.491Z","codeDacces":"8731fb","__v":0}
     */

    private String message;
    private CompanyDataBean companyData;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public CompanyDataBean getCompanyData() {
        return companyData;
    }

    public void setCompanyData(CompanyDataBean companyData) {
        this.companyData = companyData;
    }

    public static class CompanyDataBean {
        /**
         * workinDay : []
         * otherMenu : []
         * accompagnement : []
         * catalog : []
         * pub : []
         * _id : 5e8c6e5cee150911b452910b
         * username : Jo
         * email : jo@live.com
         * contact : 66008384
         * type : userapp
         * subscription : enable
         * subscriberEndTime : 2020-05-07T12:13:16.491Z
         * codeDacces : 8731fb
         * __v : 0
         */

        private String _id;
        private String username;
        private String email;
        private String contact;
        private String type;
        private String subscription;
        private String subscriberEndTime;
        private String codeDacces;
        private int __v;
        private List<?> workinDay;
        private List<?> otherMenu;
        private List<?> accompagnement;
        private List<?> catalog;
        private List<?> pub;

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

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getSubscription() {
            return subscription;
        }

        public void setSubscription(String subscription) {
            this.subscription = subscription;
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

        public List<?> getWorkinDay() {
            return workinDay;
        }

        public void setWorkinDay(List<?> workinDay) {
            this.workinDay = workinDay;
        }

        public List<?> getOtherMenu() {
            return otherMenu;
        }

        public void setOtherMenu(List<?> otherMenu) {
            this.otherMenu = otherMenu;
        }

        public List<?> getAccompagnement() {
            return accompagnement;
        }

        public void setAccompagnement(List<?> accompagnement) {
            this.accompagnement = accompagnement;
        }

        public List<?> getCatalog() {
            return catalog;
        }

        public void setCatalog(List<?> catalog) {
            this.catalog = catalog;
        }

        public List<?> getPub() {
            return pub;
        }

        public void setPub(List<?> pub) {
            this.pub = pub;
        }
    }
}
