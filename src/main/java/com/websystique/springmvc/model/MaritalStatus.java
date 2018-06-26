package com.websystique.springmvc.model;

import javax.persistence.*;

    @Entity
    @Table(name = "marital_status")
    public class MaritalStatus {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "marital_status_id")
        private Integer maritalStatusId;

        @Column(name = "status")
        private String status;


        //Getter and Setter

        public Integer getMaritalStatusId() {
            return maritalStatusId;
        }

        public void setMaritalStatusId(Integer maritalStatusId) {
            this.maritalStatusId = maritalStatusId;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }
}
