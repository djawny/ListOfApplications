package com.sdaacademy.jawny.daniel.listofapplications;

import android.graphics.drawable.Drawable;

public class AppInfo {
    private int uId;
    private String name;
    private Drawable icon;

    public AppInfo(int uId, String name, Drawable icon) {
        this.uId = uId;
        this.name = name;
        this.icon = icon;
    }

    public int getuId() {
        return uId;
    }

    public void setuId(int uId) {
        this.uId = uId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Drawable getIcon() {
        return icon;
    }

    public void setIcon(Drawable icon) {
        this.icon = icon;
    }
}
