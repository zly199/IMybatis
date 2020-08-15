package cn.elite.pojo;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: eliteMade
 * @Date: 2020/8/15 15:07
 * @Version: 1.0
 */
public class Configuration {
    private DataSource dataSource;
    /**
     * key statementId
     */
    private Map<String,MappedStatement> mappedStatementMap = new HashMap<>();

    public DataSource getDataSource() {
        return dataSource;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public Map<String, MappedStatement> getMappedStatementMap() {
        return mappedStatementMap;
    }

    public void setMappedStatementMap(Map<String, MappedStatement> mappedStatementMap) {
        this.mappedStatementMap = mappedStatementMap;
    }
}
