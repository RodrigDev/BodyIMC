package com.rodrigo.bodyimc.model;

import android.content.Context;

public class SharedPreferences {

    public void setSharedPrefs(Context contexto,
                                String nomeProjeto,
                                String chave,
                                String valor) {
        android.content.SharedPreferences sharedPreferences;
        sharedPreferences = contexto.getSharedPreferences(nomeProjeto, Context.MODE_PRIVATE);
        android.content.SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(chave, valor);
        editor.apply();
    }

    public String getSharedPrefs(Context contexto,
                                  String nomeProjeto,
                                  String chave) {
        android.content.SharedPreferences sharedPreferences;
        sharedPreferences = contexto.getSharedPreferences(nomeProjeto, Context.MODE_PRIVATE);
        return sharedPreferences.getString(chave, null);
    }

}
