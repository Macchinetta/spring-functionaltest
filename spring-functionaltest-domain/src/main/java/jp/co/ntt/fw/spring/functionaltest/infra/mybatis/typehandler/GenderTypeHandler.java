/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.infra.mybatis.typehandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jp.co.ntt.fw.spring.functionaltest.domain.model.Gender;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

public class GenderTypeHandler extends BaseTypeHandler<Gender> {

    @Override
    public Gender getNullableResult(ResultSet rs, String columnName) throws SQLException {
        return getByCode(rs.getString(columnName));
    }

    @Override
    public Gender getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        return getByCode(rs.getString(columnIndex));
    }

    @Override
    public Gender getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        return getByCode(cs.getString(columnIndex));
    }

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i,
            Gender parameter, JdbcType jdbcType) throws SQLException {
        ps.setString(i, parameter.getCode());

    }

    private Gender getByCode(String byCode) {
        if (byCode == null) {
            return null;
        } else {
            return Gender.getByCode(byCode);
        }
    }

}
