package com.creativewave.ucsmtla.kingofmangoes.models;

public class Advisor_Model {
    Boolean saved=null;

    public static class Advisor{
        String adv_id;
        String adv_name,adv_adressId,adv_phno,adv_experience,adv_photo,adv_password;

        public String getAdv_id() {
            return adv_id;
        }

        public void setAdv_id(String adv_id) {
            this.adv_id = adv_id;
        }

        public String getAdv_name() {
            return adv_name;
        }

        public void setAdv_name(String adv_name) {
            this.adv_name = adv_name;
        }

        public String getAdv_adressId() {
            return adv_adressId;
        }

        public void setAdv_adressId(String adv_adressId) {
            this.adv_adressId = adv_adressId;
        }

        public String getAdv_phno() {
            return adv_phno;
        }

        public void setAdv_phno(String adv_phno) {
            this.adv_phno = adv_phno;
        }

        public String getAdv_experience() {
            return adv_experience;
        }

        public void setAdv_experience(String adv_experience) {
            this.adv_experience = adv_experience;
        }

        public String getAdv_photo() {
            return adv_photo;
        }

        public void setAdv_photo(String adv_photo) {
            this.adv_photo = adv_photo;
        }

        public String getAdv_password() {
            return adv_password;
        }

        public void setAdv_password(String adv_password) {
            this.adv_password = adv_password;
        }

        public Advisor() {
        }

        public Advisor(String adv_name, String adv_adressId, String adv_phno, String adv_experience, String adv_photo, String adv_password) {
            this.adv_name = adv_name;
            this.adv_adressId = adv_adressId;
            this.adv_phno = adv_phno;
            this.adv_experience = adv_experience;
            this.adv_photo = adv_photo;
            this.adv_password = adv_password;
        }

        public Advisor(String adv_id, String adv_name, String adv_adressId, String adv_phno, String adv_experience, String adv_photo, String adv_password) {

            this.adv_id = adv_id;
            this.adv_name = adv_name;
            this.adv_adressId = adv_adressId;
            this.adv_phno = adv_phno;
            this.adv_experience = adv_experience;
            this.adv_photo = adv_photo;
            this.adv_password = adv_password;
        }



    }

    public boolean isSave(Advisor_Model.Advisor advisor){
        if (advisor==null){
            saved=false;
        }else {

            try{
                FirebaseDB.getFirebaseDB().child("Advisor").push().setValue(advisor);
                saved=true;
            }catch (Exception e){
                saved=false;
            }
        }
        return saved;
    }
    public boolean update(String id,Advisor_Model.Advisor advisor){
        if (advisor==null){
            saved=false;
        }else {

            try{
                FirebaseDB.getFirebaseDB().child("Advisor").child(id).setValue(advisor);
                saved=true;
            }catch (Exception e){
                saved=false;
            }
        }
        return saved;
    }
}
