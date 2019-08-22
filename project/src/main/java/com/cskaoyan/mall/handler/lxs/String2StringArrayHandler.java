package com.cskaoyan.mall.handler.lxs;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;
import org.apache.ibatis.type.TypeHandler;

import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
@MappedJdbcTypes(JdbcType.VARCHAR)
@MappedTypes(String[].class)
public class String2StringArrayHandler implements TypeHandler<String[]> {

    @Override
    public void setParameter(PreparedStatement preparedStatement, int i, String[] strings, JdbcType jdbcType) throws SQLException {
        ObjectMapper objectMapper = new ObjectMapper();
        String value = null;
        try {
            //将object转换成json字符串
            value = objectMapper.writeValueAsString(strings);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        //将object转换成的值 配置过去
       /* preparedStatement.setString(index,value);
        StringBuffer stringBuffer = new StringBuffer();
        for (String string : strings) {
            stringBuffer.append(string).append(",");
        }
        String s = stringBuffer.substring(0,stringBuffer.length()-1);*/
        preparedStatement.setString(i,value);
    }

    @Override
    public String[] getResult(ResultSet resultSet, String s) throws SQLException {
        String string = resultSet.getString(s);
        return transStringtoStringArray(string);
    }

    @Override
    public String[] getResult(ResultSet resultSet, int i) throws SQLException {
        String string = resultSet.getString(i);
        return transStringtoStringArray(string);
    }

    @Override
    public String[] getResult(CallableStatement callableStatement, int i) throws SQLException {
        String string = callableStatement.getString(i);
        return transStringtoStringArray(string);
    }
    private String[] transStringtoStringArray(String s){
        ObjectMapper objectMapper = new ObjectMapper();
        String[] strings = new String[0];
        try {
            strings = objectMapper.readValue(s, String[].class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        /*String[] array = s.split(",");
        System.out.println(array);*/
        return strings;
    }
}
