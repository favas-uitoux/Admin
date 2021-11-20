package com.project.shopping_admin.database.appdb;




import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.project.shopping_admin.database.daos.ImageEntityDao;
import com.project.shopping_admin.database.entities.ImageEntity;


@Database(version = 1,entities = {ImageEntity.class})
public abstract  class Appdb extends RoomDatabase {


  private static Appdb db;

  public abstract ImageEntityDao getImageEntityDao();

  public static synchronized Appdb getDb_instance(Context context)
  {

    if(db==null)
    {
      db = Room.databaseBuilder(context.getApplicationContext(), Appdb.class, "AdminDB").allowMainThreadQueries().build();



    }


    return db;

  }



}
