package com.aaronevan.binusezyfoody.binusclass;

import android.content.Context;

import com.aaronevan.binusezyfoody.helper.UserDatabaseHandler;

public class MyMoney {
    private Integer money;
    private Context con;
    UserDatabaseHandler udh;
    private static Integer store;

    public MyMoney(Context con) {
        this.con = con;
        this.udh = new UserDatabaseHandler(this.con);
        money = udh.getMoney();
    }

    public void TopUpMoney(Integer duit) {
        udh.updateMoney(duit+this.money);
        this.money = udh.getMoney();
    }

    public void ReduceMoney(Integer duit){
        udh.updateMoney(this.money-duit);
        this.money = udh.getMoney();
    }

    public Integer getMoney() {
        return money;
    }

    public void setMoney(Integer money) {
        this.money = money;
    }

    public static Integer getStore() {
        return store;
    }

    public static void setStore(Integer store) {
        MyMoney.store = store;
    }
}
