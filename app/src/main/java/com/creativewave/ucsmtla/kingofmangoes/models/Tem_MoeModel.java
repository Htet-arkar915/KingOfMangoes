package com.creativewave.ucsmtla.kingofmangoes.models;

public class Tem_MoeModel {
    Boolean saved=null;
    public static class Tem_Moe{
        public Tem_Moe(String tem_start, String CID, String tem_end, String moe_start, String moe_end) {
            this.tem_start = tem_start;
            this.CID = CID;
            this.tem_end = tem_end;
            this.moe_start = moe_start;
            this.moe_end = moe_end;
        }

        public Tem_Moe(String id, String tem_start, String CID, String tem_end, String moe_start, String moe_end) {

            this.id = id;
            this.tem_start = tem_start;
            this.CID = CID;
            this.tem_end = tem_end;
            this.moe_start = moe_start;
            this.moe_end = moe_end;
        }

        String id;
        String tem_start;
        String CID;
        String tem_end;
        String moe_start;
        String moe_end;

        public String getCID() {
            return CID;
        }

        public void setCID(String CID) {
            this.CID = CID;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getTem_start() {
            return tem_start;
        }

        public void setTem_start(String tem_start) {
            this.tem_start = tem_start;
        }

        public String getTem_end() {
            return tem_end;
        }

        public void setTem_end(String tem_end) {
            this.tem_end = tem_end;
        }

        public String getMoe_start() {
            return moe_start;
        }

        public void setMoe_start(String moe_start) {
            this.moe_start = moe_start;
        }

        public String getMoe_end() {
            return moe_end;
        }

        public void setMoe_end(String moe_end) {
            this.moe_end = moe_end;
        }




//

        public Tem_Moe() {
        }


    }
    public boolean save(Tem_MoeModel.Tem_Moe tem){
        if(tem==null){
            saved=false;
        }else{
            try {
                FirebaseDB.getFirebaseDB().child("State_Tempeture").push().setValue(tem);
                saved = true;
            }catch (Exception e){
                saved=false;
            }
        }
        return saved;
    }
    public boolean update(String id,Tem_MoeModel.Tem_Moe tem){
        if(tem==null){
            saved=false;
        }else{
            try {
                FirebaseDB.getFirebaseDB().child("State_Tempeture").child(id).setValue(tem);
                saved = true;
            }catch (Exception e){
                saved=false;
            }
        }
        return saved;
    }
}
