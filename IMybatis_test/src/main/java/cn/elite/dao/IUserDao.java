package cn.elite.dao;

import cn.elite.pojo.User;

import java.util.List;

/**
 * @Author: eliteMade
 * @Date: 2020/8/15 19:12
 * @Version: 1.0
 */
public interface IUserDao {
    /**
     * 查询user列表
     * @return
     */
    List<User> findAll();

    /**
     * 查询单个user
     * @param user
     * @return
     */
    User findByCondition(User user);

    /**
     * 新增user
     * @param user
     * @return
     */
    int insert(User user);
    /**
     * 修改user
     * @param user
     * @return
     */
    int update(User user);

    /**
     * 删除user
     * @param id
     * @return
     */
    int delete(Integer id);
}
