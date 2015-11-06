package com.enlinkmob.ucenterapi.dao.impl;

import com.enlinkmob.ucenterapi.dao.BaseDao;
import com.enlinkmob.ucenterapi.exception.DataNotFoundException;
import com.enlinkmob.ucenterapi.model.PageBean;
import com.enlinkmob.ucenterapi.model.Sequence;
import com.mongodb.WriteResult;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.data.redis.core.RedisTemplate;

import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Map;

/**
 * Created by Zhaowy on 2015/5/6.
 */

public class BaseDaoImpl<T> implements BaseDao<T> {
    @Autowired
    protected RedisTemplate<String, String> redisTemplate;
    @Autowired
    protected MongoTemplate mongoTemplate;
    protected Class<T> clazz;

    public BaseDaoImpl() {
        ParameterizedType type = (ParameterizedType) this.getClass()
                .getGenericSuperclass();
        this.clazz = (Class) type.getActualTypeArguments()[0];
        System.out.println("---------->class = " + clazz);
    }

    public List<T> getAllObjects(boolean status) {
        Query q = new Query();
        if (status) {
            q.addCriteria(Criteria.where("status").is(1));
        }
        return mongoTemplate.find(q, clazz);
    }

    public T saveObject(T object) {
        mongoTemplate.insert(object);
        return object;

    }

    public T getObject(ObjectId id) {
        return mongoTemplate.findOne(new Query(Criteria.where("_id").is(id)), clazz);
    }

    @Override
    public T getObjectByCondition(Query query) {
        return mongoTemplate.findOne(query, clazz);
    }

    public WriteResult updateObject(ObjectId id, String name) {

        return mongoTemplate.updateFirst(

                new Query(Criteria.where("_id").is(id)),

                Update.update("name", name), clazz);
    }

    public void deleteObject(ObjectId id) {
        mongoTemplate.remove(new Query(Criteria.where("_id").is(id)), clazz);
    }

    public PageBean<T> getPageBean(int pageNo, int pageSize, Query query) {
        long totalCount = this.mongoTemplate.count(query, clazz);
        PageBean<T> pageBean = new PageBean<T>(pageNo, pageSize, totalCount);
        query.skip(pageBean.getFirstResult());// skip相当于从那条记录开始
        query.limit(pageSize);// 从skip开始,取多少条记录
        List datas = this.mongoTemplate.find(query, clazz);
        pageBean.setDatas(datas);
        return pageBean;
    }

    @Override
    public Sequence getIntSqlId() {

        //get sequence id
        Query query = new Query(Criteria.where("className").is(clazz.getTypeName()));

        //increase sequence id by 1
        Update update = new Update();
        update.inc("seq", 1);

        //return new increased id
        FindAndModifyOptions options = new FindAndModifyOptions();
        options.returnNew(true);
        options.upsert(true);

        //this is the magic happened.
        Sequence seqId =
                mongoTemplate.findAndModify(query, update, options, Sequence.class);

        //if no id, throws SequenceException
        //optional, just a way to tell user when the sequence id is failed to generate.
        if (seqId == null) {
            throw new DataNotFoundException("can not get seq", "Unable to get sequence id for key : " + clazz.getTypeName());
        } else {
            return seqId;
        }

    }

    @Override
    public void updateObj(T t) {
        mongoTemplate.save(t);
    }

    @Override
    public boolean update(Query query, Update update) {

        boolean result = false;
        WriteResult wr = this.mongoTemplate.updateFirst(query, update, clazz);
        if (wr != null && wr.getN() > 0) {
            result = true;
        }
        return result;
    }

    @Override
    public boolean updateMulti(Query query, Update update) {
        boolean result = false;
        WriteResult wr = this.mongoTemplate.updateMulti(query, update, clazz);
        if (wr != null && wr.getN() > 0) {
            result = true;
        }
        return result;
    }

    public int commonUpdate(Map<String, Object> updateMap, String key, Object value) {
        Query query = new Query(Criteria.where(key).is(value));
        Update update = new Update();
        for (Map.Entry<String, Object> entry : updateMap.entrySet()) {
            update.set(entry.getKey(), entry.getValue());
        }
        WriteResult wr = this.mongoTemplate.updateMulti(query, update, clazz);
        return wr.getN();
    }

}
