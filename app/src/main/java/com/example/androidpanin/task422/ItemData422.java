package com.example.androidpanin.task422;

import android.app.Activity;
import android.graphics.drawable.Drawable;

public class ItemData422 {

    private Drawable image;
    private String header;
    private String description;
    private boolean checked;
    private String className;
    private Class activityClass;

    public ItemData422(Drawable image, String header, String description, boolean checked, String className) {
        this.image = image;
        this.header = header;
        this.description = description;
        this.checked = checked;
        this.className = className;
        try {
            this.activityClass = Class.forName(className);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public Drawable getImage() {
        return image;
    }

    public String getHeader() {
        return header;
    }

    public String getDescription() {
        return description;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public String getClassName() {
        return className;
    }

    public Class getActivityClass() {
        return activityClass;
    }
}
