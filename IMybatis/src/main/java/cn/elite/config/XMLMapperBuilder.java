package cn.elite.config;

import cn.elite.pojo.Configuration;
import cn.elite.pojo.MappedStatement;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.InputStream;
import java.util.List;

/**
 * @Author: eliteMade
 * @Date: 2020/8/15 16:48
 * @Version: 1.0
 */
public class XMLMapperBuilder {
    private Configuration configuration;
    public XMLMapperBuilder(Configuration configuration) {
        this.configuration = configuration;
    }

    public void parse(InputStream resourceAsStream) throws DocumentException {
        Document document = new SAXReader().read(resourceAsStream);
        //Mapper
        Element rootElement = document.getRootElement();
        String namespace = rootElement.attributeValue("namespace");
        List<Element> sqlList = rootElement.selectNodes("//select");
        for (Element element : sqlList) {
            String id = element.attributeValue("id");
            String resultType = element.attributeValue("resultType");
            String paramType = element.attributeValue("paramType");
            String sqlText = element.getTextTrim();
            MappedStatement mappedStatement = new MappedStatement();
            mappedStatement.setId(id);
            mappedStatement.setResultType(resultType);
            mappedStatement.setParamType(paramType);
            mappedStatement.setSql(sqlText);
            String key = namespace+"."+id;
            configuration.getMappedStatementMap().put(key,mappedStatement);
        }
    }
}

