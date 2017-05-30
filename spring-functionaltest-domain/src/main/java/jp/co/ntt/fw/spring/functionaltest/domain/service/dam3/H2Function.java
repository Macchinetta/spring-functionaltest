/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.domain.service.dam3;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.stereotype.Component;

/**
 * Class to implement the stored procedure call from H2 Database.
 */
@Component
public class H2Function {

    public static ResultSet findTodo(Connection con, String todoId) throws SQLException {

        String sql = "select * from t_todo where todo_id = ?";
        PreparedStatement preparedStatement = con.prepareStatement(sql);
        preparedStatement.setString(1, todoId);

        ResultSet resultSet = preparedStatement.executeQuery();

        return resultSet;
    }

}
