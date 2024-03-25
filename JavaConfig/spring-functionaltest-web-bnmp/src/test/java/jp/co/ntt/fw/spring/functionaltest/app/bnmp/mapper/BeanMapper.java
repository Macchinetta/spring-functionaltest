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
package jp.co.ntt.fw.spring.functionaltest.app.bnmp.mapper;

import java.util.Map;

import org.mapstruct.Condition;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.SubclassMapping;
import org.springframework.util.StringUtils;

import jp.co.ntt.fw.spring.functionaltest.app.bnmp.sourcebean.AccountForm;
import jp.co.ntt.fw.spring.functionaltest.app.bnmp.sourcebean.CustomMappingSource;
import jp.co.ntt.fw.spring.functionaltest.app.bnmp.sourcebean.FormatSource;
import jp.co.ntt.fw.spring.functionaltest.app.bnmp.sourcebean.MultipleMappingSource;
import jp.co.ntt.fw.spring.functionaltest.app.bnmp.sourcebean.MultipleSourceInterfaceImpl;
import jp.co.ntt.fw.spring.functionaltest.app.bnmp.sourcebean.NestSource;
import jp.co.ntt.fw.spring.functionaltest.app.bnmp.sourcebean.Source;
import jp.co.ntt.fw.spring.functionaltest.app.bnmp.sourcebean.SourceInterface;
import jp.co.ntt.fw.spring.functionaltest.app.bnmp.sourcebean.SourceInterfaceImpl;
import jp.co.ntt.fw.spring.functionaltest.app.bnmp.targetbean.Account;
import jp.co.ntt.fw.spring.functionaltest.app.bnmp.targetbean.CustomMappingTarget;
import jp.co.ntt.fw.spring.functionaltest.app.bnmp.targetbean.DefaultConstructorTarget;
import jp.co.ntt.fw.spring.functionaltest.app.bnmp.targetbean.DifferentFieldNameTarget;
import jp.co.ntt.fw.spring.functionaltest.app.bnmp.targetbean.DifferentFieldTypeTarget;
import jp.co.ntt.fw.spring.functionaltest.app.bnmp.targetbean.FormatTarget;
import jp.co.ntt.fw.spring.functionaltest.app.bnmp.targetbean.MultipleMappingTarget;
import jp.co.ntt.fw.spring.functionaltest.app.bnmp.targetbean.NestedMappingTarget;
import jp.co.ntt.fw.spring.functionaltest.app.bnmp.targetbean.NoArgumentConstructorTarget;
import jp.co.ntt.fw.spring.functionaltest.app.bnmp.targetbean.PublicConstructorTarget;
import jp.co.ntt.fw.spring.functionaltest.app.bnmp.targetbean.Target;

@Mapper(uses = StringLogic.class)
public interface BeanMapper {

    // 0101001
    Target map(Source source);

    // 0102001
    DifferentFieldTypeTarget differentFieldTypeMap(Source source);

    // 0103001
    @Mapping(target = "targetId", source = "id")
    DifferentFieldNameTarget differentFieldNameMap(Source source);

    // 0104001
    @Mapping(target = "deptId", source = "deptSource.deptId")
    @Mapping(target = "deptName", source = "deptSource.deptName")
    NestedMappingTarget nestedMap(NestSource source);

    // 0104002
    @Mapping(target = ".", source = "deptSource")
    NestedMappingTarget nestedAllMap(NestSource source);

    // 0105001
    void map(Source source, @MappingTarget Target target);

    // 0106001
    Account collectionMap(AccountForm accountForm);

    // 0106002
    void collectionMap(AccountForm accountForm, @MappingTarget Account account);

    // 0107001
    @Mapping(target = "name", source = "mapName")
    Target mapToBeanMap(Map<String, String> map);

    // 0108001
    @Mapping(target = "id", source = "source1.id")
    MultipleMappingTarget multipleMap(Source source1,
            MultipleMappingSource source2);

    // 0109001
    Target interfaceMap(SourceInterface source);

    // 0109002
    MultipleMappingTarget subclassMap(SourceInterfaceImpl source);

    MultipleMappingTarget subclassMap(MultipleSourceInterfaceImpl source);

    @SubclassMapping(source = SourceInterfaceImpl.class, target = MultipleMappingTarget.class)
    @SubclassMapping(source = MultipleSourceInterfaceImpl.class, target = MultipleMappingTarget.class)
    MultipleMappingTarget multipleInterfaceMap(SourceInterface source);

    // 0110001
    @Mapping(target = "name", ignore = true)
    void exceptingMap(Source source, @MappingTarget Target target);

    // 0201002
    @Mapping(target = "name", qualifiedByName = "toUpper")
    CustomMappingTarget customMap(CustomMappingSource source);

    @Named("toUpper")
    default String stringToUpper(String string) {

        if (string == null) {
            return null;
        }

        return string.toUpperCase();
    }

    // 0201003
    @Mapping(target = "name", qualifiedByName = { "StringConverter",
            "UpperConverter" })
    CustomMappingTarget separateClassCustomMap(CustomMappingSource source);

    // 0202001
    @Mapping(target = "name", defaultValue = "DefaultName")
    Target nullDefaultValueMap(Source source);

    // 0202002
    @Mapping(target = "name", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void nullIgnoreMap(Source source, @MappingTarget Target target);

    // 0203002
    @Mapping(target = "name", conditionQualifiedByName = "NotEmpty")
    void conditionalMap(CustomMappingSource source,
            @MappingTarget CustomMappingTarget target);

    @Named("NotEmpty")
    @Condition
    default boolean isNotEmpty(String string) {
        return StringUtils.hasLength(string);
    }

    // 0204001
    @Mapping(target = "number", numberFormat = "000,000")
    @Mapping(target = "date", dateFormat = "uuuu-MM-dd HH:mm:ss")
    FormatTarget formattedMap(FormatSource source);

    // 0301001
    DefaultConstructorTarget defaultConstructorMap(Source source);

    // 0301002
    PublicConstructorTarget publicConstructorMap(Source source);

    // 0301003
    NoArgumentConstructorTarget noArgumentConstructorMap(Source source);
}
