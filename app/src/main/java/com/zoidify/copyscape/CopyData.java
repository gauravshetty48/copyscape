package com.zoidify.copyscape;

/**
 * Created by gaurav on 04-02-2017.
 */

public class CopyData {

    public String getCopiedText() {
        return copiedText;
    }

    public void setCopiedText(String copiedText) {
        this.copiedText = copiedText;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDateTime() {
        return datetime;
    }

    public void setDateTime(String datetime) {
        this.datetime = datetime;
    }

    public boolean getPinned() {
        return pinned;
    }

    public void setPinned(boolean pinned) {
        this.pinned = pinned;
    }


    private String copiedText, category, datetime;
    private boolean pinned;
}
