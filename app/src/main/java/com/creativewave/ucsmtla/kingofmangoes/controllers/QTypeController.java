package com.creativewave.ucsmtla.kingofmangoes.controllers;

import com.creativewave.ucsmtla.kingofmangoes.models.QTypeModel;

public class QTypeController {
    QTypeModel model=new QTypeModel();
    //date insert
    public Boolean isInsert(QTypeModel.QType qType){
        return model.save(qType);
    }
    //data update
    public Boolean isUpdate(String id, QTypeModel.QType qType){
        return model.update(id,qType);
    }
}
