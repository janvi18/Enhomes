package com.e_society.model;

public class NonMemberLangModel {
    String id, houseId, nonMemberName, arrivingTime, isVisited, pickup, status;

    public String getHouseId() {
        return houseId;
    }

    public void setHouseId(String houseId) {
        this.houseId = houseId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNonMemberName() {
        return nonMemberName;
    }

    public void setNonMemberName(String nonMemberName) {
        this.nonMemberName = nonMemberName;
    }

    public String getArrivingTime() {
        return arrivingTime;
    }

    public void setArrivingTime(String arrivingTime) {
        this.arrivingTime = arrivingTime;
    }

    public String getIsVisited() {
        return isVisited;
    }

    public void setIsVisited(String isVisited) {
        this.isVisited = isVisited;
    }

    public String getPickup() {
        return pickup;
    }

    public void setPickup(String pickup) {
        this.pickup = pickup;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
