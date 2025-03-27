/*
 * Copyright(c) 2025 NTT Corporation.
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
package jp.co.ntt.fw.spring.functionaltest.infra.mybatis.manual.typehandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.springframework.util.StringUtils;
import jp.co.ntt.fw.spring.functionaltest.domain.model.HandlerObj;

public class CustomTypeHandler extends BaseTypeHandler<HandlerObj> {

    @Override
    public HandlerObj getNullableResult(ResultSet rs, String columnName) throws SQLException {
        return getObj(rs.getString(columnName));
    }

    @Override
    public HandlerObj getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        return getObj(rs.getString(columnIndex));
    }

    @Override
    public HandlerObj getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        return getObj(cs.getString(columnIndex));
    }

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, HandlerObj parameter,
            JdbcType jdbcType) throws SQLException {
        ps.setString(i, objToString(parameter));

    }

    private HandlerObj getObj(String value) {

        if (StringUtils.hasText(value)) {
            String[] parts = value.split("/");
            if (parts.length == 3) {
                return new HandlerObj(parts[0], parts[1], Integer.valueOf(parts[2]));
            }
        }

        return null;
    }

    private String objToString(HandlerObj handlerObj) {
        return handlerObj.getValue1() + "/" + handlerObj.getValue2() + "/" + handlerObj.getValue3();
    }

}
