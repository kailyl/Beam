package com.me.kaily.beam.model;

public class Entry {
    private String entryText;
    private long entryDate;

    public Entry(String entryText, long entryDate) {
        this.entryText = entryText;
        this.entryDate = entryDate;
    }

    public String getEntryText() {
        return entryText;
    }

    public void setEntryText(String entryText) {
        this.entryText = entryText;
    }

    public long getEntryDate() {
        return entryDate;
    }

    public void setEntryDate(long entryDate) {
        this.entryDate = entryDate;
    }
}
