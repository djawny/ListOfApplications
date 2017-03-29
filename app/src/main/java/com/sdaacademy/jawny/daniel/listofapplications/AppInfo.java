package com.sdaacademy.jawny.daniel.listofapplications;

import android.graphics.drawable.Drawable;

public class AppInfo {
    private int uid;
    private String name;
    private Drawable icon;

    public AppInfo(int uid, String name, Drawable icon) {
        this.uid = uid;
        this.name = name;
        this.icon = icon;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
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

    @Override
    public String toString() {
        return "AppInfo{" + "uid=" + uid + ", name='" + name + '\'' + '}';
    }
}
