package com.example.keralavisiontaskorganizer;

public class NewComplaintModel {


    private String name;
    private String phoneNumber;
    private String address;
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

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    private String task;


    NewComplaintModel(){

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    NewComplaintModel(String name, String phoneNumber, String address, String discription, String priority, String task, String allreadyDone,String allreadyDoneUid){

        this.name = name;
        this.allreadyDone =  allreadyDone;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.discription = discription;
        this.priority = priority;
        this.task = task;
        this.allreadyDoneUid = allreadyDoneUid;
    }


}

