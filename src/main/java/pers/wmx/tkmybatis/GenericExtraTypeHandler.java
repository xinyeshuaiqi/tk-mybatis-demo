package pers.wmx.tkmybatis;

import com.alibaba.fastjson.JSON;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.TypeHandler;

import java.sql.*;

/**
 * @author wmx
 * @date 2019-12-16
 */
@MappedJdbcTypes({JdbcType.LONGNVARCHAR})
public class GenericExtraTypeHandler<T> implements TypeHandler<T> {

    private Class<T> type;

    public GenericExtraTypeHandler(Class<T> type) {
        if (type == null) {
            throw new IllegalArgumentException("type argument cannot be null");
        }
        this.type = type;
    }

    @Override
    public void setParameter(PreparedStatement preparedStatement, int i, T parameter, JdbcType jdbcType) throws SQLException {
        if(parameter == null){
            preparedStatement.setNull(i, Types.LONGNVARCHAR);
        }else{
            preparedStatement.setString(i, JSON.toJSONString(parameter));
        }
    }

    @Override
    public T getResult(ResultSet resultSet, String columnName) throws SQLException {
        String columnValue = resultSet.getString(columnName);
        return getExtra(columnValue);
    }

    @Override
    public T getResult(ResultSet resultSet, int i) throws SQLException {
        String columnValue = resultSet.getString(i);
        return getExtra(columnValue);
    }

    @Override
    public T getResult(CallableStatement callableStatement, int i) throws SQLException {
        String columnValue = callableStatement.getString(i);
        return getExtra(columnValue);
    }

    private T getExtra(String columnValue) {
        if(columnValue == null){
            return null;
        }
        return  JSON.parseObject(columnValue,type);
    }
}
