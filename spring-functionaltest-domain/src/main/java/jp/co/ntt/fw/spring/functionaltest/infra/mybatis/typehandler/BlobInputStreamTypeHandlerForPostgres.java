/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.infra.mybatis.typehandler;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.commons.io.IOUtils;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

public class BlobInputStreamTypeHandlerForPostgres extends
                                                  BaseTypeHandler<InputStream> {
    // (2)
    @Override
    public void setNonNullParameter(PreparedStatement ps, int i,
            InputStream parameter, JdbcType jdbcType) throws SQLException {
        try {
            ps.setBytes(i, IOUtils.toByteArray(parameter));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    // (3)
    @Override
    public InputStream getNullableResult(ResultSet rs, String columnName) throws SQLException {
        return toInputStream(rs.getBytes(columnName));
    }

    // (3)
    @Override
    public InputStream getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        return toInputStream(rs.getBytes(columnIndex));
    }

    // (3)
    @Override
    public InputStream getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        return toInputStream(cs.getBytes(columnIndex));
    }

    private InputStream toInputStream(byte[] bytes) throws SQLException {
        // (4)
        if (bytes == null) {
            return null;
        } else {
            InputStream is = new ByteArrayInputStream(bytes);
            return is;
        }
    }

}
