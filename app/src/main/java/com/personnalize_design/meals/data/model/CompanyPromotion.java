package com.personnalize_design.meals.data.model;

import java.util.List;

public class CompanyPromotion {


    /**
     * message : request success
     * companyEvent : [{"companyName":"Paf Delice","dateEventStart":"02/04/2020","dateEventEnd":"25/04/2020","eventTitle":"Réduction de 50%","eventDescription":"Avec 2 achats obtenez jusqu'à 50% de réduction."}]
     */

    private String message;
    private List<CompanyEventBean> companyEvent;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<CompanyEventBean> getCompanyEvent() {
        return companyEvent;
    }

    public void setCompanyEvent(List<CompanyEventBean> companyEvent) {
        this.companyEvent = companyEvent;
    }

    public static class CompanyEventBean {
        /**
         * companyName : Paf Delice
         * dateEventStart : 02/04/2020
         * dateEventEnd : 25/04/2020
         * eventTitle : Réduction de 50%
         * eventDescription : Avec 2 achats obtenez jusqu'à 50% de réduction.
         */

        private String companyName;
        private String dateEventStart;
        private String dateEventEnd;
        private String eventTitle;
        private String eventDescription;

        public String getCompanyName() {
            return companyName;
        }

        public void setCompanyName(String companyName) {
            this.companyName = companyName;
        }

        public String getDateEventStart() {
            return dateEventStart;
        }

        public void setDateEventStart(String dateEventStart) {
            this.dateEventStart = dateEventStart;
        }

        public String getDateEventEnd() {
            return dateEventEnd;
        }

        public void setDateEventEnd(String dateEventEnd) {
            this.dateEventEnd = dateEventEnd;
        }

        public String getEventTitle() {
            return eventTitle;
        }

        public void setEventTitle(String eventTitle) {
            this.eventTitle = eventTitle;
        }

        public String getEventDescription() {
            return eventDescription;
        }

        public void setEventDescription(String eventDescription) {
            this.eventDescription = eventDescription;
        }
    }
}
