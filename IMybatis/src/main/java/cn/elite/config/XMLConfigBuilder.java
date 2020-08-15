package cn.elite.config;

import cn.elite.io.Resources;
import cn.elite.pojo.Configuration;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;

/**
 * @Author: eliteMade
 * @Date: 2020/8/15 16:05
 * @Version: 1.0
 */
public class XMLConfigBuilder {
    private Configuration configuration;

    public XMLConfigBuilder() {
        this.configuration = new Configuration();
    }

    public Configuration parseConfig(InputStream in) throws DocumentException, PropertyVetoException {
        Document document = new SAXReader().read(in);
        Element rootElement = document.getRootElement();
        //Configration
        List<Element> propertyList = rootElement.selectNodes("//property");
        Properties properties = new Properties();
        for (Element element : propertyList) {
            String name = element.attributeValue("name");
            String value = element.attributeValue("value");
            properties.setProperty(name,value);
        }
        ComboPooledDataSource comboPooledDataSource = new ComboPooledDataSource();
        comboPooledDataSource.setDriverClass(properties.getProperty("driverClass"));
        comboPooledDataSource.setJdbcUrl(properties.getProperty("jdbcUrl"));
        comboPooledDataSource.setUser(properties.getProperty("username"));
        comboPooledDataSource.setPassword(properties.getProperty("password"));

        configuration.setDataSource(comboPooledDataSource);
        //mapper的解析
        List<Element> mapperList = rootElement.selectNodes("//Mapper");

        for (Element element : mapperList) {
            String mapperPath = element.attributeValue("resource");
            InputStream resourceAsStream = Resources.getResourceAsStream(mapperPath);
            XMLMapperBuilder xmlMapperBuilder = new XMLMapperBuilder(configuration);
            xmlMapperBuilder.parse(resourceAsStream);
        }
        return configuration;
    }
}
