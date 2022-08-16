package com.creativewave.ucsmtla.kingofmangoes.models;

public class StateZipModel {
    Boolean saved=null;
    public static class StateZip{
        String id,name;
        //

        public StateZip() {
        }
        public StateZip(String _id, String _name) {
            this.id = _id;
            this.name = _name;
        }
        public StateZip(String _name) {
            this.name = _name;
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

    }
    public boolean save(StateZip cty){
        if(cty==null){
            saved=false;
        }else{
            try {
                FirebaseDB.getFirebaseDB().child("Adress").child("StateZip").push().setValue(cty);
                saved = true;
            }catch (Exception e){
                saved=false;
            }
            }
        return saved;
        }
    public boolean update(String id,StateZip cty){
        if(cty==null){
            saved=false;
        }else{
            try {
                FirebaseDB.getFirebaseDB().child("Adress").child("StateZip").child(id).setValue(cty);
                saved = true;
            }catch (Exception e){
                saved=false;
            }
            }
        return saved;
    }
}
