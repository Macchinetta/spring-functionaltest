/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.infra.mybatis.typehandler;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.sql.CallableStatement;
import java.sql.Clob;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.commons.io.IOUtils;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

public class ClobReaderTypeHandlerForPostgres extends BaseTypeHandler<Reader> {
    // (2)
    @Override
    public void setNonNullParameter(PreparedStatement ps, int i,
            Reader parameter, JdbcType jdbcType) throws SQLException {
        String result = "";
        try {
            result = IOUtils.toString(parameter);
        } catch (IOException e) {
            e.printStackTrace();
        }
        ps.setString(i, result);
    }

    // (3)
    @Override
    public Reader getNullableResult(ResultSet rs, String columnName) throws SQLException {
        return toReader(rs.getString(columnName));
    }

    // (3)
    @Override
    public Reader getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        return toReader(rs.getString(columnIndex));
    }

    // (3)
    @Override
    public Reader getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        return toReader(cs.getString(columnIndex));
    }

    private Reader toReader(String stringVal) throws SQLException {
        // (4)
        if (stringVal == null) {
            return null;
        } else {
            return new StringReader(stringVal);
        }
    }
}
