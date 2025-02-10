package com.example.studentBackend.common.mybatis;

import com.example.studentBackend.common.vo.BaseException;
import tk.mybatis.mapper.additional.insert.InsertListMapper;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.ids.SelectByIdsMapper;

public interface CommonMapper<T> extends SelectByIdsMapper<T>, Mapper<T>, InsertListMapper<T>{
    /**
     * 根据主键选择字段更新-乐观锁校验
     *
     * @param t 更新对象
     * @return 更新条数
     */
    default int updateByPrimaryKeyWithVersionSelective(T t) {
        int result = updateByPrimaryKeySelective(t);
        if (result == 0) {
            throw new BaseException("更新失败-该数据已被他人修改!");
        }
        return result;
    }

    /**
     * 根据主键全更新-乐观锁校验
     *
     * @param t 更新对象
     * @return 更新条数
     */
    default int updateByPrimaryKeyWithVersion(T t) {
        int result = updateByPrimaryKey(t);
        if (result == 0) {
            throw new BaseException("更新失败-该数据已被他人修改!");
        }
        return result;
    }

    /**
     * 根据选择条件选择更新-乐观锁校验
     *
     * @param t       更新对象
     * @param example 查询条件
     * @return 更新条数
     */
    default int updateByExampleWithVersionSelective(T t, Object example) {
        int result = updateByExampleSelective(t, example);
        if (result == 0) {
            throw new BaseException("更新失败-该数据已被他人修改!");
        }
        return result;
    }

    /**
     * 根据选择条件全更新-乐观锁校验
     *
     * @param t       更新对象
     * @param example 查询条件
     * @return 更新条数
     */
    default int updateByExampleWithVersion(T t, Object example) {
        int result = updateByExample(t, example);
        if (result == 0) {
            throw new BaseException("更新失败-该数据已被他人修改!");
        }
        return result;
    }

    /**
     * 根据主键删除-乐观锁校验
     *
     * @param t 删除对象
     * @return 删除条数
     */
    default int deleteByPrimaryKeyWithVersion(T t) {
        int result = deleteByPrimaryKey(t);
        if (result == 0) {
            throw new BaseException("删除失败-该数据已被他人修改!");
        }
        return result;
    }

    /**
     * 根据条件删除-乐观锁校验
     *
     * @param t 删除对象
     * @return 删除条数
     */
    default int deleteWithVersion(T t) {
        int result = delete(t);
        if (result == 0) {
            throw new BaseException("删除失败-该数据已被他人修改!");
        }
        return result;
    }
}