package com.creativewave.ucsmtla.kingofmangoes.controllers;

import com.creativewave.ucsmtla.kingofmangoes.models.Advisor_Model;

public class Advisor_controller {
    Advisor_Model model=new Advisor_Model();
    //date insert
    public Boolean isInsert(Advisor_Model.Advisor advisor){
        return model.isSave(advisor);
    }
    //data update
    public Boolean isUpdate(String id, Advisor_Model.Advisor advisor){
        return model.update(id,advisor);
    }
}
