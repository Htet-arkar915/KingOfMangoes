package com.creativewave.ucsmtla.kingofmangoes.controllers;

import com.creativewave.ucsmtla.kingofmangoes.models.ActionDetailModel;

public class ActionController {
    ActionDetailModel model=new ActionDetailModel();
    //date insert
    public Boolean isInsert(ActionDetailModel.Action action){
        return model.isSave(action);
    }
    //data update
    public Boolean isUpdate(String id, ActionDetailModel.Action action){
        return model.update(id,action);
    }
}
