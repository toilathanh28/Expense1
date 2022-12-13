package com.example.coursework.model;

public class RecentData {
    String placeName;
    String tripName;
    String price;
    String riskAssessment;
    Integer imageUrl;

    public RecentData(String placeName, String tripName, String price, String riskAssessment,Integer imageUrl) {
        this.placeName = placeName;
        this.tripName = tripName;
        this.price = price;
        this.riskAssessment = riskAssessment;
        this.imageUrl = imageUrl;
    }

    public Integer getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(Integer imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getPlaceName() {
        return placeName;
    }

    public void setPlaceName(String placeName) {
        this.placeName = placeName;
    }

    public String getTripName() {
        return tripName;
    }

    public void setTripName(String tripName) {
        this.tripName= tripName;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getRiskAssessment() {
        return riskAssessment;
    }

    public void setRiskAssessment(String riskAssessment) {
        this.riskAssessment = riskAssessment;
    }
}
