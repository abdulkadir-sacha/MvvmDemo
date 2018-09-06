package com.ak.mvvmdemo.data.local.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.ak.mvvmdemo.data.local.db.dao.DummyDao;
import com.ak.mvvmdemo.data.models.DummyModel;


@Database(entities = {DummyModel.class}, version = 3,exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    abstract DummyDao dummyDao();

}
