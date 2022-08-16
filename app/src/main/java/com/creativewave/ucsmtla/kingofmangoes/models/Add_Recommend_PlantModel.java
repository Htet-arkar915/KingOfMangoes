package com.creativewave.ucsmtla.kingofmangoes.models;

public class Add_Recommend_PlantModel {
    Boolean saved=null;
    public static class Recommend_Plant{
        String id,name,photo,start_tem,end_tem,start_moe,end_moe;

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

        public String getPhoto() {
            return photo;
        }

        public void setPhoto(String photo) {
            this.photo = photo;
        }

        public String getStart_tem() {
            return start_tem;
        }

        public void setStart_tem(String start_tem) {
            this.start_tem = start_tem;
        }

        public String getEnd_tem() {
            return end_tem;
        }

        public void setEnd_tem(String end_tem) {
            this.end_tem = end_tem;
        }

        public String getStart_moe() {
            return start_moe;
        }

        public void setStart_moe(String start_moe) {
            this.start_moe = start_moe;
        }

        public String getEnd_moe() {
            return end_moe;
        }

        public void setEnd_moe(String end_moe) {
            this.end_moe = end_moe;
        }

        public Recommend_Plant(String name, String photo, String start_tem, String end_tem, String start_moe, String end_moe) {

            this.name = name;
            this.photo = photo;
            this.start_tem = start_tem;
            this.end_tem = end_tem;
            this.start_moe = start_moe;
            this.end_moe = end_moe;
        }

        public Recommend_Plant(String id, String name, String photo, String start_tem, String end_tem, String start_moe, String end_moe) {

            this.id = id;
            this.name = name;
            this.photo = photo;
            this.start_tem = start_tem;
            this.end_tem = end_tem;
            this.start_moe = start_moe;
            this.end_moe = end_moe;
        }
//

        public Recommend_Plant() {
        }




    }
    public boolean save(Add_Recommend_PlantModel.Recommend_Plant recPlant){
        if(recPlant==null){
            saved=false;
        }else{
            try {
                FirebaseDB.getFirebaseDB().child("Recommend_Plant").push().setValue(recPlant);
                saved = true;
            }catch (Exception e){
                saved=false;
            }
        }
        return saved;
    }
    public boolean update(String id,Add_Recommend_PlantModel.Recommend_Plant recPlant){
        if(recPlant==null){
            saved=false;
        }else{
            try {
                FirebaseDB.getFirebaseDB().child("Recommend_Plant").child(id).setValue(recPlant);
                saved = true;
            }catch (Exception e){
                saved=false;
            }
        }
        return saved;
    }
}
