package com.project.shopping_admin.database.daos;


import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.project.shopping_admin.database.entities.ImageEntity;

import java.util.List;


@Dao
public interface ImageEntityDao {

    @Query(" Select count(*)  from tbl_image")
    public int get_count();


    @Insert
    public Long insert_image_details(ImageEntity tbl);

    @Query(" Delete from tbl_image")
    public int del_all();


    @Query(" Select id,saved_fname from tbl_image")
    public List<ImageEntity> get_saved_file_details();


    @Query(" Delete from tbl_image where saved_fname=:fname")
    public int del(String fname);


}
