package com.creativewave.ucsmtla.kingofmangoes.models;

public class NTDModel {

    Boolean saved=null;

    public static class NeedToDo{
     String actionId,quesId,ansId;
     String id;

        public NeedToDo() {
        }

        public NeedToDo(String actionId, String quesId, String ansId, String id) {
            this.actionId = actionId;
            this.quesId = quesId;
            this.ansId = ansId;
            this.id = id;
        }

        public NeedToDo(String actionId, String quesId, String ansId) {
            this.actionId = actionId;
            this.quesId = quesId;
            this.ansId = ansId;
        }

        public String getActionId() {
            return actionId;
        }

        public void setActionId(String actionId) {
            this.actionId = actionId;
        }

        public String getQuesId() {
            return quesId;
        }

        public void setQuesId(String quesId) {
            this.quesId = quesId;
        }

        public String getAnsId() {
            return ansId;
        }

        public void setAnsId(String ansId) {
            this.ansId = ansId;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }
    }

    public boolean isSave(NeedToDo stu){
        if (stu==null){
            saved=false;
        }else {

            try{
                FirebaseDB.getFirebaseDB().child("NeedToDo").push().setValue(stu);
                saved=true;
            }catch (Exception e){
                e.printStackTrace();
                saved=false;
            }
        }
        return saved;
    }
    public boolean update(String id,NeedToDo stu){
        if (stu==null){
            saved=false;
        }else {

            try{
                FirebaseDB.getFirebaseDB().child("NeedToDo").child(id).setValue(stu);
                saved=true;
            }catch (Exception e){
                e.printStackTrace();
                saved=false;
            }
        }
        return saved;
    }
}
