package com.creativewave.ucsmtla.kingofmangoes.models;

public class ActionDetailModel {

    Boolean saved=null;

    public static class Action{
        String body,atid;
        String id,image;

        public Action() {
        }

        public Action(String body, String atid, String id, String image) {
            this.body = body;
            this.atid = atid;
            this.id = id;
            this.image = image;
        }

        public Action(String body, String atid, String image) {
            this.body = body;
            this.atid = atid;
            this.image = image;
        }

        public String getBody() {
            return body;
        }

        public void setBody(String body) {
            this.body = body;
        }

        public String getAtid() {
            return atid;
        }

        public void setAtid(String atid) {
            this.atid = atid;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }
    }

    public boolean isSave(Action stu){
        if (stu==null){
            saved=false;
        }else {

            try{
                FirebaseDB.getFirebaseDB().child("Action").push().setValue(stu);
                saved=true;
            }catch (Exception e){
                saved=false;
            }
        }
        return saved;
    }
    public boolean update(String id,Action stu){
        if (stu==null){
            saved=false;
        }else {

            try{
                FirebaseDB.getFirebaseDB().child("Action").child(id).setValue(stu);
                saved=true;
            }catch (Exception e){
                saved=false;
            }
        }
        return saved;
    }
}
