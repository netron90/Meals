package com.personnalize_design.meals.data.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(tableName = "day_menu")
public class DayMenuModel {


    @PrimaryKey(autoGenerate = true)
    public int id;

}
