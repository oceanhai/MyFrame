package com.xywy.component.uimodules.menu;

/**
 * Created by shijiazi on 16/2/25.
 */
public class MenuData {
    private static int MENU_Id_INDEX = 1;
    private int mMenuId;
    private String mText;

    public MenuData() {
        mMenuId = MENU_Id_INDEX;
        MENU_Id_INDEX++;
    }

    public int getMenuId() {
        return mMenuId;
    }

    public String getText() {
        return mText;
    }

    public void setText(String mText) {
        this.mText = mText;
    }

}
