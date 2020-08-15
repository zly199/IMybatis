package cn.elite.test;

import cn.elite.dao.IUserDao;
import cn.elite.io.Resources;
import cn.elite.pojo.User;
import cn.elite.sqlSession.SqlSession;
import cn.elite.sqlSession.SqlSessionFactory;
import cn.elite.sqlSession.SqlSessionFactoryBuilder;
import org.dom4j.DocumentException;
import org.junit.Test;

import java.beans.PropertyVetoException;
import java.io.InputStream;
import java.util.List;

/**
 * @Author: eliteMade
 * @Date: 2020/8/15 14:59
 * @Version: 1.0
 */
public class IMybatisTest {

    @Test
    public void test() throws Exception {
        InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();

        User user = new User();
        user.setId(1);
        user.setUsername("tom");
        /*User user2 = sqlSession.selectOne("cn.elite.dao.IUserDao.findByCondition", user);
        System.out.println(user2);*/
        /*List<User> objects = sqlSession.selectList("cn.elite.dao.IUserDao.findAll", user);
        for (User user3 : objects) {
            System.out.println(user3);
        }*/
        /*IUserDao userDao = sqlSession.getMapper(IUserDao.class);
        List<User> all = userDao.findAll();
        for (User user4 : all) {
            System.out.println(user4);
        }*/
        IUserDao userDao = sqlSession.getMapper(IUserDao.class);
        User user5 = userDao.findByCondition(user);
        System.out.println(user5);
    }
}
