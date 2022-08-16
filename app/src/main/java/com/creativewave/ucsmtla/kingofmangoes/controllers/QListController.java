package com.creativewave.ucsmtla.kingofmangoes.controllers;

import com.creativewave.ucsmtla.kingofmangoes.models.QListModel;

public class QListController {
    QListModel model = new QListModel();

    //date insert
    public Boolean isInsert(QListModel.QList qlist) {
        return model.save(qlist);
    }

    //data update
    public Boolean isUpdate(String id, QListModel.QList qlist) {
        return model.update(id, qlist);
    }
}