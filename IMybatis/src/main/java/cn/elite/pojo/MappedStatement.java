package cn.elite.pojo;

/**
 * @Author: eliteMade
 * @Date: 2020/8/15 15:07
 * @Version: 1.0
 */
public class MappedStatement {
    /**
     * id标识
     * */
    private String id;
    /**
     * 返回值类型
     * */
    private String resultType;
    /**
     * 参数类型
     * */
    private String paramType;
    /**
     * statement类型 select/insert/update/delete
     */
    private String sqlCommandType;
    /**
     * SQL信息
     */
    private String sql;

    public String getSqlCommandType() {
        return sqlCommandType;
    }

    public void setSqlCommandType(String sqlCommandType) {
        this.sqlCommandType = sqlCommandType;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getResultType() {
        return resultType;
    }

    public void setResultType(String resultType) {
        this.resultType = resultType;
    }

    public String getParamType() {
        return paramType;
    }

    public void setParamType(String paramType) {
        this.paramType = paramType;
    }

    public String getSql() {
        return sql;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }

    @Override
    public String toString() {
        return "MappedStatement{" +
                "id='" + id + '\'' +
                ", resultType='" + resultType + '\'' +
                ", paramType='" + paramType + '\'' +
                ", sql='" + sql + '\'' +
                '}';
    }
}
