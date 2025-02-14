/*
 * Copyright(c) 2024 NTT Corporation.
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
package jp.co.ntt.fw.spring.functionaltest.app.djpa;

import org.mapstruct.Condition;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.security.crypto.codec.Hex;
import jp.co.ntt.fw.spring.functionaltest.domain.model.JPABook;
import jp.co.ntt.fw.spring.functionaltest.domain.model.JPABookEG;
import jp.co.ntt.fw.spring.functionaltest.domain.model.JPABookLZ;

@Mapper
public interface DJPABeanMapper {

    @Mapping(target = "category.categoryName", source = "categoryName")
    JPABook map(BookForm bookForm);

    @Mapping(target = "categoryName", source = "category.categoryName")
    @Mapping(target = "category", ignore = true)
    BookForm map(JPABook jpaBook);

    @Mapping(target = "category.categoryName", source = "categoryName")
    JPABookLZ mapLZ(BookForm bookForm);

    @Mapping(target = "categoryName", source = "category.categoryName")
    @Mapping(target = "category", ignore = true)
    BookForm mapLZ(JPABookLZ jpaBookLZ);

    @Mapping(target = "category.categoryName", source = "categoryName")
    JPABookEG mapEG(BookForm bookForm);

    @Mapping(target = "categoryName", source = "category.categoryName")
    @Mapping(target = "category", ignore = true)
    BookForm mapEG(JPABookEG jpaBookEG);

    @Mapping(target = "category.categoryName", source = "categoryName")
    JPABookEG map(BookUpdateForm bookUpdateForm);

    @Mapping(target = "categoryName", source = "category.categoryName")
    @Mapping(target = "category", ignore = true)
    BookUpdateForm mapEGToForm(JPABookEG jpaBookEG);

    default String byteToString(byte[] source) {
        String src = null;
        if (source != null) {
            src = new String(Hex.encode(source)).toUpperCase();
        }
        return src;
    }

    default byte[] stringToByte(String source) {
        byte[] dest = null;
        if (source != null) {
            dest = Hex.decode(source);
        }
        return dest;
    }

    @Condition
    default boolean isNotEmpty(String value) {
        return value != null && !value.isEmpty();
    }

}
