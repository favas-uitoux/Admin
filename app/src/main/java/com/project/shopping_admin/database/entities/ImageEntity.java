package com.project.shopping_admin.database.entities;


import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "tbl_image")
public class ImageEntity {

    @PrimaryKey(autoGenerate = true)
    private   long id;





    private String saved_fname;


    public ImageEntity(long id, String saved_fname) {
        this.id = id;
        this.saved_fname = saved_fname;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getSaved_fname() {
        return saved_fname;
    }

    public void setSaved_fname(String saved_fname) {
        this.saved_fname = saved_fname;
    }
}
