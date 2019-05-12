package com.example.projectui.entities;

public class DonnerPojo {

   private String name,Mobile,governorate,gender,bloodType,date,PaymentType,longitude,latitude;


   public DonnerPojo(String name, String mobile, String governorate, String gender, String bloodType, String date, String paymentType, String longitude, String latitude) {
      this.name = name;
      this.Mobile = mobile;
      this.governorate = governorate;
      this.gender = gender;
      this.bloodType = bloodType;
      this.date = date;
      this.PaymentType = paymentType;
      this.longitude = longitude;
      this.latitude = latitude;
   }



   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public String getMobile() {
      return Mobile;
   }

   public void setMobile(String mobile) {
      Mobile = mobile;
   }

   public String getGovernorate() {
      return governorate;
   }

   public void setGovernorate(String governorate) {
       governorate = governorate;
   }

   public String getGender() {
      return gender;
   }

   public void setGender(String gender) {
      this.gender = gender;
   }

   public String getBloodType() {
      return bloodType;
   }

   public void setBloodType(String bloodType) {
      this.bloodType = bloodType;
   }

   public String getdate() {
      return date;
   }

   public void setdate(String date) {
      date = date;
   }

   public String getPaymentType() {
      return PaymentType;
   }

   public void setPaymentType(String paymentType) {
      PaymentType = paymentType;
   }

   public String getLongitude() {
      return longitude;
   }

   public void setLongitude(String longitude) {
      this.longitude = longitude;
   }

   public String getLatitude() {
      return latitude;
   }

   public void setLatitude(String latitude) {
      this.latitude = latitude;
   }
}
