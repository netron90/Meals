package com.personnalize_design.meals.data.model;

public class OneCompanySearchModel {


    /**
     * message : request success
     * data : {"companyName":"Paf Delice","companyCoverImage":"/css/2265718036_5993df4700_z.jpg"}
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
         * companyName : Paf Delice
         * companyCoverImage : /css/2265718036_5993df4700_z.jpg
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
