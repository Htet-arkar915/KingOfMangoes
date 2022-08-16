package com.creativewave.ucsmtla.kingofmangoes.models;

public class QTypeModel {
    Boolean saved=null;
    public static class QType{
        String id,name;
        //

        public QType() {
        }
        public QType(String _id, String _name) {
            this.id = _id;
            this.name = _name;
        }
        public QType(String _name) {
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
    public boolean save(QType cty){
        if(cty==null){
            saved=false;
        }else{
            try {
                FirebaseDB.getFirebaseDB().child("QType").push().setValue(cty);
                saved = true;
            }catch (Exception e){
                saved=false;
            }
            }
        return saved;
        }
    public boolean update(String id,QType cty){
        if(cty==null){
            saved=false;
        }else{
            try {
                FirebaseDB.getFirebaseDB().child("QType").child(id).setValue(cty);
                saved = true;
            }catch (Exception e){
                saved=false;
            }
            }
        return saved;
    }
}
