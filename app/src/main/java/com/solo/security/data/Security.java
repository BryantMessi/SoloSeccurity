package com.solo.security.data;

import android.graphics.drawable.Drawable;

/**
 * Created by Messi on 16-11-3.
 */

public class Security {
    private Drawable mIcon;
    private String mLabel;
    private String mPackageName;
    private String mSize;
    private String mInfo;

    public Drawable getIcon() {
        return mIcon;
    }

    public void setIcon(Drawable icon) {
        this.mIcon = icon;
    }

    public String getLabel() {
        return mLabel;
    }

    public void setLabel(String label) {
        this.mLabel = label;
    }

    public String getPackageName() {
        return mPackageName;
    }

    public void setPackageName(String packageName) {
        this.mPackageName = packageName;
    }

    public String getSize() {
        return mSize;
    }

    public void setSize(String size) {
        this.mSize = size;
    }

    public String getInfo() {
        return mInfo;
    }

    public void setInfo(String info) {
        this.mInfo = info;
    }
}
