package com.creativewave.ucsmtla.kingofmangoes.models;

public class MessageModel {
    Boolean saved=null;

    public static class Message{


        public String getMessage_Aid() {
            return message_Aid;
        }

        public void setMessage_Aid(String message_Aid) {
            this.message_Aid = message_Aid;
        }

        public String getMessage_Uid() {
            return message_Uid;
        }

        public void setMessage_Uid(String message_Uid) {
            this.message_Uid = message_Uid;
        }

        public String getMessage_message() {
            return message_message;
        }

        public void setMessage_message(String message_message) {
            this.message_message = message_message;
        }

        public String getMessage_date() {
            return message_date;
        }

        public void setMessage_date(String message_date) {
            this.message_date = message_date;
        }

        public String getMessage_time() {
            return message_time;
        }

        public void setMessage_time(String message_time) {
            this.message_time = message_time;
        }

        public String getMessage_messageId() {
            return message_messageId;
        }

        public void setMessage_messageId(String message_messageId) {
            this.message_messageId = message_messageId;
        }

        public Boolean getUser() {
            return isUser;
        }

        public void setUser(Boolean user) {
            isUser = user;
        }

        String message_Aid,message_Uid,message_message,message_date,message_time,message_messageId;
       Boolean isUser;

        public Message() {
        }

        public Message(String message_Aid, String message_Uid, String message_message, String message_date, String message_time, Boolean isUser) {
            this.message_Aid = message_Aid;
            this.message_Uid = message_Uid;
            this.message_message = message_message;
            this.message_date = message_date;
            this.message_time = message_time;
            this.isUser = isUser;
        }

        public Message(String message_Aid, String message_Uid, String message_message, String message_date, String message_time, String message_messageId, Boolean isUser) {
            this.message_Aid = message_Aid;
            this.message_Uid = message_Uid;
            this.message_message = message_message;
            this.message_date = message_date;
            this.message_time = message_time;
            this.message_messageId = message_messageId;
            this.isUser = isUser;
        }







    }

    public boolean isSave(MessageModel.Message msg){
        if (msg==null){
            saved=false;
        }else {

            try{
                FirebaseDB.getFirebaseDB().child("Message").push().setValue(msg);
                saved=true;
            }catch (Exception e){
                saved=false;
            }
        }
        return saved;
    }
    public boolean update(String id,MessageModel.Message msg){
        if (msg==null){
            saved=false;
        }else {

            try{
                FirebaseDB.getFirebaseDB().child("Advisor").child(id).setValue(msg);
                saved=true;
            }catch (Exception e){
                saved=false;
            }
        }
        return saved;
    }
}
