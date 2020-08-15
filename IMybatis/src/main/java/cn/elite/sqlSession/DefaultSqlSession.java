package cn.elite.sqlSession;

import cn.elite.pojo.Configuration;
import cn.elite.pojo.MappedStatement;

import java.lang.reflect.*;
import java.util.List;

/**
 * @author eliteMade
 * @date 2020/8/15 17:08
 * @version 1.0
 */
public class DefaultSqlSession implements SqlSession {

    private Configuration configuration;

    public DefaultSqlSession(Configuration configuration) {
        this.configuration = configuration;
    }

    @Override
    public <E> List<E> selectList(String statementId, Object... params) throws Exception {
        Executor executor = new SimpleExecutor();
        MappedStatement mappedStatement = configuration.getMappedStatementMap().get(statementId);
        return executor.query(configuration,mappedStatement,params);
    }

    @Override
    public <T> T selectOne(String statementId, Object... params) throws Exception {
        List<Object> objects = selectList(statementId, params);
        if (objects.size()==1){
            return (T)objects.get(0);
        }else{
            throw new RuntimeException("值为空或者值过多!");
        }
    }

    @Override
    public <T> T getMapper(Class<?> mapperClass){
        return (T)Proxy.newProxyInstance(DefaultSqlSession.class.getClassLoader(), new Class[]{mapperClass}, new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                //1.String statementId   2 Object... params
                //方法名: findAll
                String methodName = method.getName();
                //类全限定类名
                String className = method.getDeclaringClass().getName();
                String statementID = className+"."+methodName;
                //准备参数
                Type returnType = method.getGenericReturnType();
                //返回值是否存在参数泛型 是:查找list 否:查找一个
                if(returnType instanceof ParameterizedType){
                    List<Object> objects = selectList(statementID, args);
                    return objects;
                }
                return selectOne(statementID,args);
            }
        });
    }

}
