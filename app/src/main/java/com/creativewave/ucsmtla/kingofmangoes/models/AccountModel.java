package com.creativewave.ucsmtla.kingofmangoes.models;

public class AccountModel {

    Boolean saved=null;

    public static class Account{
       String id;
       String name,adressId,phno,distance,password,plantCty, verifyCode;
       int acre,width,height;

        public Account() {
        }

        public Account(String id, String name, String adressId, String phno, String distance, String password,String plantCty,
                      String verifyCode, int acre, int width, int height) {
            this.id = id;
            this.name = name;
            this.adressId = adressId;
            this.phno = phno;
            this.distance = distance;
            this.password = password;
            this.verifyCode = verifyCode;
            this.acre = acre;
            this.width = width;
            this.height = height;
            this.plantCty=plantCty;
        }
        public Account( String name, String adressId, String phno, String distance, String password,String plantCty,
                      String verifyCode, int acre, int width, int height) {
            this.name = name;
            this.adressId = adressId;
            this.phno = phno;
            this.distance = distance;
            this.password = password;
            this.verifyCode = verifyCode;
            this.acre = acre;
            this.width = width;
            this.height = height;
            this.plantCty=plantCty;
        }

        public String getPlantCty() {
            return plantCty;
        }

        public void setPlantCty(String plantCty) {
            this.plantCty = plantCty;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getAdressId() {
            return adressId;
        }

        public void setAdressId(String adressId) {
            this.adressId = adressId;
        }

        public String getPhno() {
            return phno;
        }

        public void setPhno(String phno) {
            this.phno = phno;
        }

        public String getDistance() {
            return distance;
        }

        public void setDistance(String distance) {
            this.distance = distance;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getVerifyCode() {
            return verifyCode;
        }

        public void setVerifyCode(String verifyCode) {
            this.verifyCode = verifyCode;
        }

        public int getAcre() {

            return acre;
        }

        public void setAcre(int acre) {
            this.acre = acre;
        }

        public int getWidth() {
            return width;
        }

        public void setWidth(int width) {
            this.width = width;
        }

        public int getHeight() {
            return height;
        }

        public void setHeight(int height) {
            this.height = height;
        }
    }

    public boolean isSave(Account stu){
        if (stu==null){
            saved=false;
        }else {

            try{
                FirebaseDB.getFirebaseDB().child("Account").push().setValue(stu);
                saved=true;
            }catch (Exception e){
                saved=false;
            }
        }
        return saved;
    }
    public boolean update(String id,Account stu){
        if (stu==null){
            saved=false;
        }else {

            try{
                FirebaseDB.getFirebaseDB().child("Account").child(id).setValue(stu);
                saved=true;
            }catch (Exception e){
                saved=false;
            }
        }
        return saved;
    }
}
