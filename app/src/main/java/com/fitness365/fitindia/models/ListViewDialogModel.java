package com.fitness365.fitindia.models;

public class ListViewDialogModel {

    private int listItemId;
    private String listItemTitle;
    private boolean isItemSelected;
    private String listItemSubtitle = null;

    public ListViewDialogModel(int listItemId, String listItemTitle, boolean isItemSelected) {
        this.listItemId = listItemId;
        this.listItemTitle = listItemTitle;
        this.isItemSelected = isItemSelected;
    }

    public ListViewDialogModel(int listItemId, String listItemTitle, String listItemSubtitle, boolean isItemSelected) {
        this.listItemId = listItemId;
        this.listItemTitle = listItemTitle;
        this.listItemSubtitle = listItemSubtitle;
        this.isItemSelected = isItemSelected;
    }

    public int getListItemId() {
        return listItemId;
    }

    public String getListItemTitle() {
        return listItemTitle;
    }

    public boolean isItemSelected() {
        return isItemSelected;
    }

    public void setItemSelected(boolean itemSelected) {
        isItemSelected = itemSelected;
    }

    public String getListItemSubtitle() {
        return listItemSubtitle;
    }


}
