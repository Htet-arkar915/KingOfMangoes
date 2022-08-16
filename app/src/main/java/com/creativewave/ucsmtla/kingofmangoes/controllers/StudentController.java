package com.creativewave.ucsmtla.kingofmangoes.controllers;


import com.creativewave.ucsmtla.kingofmangoes.models.ActionDetailModel;

public class StudentController {

    ActionDetailModel actionDetailModel =new ActionDetailModel();


    //date insert
    public Boolean isInsert(ActionDetailModel.Action stud){
        return actionDetailModel.isSave(stud);
    }
    //data update
    public Boolean isUpdate(String id, ActionDetailModel.Action student){
        return actionDetailModel.update(id,student);
    }
}
