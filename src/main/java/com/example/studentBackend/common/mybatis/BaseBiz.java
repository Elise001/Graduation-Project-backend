package com.example.studentBackend.common.mybatis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public abstract class BaseBiz<M extends CommonMapper<T>, T> {
    @Autowired
    protected M mapper;

    public void setMapper(M mapper) {
        this.mapper = mapper;
    }

    public T selectOne(T entity) {
        return mapper.selectOne(entity);
    }

    public T selectById(Object id) {
        return mapper.selectByPrimaryKey(id);
    }

    public List<T> selectList(T entity) {
        return mapper.select(entity);
    }

    public List<T> selectListAll() {
        return mapper.selectAll();
    }

    public Long selectCount(T entity) {
        return new Long(mapper.selectCount(entity));
    }

    public void insertSelective(T entity) {
        mapper.insertSelective(entity);
    }

    public void delete(T entity) {
        mapper.delete(entity);
    }

    public void deleteById(Object id) {
        mapper.deleteByPrimaryKey(id);
    }

    public void updateById(T entity) {
        mapper.updateByPrimaryKey(entity);
    }

    public void updateSelectiveById(T entity) {
        mapper.updateByPrimaryKeySelective(entity);
    }

    public List<T> selectByExample(Object example) {
        return mapper.selectByExample(example);
    }

    public int selectCountByExample(Object example) {
        return mapper.selectCountByExample(example);
    }


    /**
     * 批量插入，300条数据插入一次，字段为null会插入
     *
     * @param list list
     */
    public int insertList(List list) {
        return MapperUtils.insertList(this.mapper, list);
    }

    /**
     * 批量插入，batchSize条数据插入一次，字段为null会插入
     *
     * @param list      list
     * @param batchSize 插入条数
     */
    public int insertList(List list, int batchSize) {
        return MapperUtils.insertList(this.mapper, list, batchSize);
    }

}
