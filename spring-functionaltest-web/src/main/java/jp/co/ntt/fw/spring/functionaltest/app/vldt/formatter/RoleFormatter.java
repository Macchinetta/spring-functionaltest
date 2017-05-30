/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.app.vldt.formatter;

import java.text.ParseException;
import java.util.Locale;

import org.springframework.format.Formatter;

import jp.co.ntt.fw.spring.functionaltest.domain.model.Role;

public class RoleFormatter implements Formatter<Role> {

    @Override
    public String print(Role source, Locale locale) {
        return source.getRole();
    }

    @Override
    public Role parse(String source, Locale locale) throws ParseException {
        Role role = new Role();
        role.setRole(source);
        return role;
    }
}
