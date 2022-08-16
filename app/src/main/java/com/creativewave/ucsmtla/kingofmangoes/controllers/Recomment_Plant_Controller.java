package com.creativewave.ucsmtla.kingofmangoes.controllers;

import com.creativewave.ucsmtla.kingofmangoes.models.Add_Recommend_PlantModel;
import com.creativewave.ucsmtla.kingofmangoes.models.QListModel;

public class Recomment_Plant_Controller {
    Add_Recommend_PlantModel model = new Add_Recommend_PlantModel();

    //date insert
    public Boolean isInsert(Add_Recommend_PlantModel.Recommend_Plant recPlant) {
        return model.save(recPlant);
    }

    //data update
    public Boolean isUpdate(String id, Add_Recommend_PlantModel.Recommend_Plant recPlant) {
        return model.update(id, recPlant);
    }
}
