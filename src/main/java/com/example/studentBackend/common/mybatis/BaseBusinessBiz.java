package com.example.studentBackend.common.mybatis;

public abstract class BaseBusinessBiz<M extends CommonMapper<T>, T> extends BaseBiz<M, T> {
    public BaseBusinessBiz() {
    }

    public void insertSelective(T entity) {
        super.insertSelective(entity);
    }

    public void updateById(T entity) {
        super.updateById(entity);
    }

    public void updateSelectiveById(T entity) {
        super.updateSelectiveById(entity);
    }
}
