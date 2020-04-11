package com.personnalize_design.meals.data.model;

public class CompanySuggestion {

    /**
     * message : request successfull
     * planName : {"_id":"5e787f5e6109b31fb8154055","planName":"novice"}
     */

    private String message;
    private PlanNameBean planName;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public PlanNameBean getPlanName() {
        return planName;
    }

    public void setPlanName(PlanNameBean planName) {
        this.planName = planName;
    }

    public static class PlanNameBean {
        /**
         * _id : 5e787f5e6109b31fb8154055
         * planName : novice
         */

        private String _id;
        private String planName;

        public String get_id() {
            return _id;
        }

        public void set_id(String _id) {
            this._id = _id;
        }

        public String getPlanName() {
            return planName;
        }

        public void setPlanName(String planName) {
            this.planName = planName;
        }
    }
}
