package com.creativewave.ucsmtla.kingofmangoes.models;

public class ActionTypeModel {
    Boolean saved=null;
    public static class ActionType{
        String id,name;
        int age;
        //

        public ActionType() {
        }
        public ActionType(String _id, String _name,int age) {
            this.id = _id;
            this.name = _name;
            this.age=age;
        }
        public ActionType(String _name,int age) {
            this.name = _name;
            this.age=age;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
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
    public boolean save(ActionType cty){
        if(cty==null){
            saved=false;
        }else{
            try {
                FirebaseDB.getFirebaseDB().child("ActionType").push().setValue(cty);
                saved = true;
            }catch (Exception e){
                saved=false;
            }
        }
        return saved;
    }
    public boolean update(String id,ActionType cty){
        if(cty==null){
            saved=false;
        }else{
            try {
                FirebaseDB.getFirebaseDB().child("ActionType").child(id).setValue(cty);
                saved = true;
            }catch (Exception e){
                saved=false;
            }
        }
        return saved;
    }
}
