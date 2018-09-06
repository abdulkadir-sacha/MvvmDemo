package com.ak.mvvmdemo.data.local.db.dao;

import android.arch.persistence.room.Dao;

import com.ak.mvvmdemo.data.models.DummyModel;

@Dao
public interface DummyDao extends BaseDao<DummyModel> {
}
