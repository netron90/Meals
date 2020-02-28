package com.personnalize_design.meals.data.model;

import java.util.List;

public class AllCompanySearch {


    /**
     * message : request success
     * data : [{"companyName":"Paf Delice","companyCoverImage":"2265718036_5993df4700_z.jpg"}]
     */

    private String message;
    private List<CompanyData> data;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<CompanyData> getData() {
        return data;
    }

    public void setData(List<CompanyData> data) {
        this.data = data;
    }

    public static class CompanyData {
        /**
         * companyName : Paf Delice
         * companyCoverImage : 2265718036_5993df4700_z.jpg
         */

        private String companyName;
        private String companyCoverImage;

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
    }
}
