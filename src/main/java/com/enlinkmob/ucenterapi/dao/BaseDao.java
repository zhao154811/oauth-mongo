package com.enlinkmob.ucenterapi.dao;

import com.enlinkmob.ucenterapi.model.PageBean;
import com.enlinkmob.ucenterapi.model.Sequence;
import com.mongodb.WriteResult;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import java.util.List;
import java.util.Map;

/**
 * Created by Zhaowy on 2015/5/6.
 */
public interface BaseDao<T> {
    public List<T> getAllObjects(boolean status);


    public T saveObject(T object);


    public T getObject(ObjectId id);

    public T getObjectByCondition(Query query);


    public WriteResult updateObject(ObjectId id, String name);


    public void deleteObject(ObjectId id);

    public PageBean<T> getPageBean(int pageNo, int pageSize, Query query);

    public Sequence getIntSqlId();

    void updateObj(T t);

    public boolean update(Query query, Update update);

    public boolean updateMulti(Query query, Update update);

    public int commonUpdate(Map<String, Object> updateMap, String key, Object value);
}
