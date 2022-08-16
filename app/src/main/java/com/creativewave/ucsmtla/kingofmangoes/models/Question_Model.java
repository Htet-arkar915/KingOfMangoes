package com.creativewave.ucsmtla.kingofmangoes.models;

public class Question_Model {
    Boolean saved=null;

    public static class Question{

        String id,question,choice1,choice2,choice3,choice4,choice5,choice6,choice7,choice8,choice9,choice10;

        public Question(String id, String question, String choice1, String choice2, String choice3, String choice4, String choice5, String choice6, String choice7, String choice8, String choice9, String choice10) {
            this.id = id;
            this.question = question;
            this.choice1 = choice1;
            this.choice2 = choice2;
            this.choice3 = choice3;
            this.choice4 = choice4;
            this.choice5 = choice5;
            this.choice6 = choice6;
            this.choice7 = choice7;
            this.choice8 = choice8;
            this.choice9 = choice9;
            this.choice10 = choice10;
        }

        public Question() {
        }


    }

    public boolean isSave(Question stu){
        if (stu==null){
            saved=false;
        }else {

            try{
                FirebaseDB.getFirebaseDB().child("Question").push().setValue(stu);
                saved=true;
            }catch (Exception e){
                saved=false;
            }
        }
        return saved;
    }
    public boolean update(String id,Question stu){
        if (stu==null){
            saved=false;
        }else {

            try{
                FirebaseDB.getFirebaseDB().child("Question").child(id).setValue(stu);
                saved=true;
            }catch (Exception e){
                saved=false;
            }
        }
        return saved;
    }
}
