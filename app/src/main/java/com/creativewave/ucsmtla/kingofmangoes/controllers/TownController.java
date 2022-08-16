package com.creativewave.ucsmtla.kingofmangoes.controllers;

import com.creativewave.ucsmtla.kingofmangoes.models.TownModel;

public class TownController {

    TownModel item=new TownModel();
    public boolean save(TownModel.Town tw){
        return item.save(tw);

    }
    public boolean update(String id,TownModel.Town tw){
        return item.update(id,tw);

    }}
