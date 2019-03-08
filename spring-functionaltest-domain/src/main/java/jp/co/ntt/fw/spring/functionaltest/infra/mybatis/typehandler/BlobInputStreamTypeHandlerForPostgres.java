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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BlobInputStreamTypeHandlerForPostgres extends
                                                   BaseTypeHandler<InputStream> {
    private static final Logger logger = LoggerFactory.getLogger(
            BlobInputStreamTypeHandlerForPostgres.class);

    // (2)
    @Override
    public void setNonNullParameter(PreparedStatement ps, int i,
            InputStream parameter, JdbcType jdbcType) throws SQLException {
        try {
            ps.setBytes(i, IOUtils.toByteArray(parameter));
        } catch (IOException e) {
            logger.warn("I/O Error", e);
        }
    }

    // (3)
    @Override
    public InputStream getNullableResult(ResultSet rs,
            String columnName) throws SQLException {
        return toInputStream(rs.getBytes(columnName));
    }

    // (3)
    @Override
    public InputStream getNullableResult(ResultSet rs,
            int columnIndex) throws SQLException {
        return toInputStream(rs.getBytes(columnIndex));
    }

    // (3)
    @Override
    public InputStream getNullableResult(CallableStatement cs,
            int columnIndex) throws SQLException {
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
