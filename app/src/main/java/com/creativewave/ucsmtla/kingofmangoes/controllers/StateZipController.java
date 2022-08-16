package com.creativewave.ucsmtla.kingofmangoes.controllers;

import com.creativewave.ucsmtla.kingofmangoes.models.StateZipModel;

public class StateZipController {
    StateZipModel szModel=new StateZipModel();

    public boolean save(StateZipModel.StateZip sz){
        return szModel.save(sz);

    }
    public boolean update(String id,StateZipModel.StateZip sz){
        return szModel.update(id,sz);

    }}
