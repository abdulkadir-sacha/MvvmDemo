package com.ak.mvvmdemo.data.local.db.dao;

import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Update;

import java.util.List;

public interface BaseDao<T> {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(T option);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<T> options);

    @Update
    void update(T value);

    @Delete
    void delete(T value);
}
