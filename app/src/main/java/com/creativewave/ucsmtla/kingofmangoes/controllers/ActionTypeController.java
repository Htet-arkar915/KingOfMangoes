package com.creativewave.ucsmtla.kingofmangoes.controllers;

import com.creativewave.ucsmtla.kingofmangoes.models.ActionTypeModel;

public class ActionTypeController {
    ActionTypeModel model=new ActionTypeModel();
    //date insert
    public Boolean isInsert(ActionTypeModel.ActionType actionType){
        return model.save(actionType);
    }
    //data update
    public Boolean isUpdate(String id, ActionTypeModel.ActionType actionType){
        return model.update(id,actionType);
    }
}
