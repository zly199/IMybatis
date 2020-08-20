package cn.elite.sqlSession;

import cn.elite.pojo.Configuration;
import cn.elite.pojo.MappedStatement;

import java.sql.SQLException;
import java.util.List;

/**
 * @Author: eliteMade
 * @Date: 2020/8/15 17:26
 * @Version: 1.0
 */
public interface Executor {
    /**
     * 查询
     * @param configuration
     * @param mappedStatement
     * @param params
     * @param <T>
     * @return
     */
    public <T> List<T> query(Configuration configuration, MappedStatement mappedStatement,Object...params) throws Exception;
    /**
     * 修改
     * @param configuration
     * @param mappedStatement
     * @param params
     * @return
     */
    int update(Configuration configuration, MappedStatement mappedStatement,Object...params) throws Exception;

}
