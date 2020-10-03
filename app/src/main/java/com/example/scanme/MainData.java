package com.example.scanme;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

//Define table name
@Entity(tableName = "scanned_results")

public class MainData {
    //Create id column
    @PrimaryKey(autoGenerate = true)
    private int ID;

    //Create text column
    @ColumnInfo(name = "text")
    private String text;

    //Generate setter and getter


    public String getText() {
        return text;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public void setText(String text) {
        this.text = text;
    }
}
