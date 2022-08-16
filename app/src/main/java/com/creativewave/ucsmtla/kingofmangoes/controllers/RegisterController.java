package com.creativewave.ucsmtla.kingofmangoes.controllers;

import com.creativewave.ucsmtla.kingofmangoes.models.AccountModel;

public class RegisterController {
    AccountModel szModel=new AccountModel();

    public boolean save(AccountModel.Account ac){
        return szModel.isSave(ac);

    }
    public boolean update(String id,AccountModel.Account ac){
        return szModel.update(id,ac);

    }}
