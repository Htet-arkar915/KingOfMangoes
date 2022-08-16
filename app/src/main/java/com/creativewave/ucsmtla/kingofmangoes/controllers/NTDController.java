package com.creativewave.ucsmtla.kingofmangoes.controllers;

import com.creativewave.ucsmtla.kingofmangoes.models.NTDModel;

public class NTDController {
    NTDModel model=new NTDModel();
    //date insert
    public Boolean isInsert(NTDModel.NeedToDo ntd){
        return model.isSave(ntd);
    }
    //data update
    public Boolean isUpdate(String id, NTDModel.NeedToDo ntd){
        return model.update(id,ntd);
    }
}
