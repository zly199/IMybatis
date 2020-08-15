package cn.elite.sqlSession;

import cn.elite.config.XMLConfigBuilder;
import cn.elite.pojo.Configuration;
import org.dom4j.DocumentException;

import java.beans.PropertyVetoException;
import java.io.InputStream;

/**
 * @Author: eliteMade
 * @Date: 2020/8/15 16:04
 * @Version: 1.0
 */
public class SqlSessionFactoryBuilder {
    public SqlSessionFactory build(InputStream in) throws DocumentException, PropertyVetoException {
        //解析xml内容,封装xml
        XMLConfigBuilder xmlConfigBuilder = new XMLConfigBuilder();
        Configuration configuration = xmlConfigBuilder.parseConfig(in);
        //创建SqlSessionFactory对象
        DefaultSqlSessionFactory defaultSqlSessionFactory = new DefaultSqlSessionFactory(configuration);
        return defaultSqlSessionFactory;
    }
}
