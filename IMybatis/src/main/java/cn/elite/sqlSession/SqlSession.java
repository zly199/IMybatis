package cn.elite.sqlSession;

import java.util.List;

/**
 * @Author: eliteMade
 * @Date: 2020/8/15 17:07
 * @Version: 1.0
 */
public interface SqlSession {
    /**
     * 查询多条记录
     * @param statementId
     * @param params
     * @param <E>
     * @return
     */
    public <E> List<E> selectList(String statementId,Object...params) throws Exception;

    /**
     * 查询多条记录
     * @param statementId
     * @param params
     * @param <T>
     * @return
     */
    public <T> T selectOne(String statementId,Object...params) throws Exception;

    /**
     * 获取mapper代理对象
     * @param mapperClass
     * @param <T>
     * @return
     */
    <T> T getMapper(Class<?> mapperClass);
}
