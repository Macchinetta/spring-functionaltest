/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.infra.mybatis.typehandler;

import java.io.InputStream;
import java.sql.Blob;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

public class BlobInputStreamTypeHandler extends BaseTypeHandler<InputStream> {
    // (2)
    @Override
    public void setNonNullParameter(PreparedStatement ps, int i,
            InputStream parameter, JdbcType jdbcType) throws SQLException {
        ps.setBlob(i, parameter);
    }

    // (3)
    @Override
    public InputStream getNullableResult(ResultSet rs, String columnName) throws SQLException {
        return toInputStream(rs.getBlob(columnName));
    }

    // (3)
    @Override
    public InputStream getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        return toInputStream(rs.getBlob(columnIndex));
    }

    // (3)
    @Override
    public InputStream getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        return toInputStream(cs.getBlob(columnIndex));
    }

    private InputStream toInputStream(Blob blob) throws SQLException {
        // (4)
        if (blob == null) {
            return null;
        } else {
            return blob.getBinaryStream();
        }
    }

}
