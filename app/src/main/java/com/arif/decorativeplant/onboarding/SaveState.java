package com.arif.decorativeplant.onboarding;

import android.content.Context;
import android.content.SharedPreferences;

public class SaveState {
    //    NIM : 10120211
    //    Nama : Arif Rachmat Darmawan
    //    Kelas : IF-6

    private Context context;
    private String saveName;
    private SharedPreferences sp;

    public SaveState(Context context, String saveName) {
        this.context = context;
        this.saveName = saveName;
        sp = context.getSharedPreferences(saveName,context.MODE_PRIVATE);
    }

    public void setState(int key){
        SharedPreferences.Editor editor = sp.edit();
        editor.putInt("key",key);
        editor.apply();
    }

    public int getState(){
        return sp.getInt("key",0);
    }
}
