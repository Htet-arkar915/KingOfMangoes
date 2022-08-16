package com.creativewave.ucsmtla.kingofmangoes.controllers;

import com.creativewave.ucsmtla.kingofmangoes.models.Advisor_Model;
import com.creativewave.ucsmtla.kingofmangoes.models.MessageModel;

public class Message_controller {
    MessageModel model=new MessageModel();
    //date insert
    public Boolean isInsert(MessageModel.Message msg){
        return model.isSave(msg);
    }
    //data update
    public Boolean isUpdate(String id, MessageModel.Message msg){
        return model.update(id,msg);
    }
}
