package com.example.keralavisiontaskorganizer;

public class NewCollectionModel {
    private String name;
    private String phoneNumber;
    private String discription;
    private String priority;

    private String allreadyDone;

    public String getAllreadyDoneUid() {
        return allreadyDoneUid;
    }

    public void setAllreadyDoneUid(String allreadyDoneUid) {
        this.allreadyDoneUid = allreadyDoneUid;
    }

    private String allreadyDoneUid;

    public String getAllreadyDone() {
        return allreadyDone;
    }

    public void setAllreadyDone(String allreadyDone) {
        this.allreadyDone = allreadyDone;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }


    public String getDiscription() {
        return discription;
    }

    public void setDiscription(String discription) {
        this.discription = discription;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }



    NewCollectionModel(){

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    NewCollectionModel(String cosumerNumber, String phoneNumber, String discription, String priority, String allreadyDone,String allreadyDoneUid){

        this.name = cosumerNumber;
        this.allreadyDone =  allreadyDone;
        this.phoneNumber = phoneNumber;
        this.discription = discription;
        this.priority = priority;
        this.allreadyDoneUid = allreadyDoneUid;

    }


}

