/*
 * Copyright 2014-2017 NTT Corporation.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ClobReaderTypeHandlerForPostgres extends BaseTypeHandler<Reader> {

    private static final Logger logger = LoggerFactory.getLogger(
            BlobInputStreamTypeHandlerForPostgres.class);

    // (2)
    @Override
    public void setNonNullParameter(PreparedStatement ps, int i,
            Reader parameter, JdbcType jdbcType) throws SQLException {
        String result = "";
        try {
            result = IOUtils.toString(parameter);
        } catch (IOException e) {
            logger.warn("I/O Error", e);
        }
        ps.setString(i, result);
    }

    // (3)
    @Override
    public Reader getNullableResult(ResultSet rs,
            String columnName) throws SQLException {
        return toReader(rs.getString(columnName));
    }

    // (3)
    @Override
    public Reader getNullableResult(ResultSet rs,
            int columnIndex) throws SQLException {
        return toReader(rs.getString(columnIndex));
    }

    // (3)
    @Override
    public Reader getNullableResult(CallableStatement cs,
            int columnIndex) throws SQLException {
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
