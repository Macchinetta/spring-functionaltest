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
