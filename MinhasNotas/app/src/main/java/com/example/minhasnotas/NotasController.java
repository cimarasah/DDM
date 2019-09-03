package com.example.minhasnotas;

import android.content.Context;
import android.content.SharedPreferences;

public class NotasController {
    Context c;
    SharedPreferences sp;
    SharedPreferences.Editor spe;
    public NotasController(Context c){
        this.c = c;
        sp = c.getSharedPreferences("MinhasNotas", c.MODE_PRIVATE);
        spe = sp.edit();
    }
    public boolean Salvar(String text){
        spe.putString("nota", text);
        return spe.commit();
    }
    public String Recupera(){

        return sp.getString("nota", "default");
    }
}
