package com.creativewave.ucsmtla.kingofmangoes.models;

public class QListModel {
    Boolean saved=null;
    public static class QList{
        String id,choice,qtid;
        //

        public QList() {
        }
        public QList(String _id, String _choice,String _qtid) {
            this.id = _id;
            this.choice = _choice;
            this.qtid=_qtid;
        }
        public QList(String _choice,String _qtid) {
            this.choice = _choice;
            this.qtid=_qtid;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getChoice() {
            return choice;
        }

        public void setChoice(String choice) {
            this.choice = choice;
        }

        public String getQtid() {
            return qtid;
        }

        public void setQtid(String qtid) {
            this.qtid = qtid;
        }
    }
    public boolean save(QList cty){
        if(cty==null){
            saved=false;
        }else{
            try {
                FirebaseDB.getFirebaseDB().child("QList").push().setValue(cty);
                saved = true;
            }catch (Exception e){
                saved=false;
            }
        }
        return saved;
    }
    public boolean update(String id,QList cty){
        if(cty==null){
            saved=false;
        }else{
            try {
                FirebaseDB.getFirebaseDB().child("QList").child(id).setValue(cty);
                saved = true;
            }catch (Exception e){
                saved=false;
            }
        }
        return saved;
    }
}
