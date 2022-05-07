package com.example.ambulanceapp;

public class confirmRide {
    String fee,service_type,hospital_name;

    public confirmRide(String fee, String service_type,String hospital_name) {
        this.fee = fee;
        this.service_type = service_type;
        this.hospital_name = hospital_name;
    }

    public String getFee() {
        return fee;
    }

    public void setFee(String fee) {
        this.fee = fee;
    }

    public String getService_type() {
        return service_type;
    }

    public void setService_type(String service_type) {
        this.service_type = service_type;
    }

    public String getHospital_name() {
        return hospital_name;
    }

    public void setHospital_name(String hospital_name) {
        this.hospital_name = hospital_name;
    }
}
