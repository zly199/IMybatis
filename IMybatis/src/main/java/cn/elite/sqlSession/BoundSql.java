package cn.elite.sqlSession;

import cn.elite.utils.ParameterMapping;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: eliteMade
 * @Date: 2020/8/15 17:48
 * @Version: 1.0
 * 存储?形式的SQL,以及处理过后的SQL占位符
 */
public class BoundSql {
    private String sql;
    private List<ParameterMapping> parameterMappings= new ArrayList<>();

    public BoundSql(String sql, List<ParameterMapping> parameterMappings) {
        this.sql = sql;
        this.parameterMappings = parameterMappings;
    }

    public String getSql() {
        return sql;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }

    public List<ParameterMapping> getParameterMappings() {
        return parameterMappings;
    }

    public void setParameterMappings(List<ParameterMapping> parameterMappings) {
        this.parameterMappings = parameterMappings;
    }
}
