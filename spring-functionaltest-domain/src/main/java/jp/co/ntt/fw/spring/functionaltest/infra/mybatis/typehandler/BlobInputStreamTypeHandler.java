/*
 * Copyright(c) 2014 NTT Corporation.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
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
    public InputStream getNullableResult(ResultSet rs,
            String columnName) throws SQLException {
        return toInputStream(rs.getBlob(columnName));
    }

    // (3)
    @Override
    public InputStream getNullableResult(ResultSet rs,
            int columnIndex) throws SQLException {
        return toInputStream(rs.getBlob(columnIndex));
    }

    // (3)
    @Override
    public InputStream getNullableResult(CallableStatement cs,
            int columnIndex) throws SQLException {
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
