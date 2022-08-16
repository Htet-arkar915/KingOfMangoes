package com.creativewave.ucsmtla.kingofmangoes.controllers;

import com.creativewave.ucsmtla.kingofmangoes.models.ActionDetailModel;
import com.creativewave.ucsmtla.kingofmangoes.models.Tem_MoeModel;

public class Tem_MoeController {

    Tem_MoeModel tem_moeModel =new Tem_MoeModel();


    //date insert
    public Boolean isInsert(Tem_MoeModel.Tem_Moe tem){
        return tem_moeModel.save(tem);
    }
    //data update
    public Boolean isUpdate(String id, Tem_MoeModel.Tem_Moe tem){
        return tem_moeModel.update(id,tem);
    }
}
