package cn.elite.sqlSession;

import cn.elite.pojo.Configuration;
import cn.elite.pojo.MappedStatement;
import cn.elite.utils.GenericTokenParser;
import cn.elite.utils.ParameterMapping;
import cn.elite.utils.ParameterMappingTokenHandler;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: eliteMade
 * @Date: 2020/8/15 17:29
 * @Version: 1.0
 */
public class SimpleExecutor implements Executor{
    @Override
    public <T> List<T> query(Configuration configuration, MappedStatement mappedStatement, Object... params) throws Exception {
        //1.获取链接
        Connection connection = configuration.getDataSource().getConnection();
        //2.获取SQL语句:sql : select * from user where id = #{id} and username=#{username}
        String sql = mappedStatement.getSql();
        BoundSql boundSql = getBoundSql(sql);
        //3.获取预处理对象
        PreparedStatement preparedStatement = connection.prepareStatement(boundSql.getSql());
        //4.设置参数
        List<ParameterMapping> parameterMappings = boundSql.getParameterMappings();
        //参数类型
        String paramType = mappedStatement.getParamType();
        Class<?> paramTypeClass = getClassType(paramType);
        for (int i = 0; i < parameterMappings.size(); i++) {
            ParameterMapping parameterMapping = parameterMappings.get(i);
            //属性名
            String content = parameterMapping.getContent();
            Field declaredField = paramTypeClass.getDeclaredField(content);
            //设置暴力访问
            declaredField.setAccessible(true);
            Object filedValue = declaredField.get(params[0]);
            preparedStatement.setObject(i+1,filedValue);
        }
        //5.执行SQL
        ResultSet resultSet = preparedStatement.executeQuery();
        String resultType = mappedStatement.getResultType();
        Class<?> resultTypeClass = getClassType(resultType);

        //6.返回结果集
        ArrayList<Object> objects = new ArrayList<>();
        while (resultSet.next()){
            ResultSetMetaData metaData = resultSet.getMetaData();
            //结果实体
            Object o = resultTypeClass.newInstance();
            for (int i = 1; i <= metaData.getColumnCount(); i++) {
                String columnName = metaData.getColumnName(i);
                Object filedValue = resultSet.getObject(i);
                //内省
                PropertyDescriptor propertyDescriptor = new PropertyDescriptor(columnName,resultTypeClass);
                Method writeMethod = propertyDescriptor.getWriteMethod();
                writeMethod.invoke(o,filedValue);
            }
            objects.add(o);
        }
        return (List<T>)objects;
    }

    private Class<?> getClassType(String classType) throws ClassNotFoundException {
        if(classType!=null){
            Class<?> aClass = Class.forName(classType);
            return aClass;
        }else{
            return null;
        }
    }


    /**
     * 解析sql占位符,并存储占位符信息
     * @param sql
     * @return
     */
    private BoundSql getBoundSql(String sql) {
        ParameterMappingTokenHandler parameterMappingTokenHandler = new ParameterMappingTokenHandler();
        GenericTokenParser genericTokenParser = new GenericTokenParser("#{", "}", parameterMappingTokenHandler);
        String sqlParse = genericTokenParser.parse(sql);

        List<ParameterMapping> parameterMappings = parameterMappingTokenHandler.getParameterMappings();
        BoundSql boundSql = new BoundSql(sqlParse, parameterMappings);
        return boundSql;
    }
}
