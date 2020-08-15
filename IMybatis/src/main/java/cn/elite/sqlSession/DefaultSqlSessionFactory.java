package cn.elite.sqlSession;

import cn.elite.pojo.Configuration;

/**
 * @Author: eliteMade
 * @Date: 2020/8/15 17:05
 * @Version: 1.0
 */
public class DefaultSqlSessionFactory implements SqlSessionFactory{
    private Configuration configuration;

    public DefaultSqlSessionFactory(Configuration configuration) {
        this.configuration = configuration;
    }

    @Override
    public SqlSession openSession() {
        return new DefaultSqlSession(configuration);
    }
}
