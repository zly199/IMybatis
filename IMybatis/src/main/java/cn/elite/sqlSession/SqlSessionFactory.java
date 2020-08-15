package cn.elite.sqlSession;

/**
 * @Author: eliteMade
 * @Date: 2020/8/15 16:16
 * @Version: 1.0
 */
public interface SqlSessionFactory {
    /**
     * 开启一个SqlSession
     * @return
     */
    public SqlSession openSession();
}
