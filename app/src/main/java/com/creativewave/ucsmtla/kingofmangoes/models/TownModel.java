package com.creativewave.ucsmtla.kingofmangoes.models;

public class TownModel {
    Boolean saved=null;
    public static class Town{
        String id,name,stateID;
        //

        public Town() {
        }
        public Town(String _id, String _name,String _ctyID) {
            this.id = _id;
            this.name = _name;
            this.stateID=_ctyID;
        }
        public Town(String _name,String _ctyID) {
            this.name = _name;
            this.stateID=_ctyID;
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

        public String getCategoryID() {
            return stateID;
        }

        public void setCategoryID(String stateID) {
            this.stateID = stateID;
        }
    }
    public boolean save(Town cty){
        if(cty==null){
            saved=false;
        }else{
            try {
                FirebaseDB.getFirebaseDB().child("Adress").child("TownList").push().setValue(cty);
                saved = true;
            }catch (Exception e){
                saved=false;
            }
        }
        return saved;
    }
    public boolean update(String id,Town cty){
        if(cty==null){
            saved=false;
        }else{
            try {
                FirebaseDB.getFirebaseDB().child("Adress").child("TownList").child(id).setValue(cty);
                saved = true;
            }catch (Exception e){
                saved=false;
            }
        }
        return saved;
    }
}
