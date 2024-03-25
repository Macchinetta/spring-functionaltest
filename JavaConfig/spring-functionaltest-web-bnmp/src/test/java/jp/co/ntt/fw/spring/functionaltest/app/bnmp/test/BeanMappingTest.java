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
package jp.co.ntt.fw.spring.functionaltest.app.bnmp.test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.Appender;
import jakarta.inject.Inject;
import jp.co.ntt.fw.spring.functionaltest.app.bnmp.mapper.AccessorOnlyBeanMapper;
import jp.co.ntt.fw.spring.functionaltest.app.bnmp.mapper.AdderPreferredBeanMapper;
import jp.co.ntt.fw.spring.functionaltest.app.bnmp.mapper.BeanMapper;
import jp.co.ntt.fw.spring.functionaltest.app.bnmp.mapper.ConditionalBeanMapper;
import jp.co.ntt.fw.spring.functionaltest.app.bnmp.mapper.CustomBeanMapper;
import jp.co.ntt.fw.spring.functionaltest.app.bnmp.mapper.SetterPreferredBeanMapper;
import jp.co.ntt.fw.spring.functionaltest.app.bnmp.mapper.TargetImmutableBeanMapper;
import jp.co.ntt.fw.spring.functionaltest.app.bnmp.sourcebean.AccountForm;
import jp.co.ntt.fw.spring.functionaltest.app.bnmp.sourcebean.CustomMappingSource;
import jp.co.ntt.fw.spring.functionaltest.app.bnmp.sourcebean.DeptSource;
import jp.co.ntt.fw.spring.functionaltest.app.bnmp.sourcebean.FormatSource;
import jp.co.ntt.fw.spring.functionaltest.app.bnmp.sourcebean.MultipleMappingSource;
import jp.co.ntt.fw.spring.functionaltest.app.bnmp.sourcebean.MultipleSourceInterfaceImpl;
import jp.co.ntt.fw.spring.functionaltest.app.bnmp.sourcebean.NestSource;
import jp.co.ntt.fw.spring.functionaltest.app.bnmp.sourcebean.Source;
import jp.co.ntt.fw.spring.functionaltest.app.bnmp.sourcebean.SourceInterfaceImpl;
import jp.co.ntt.fw.spring.functionaltest.app.bnmp.targetbean.Account;
import jp.co.ntt.fw.spring.functionaltest.app.bnmp.targetbean.CustomMappingTarget;
import jp.co.ntt.fw.spring.functionaltest.app.bnmp.targetbean.DefaultConstructorTarget;
import jp.co.ntt.fw.spring.functionaltest.app.bnmp.targetbean.DifferentFieldNameTarget;
import jp.co.ntt.fw.spring.functionaltest.app.bnmp.targetbean.DifferentFieldTypeTarget;
import jp.co.ntt.fw.spring.functionaltest.app.bnmp.targetbean.EmailDto;
import jp.co.ntt.fw.spring.functionaltest.app.bnmp.targetbean.FormatTarget;
import jp.co.ntt.fw.spring.functionaltest.app.bnmp.targetbean.MultipleMappingTarget;
import jp.co.ntt.fw.spring.functionaltest.app.bnmp.targetbean.NestedMappingTarget;
import jp.co.ntt.fw.spring.functionaltest.app.bnmp.targetbean.NoArgumentConstructorTarget;
import jp.co.ntt.fw.spring.functionaltest.app.bnmp.targetbean.PublicConstructorTarget;
import jp.co.ntt.fw.spring.functionaltest.app.bnmp.targetbean.Target;
import jp.co.ntt.fw.spring.functionaltest.config.SpringMvcTestBnmpConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { SpringMvcTestBnmpConfig.class })
public class BeanMappingTest {

    @Inject
    BeanMapper beanMapper;

    @Inject
    CustomBeanMapper customBeanMapper;

    @Inject
    ConditionalBeanMapper conditionalBeanMapper;

    @Inject
    AccessorOnlyBeanMapper accessorOnlyBeanMapper;

    @Inject
    SetterPreferredBeanMapper setterPreferredBeanMapper;

    @Inject
    AdderPreferredBeanMapper adderPreferredBeanMapper;

    @Inject
    TargetImmutableBeanMapper targetImmutableBeanMapper;

    @SuppressWarnings("unchecked")
    private Appender<ILoggingEvent> mockAppender = mock(Appender.class);

    @Captor
    private ArgumentCaptor<ILoggingEvent> captorLoggingEvent = ArgumentCaptor
            .forClass(ILoggingEvent.class);

    @Test
    public void testBNMP0101001() {

        Source source = new Source();

        source.setId(1);
        source.setName("SourceName");

        Target target = beanMapper.map(source);

        assertThat(target.getId(), is(1));
        assertThat(target.getName(), is("SourceName"));
    }

    @Test
    public void testBNMP0102001() {

        Source source = new Source();

        source.setName("123.45");

        DifferentFieldTypeTarget target = this.beanMapper.differentFieldTypeMap(
                source);

        assertThat(target.getName(), is(new BigDecimal("123.45")));
    }

    @Test
    public void testBNMP0103001() {

        Source source = new Source();

        source.setId(1);
        source.setName("SourceName");

        DifferentFieldNameTarget target = this.beanMapper.differentFieldNameMap(
                source);

        assertThat(target.getTargetId(), is(1));
        assertThat(target.getTargetName(), nullValue());
    }

    @Test
    public void testBNMP0104001() {

        NestSource source = new NestSource();

        DeptSource deptSource = new DeptSource();

        deptSource.setDeptId("D01");
        deptSource.setDeptName("DeptName");

        source.setDeptSource(deptSource);

        NestedMappingTarget target = this.beanMapper.nestedMap(source);

        assertThat(target.getDeptId(), is("D01"));
        assertThat(target.getDeptName(), is("DeptName"));
    }

    @Test
    public void testBNMP0104002() {

        NestSource source = new NestSource();

        DeptSource deptSource = new DeptSource();

        deptSource.setDeptId("D01");
        deptSource.setDeptName("DeptName");

        source.setDeptSource(deptSource);

        NestedMappingTarget target = this.beanMapper.nestedAllMap(source);

        assertThat(target.getDeptId(), is("D01"));
        assertThat(target.getDeptName(), is("DeptName"));
    }

    @Test
    public void testBNMP0105001() {

        Source source = new Source();

        source.setId(1);
        source.setName("SourceName");

        Target target = new Target();

        target.setId(2);
        target.setName("TargetName");

        this.beanMapper.map(source, target);

        assertThat(target.getId(), is(1));
        assertThat(target.getName(), is("SourceName"));
    }

    @Test
    public void testBNMP0106001() {

        AccountForm form = new AccountForm();

        List<EmailDto> emailList = new ArrayList<EmailDto>();
        emailList.add(new EmailDto("a@example.com"));
        emailList.add(new EmailDto("b@example.com"));
        emailList.add(new EmailDto("c@example.com"));
        form.setEmails(emailList);

        Account account = this.beanMapper.collectionMap(form);

        assertThat(account.getEmails(), is(emailList));

    }

    @Test
    public void testBNMP0106002() {

        AccountForm form = new AccountForm();

        List<EmailDto> sourceEmailList = new ArrayList<EmailDto>();
        sourceEmailList.add(new EmailDto("a@example.com"));
        sourceEmailList.add(new EmailDto("b@example.com"));
        sourceEmailList.add(new EmailDto("c@example.com"));
        form.setEmails(sourceEmailList);

        Account account = new Account();

        List<EmailDto> targetEmailList = new ArrayList<EmailDto>();
        targetEmailList.add(new EmailDto("d@example.com"));
        targetEmailList.add(new EmailDto("e@example.com"));
        targetEmailList.add(new EmailDto("f@example.com"));
        account.setEmails(targetEmailList);

        this.beanMapper.collectionMap(form, account);

        assertThat(account.getEmails(), is(sourceEmailList));

    }

    @Test
    public void testBNMP0106003() {

        AccountForm form = new AccountForm();

        List<EmailDto> sourceEmailList = new ArrayList<EmailDto>();
        sourceEmailList.add(new EmailDto("a@example.com"));
        sourceEmailList.add(new EmailDto("b@example.com"));
        sourceEmailList.add(new EmailDto("c@example.com"));
        form.setEmails(sourceEmailList);

        Account account = new Account();

        List<EmailDto> targetEmailList = new ArrayList<EmailDto>();
        targetEmailList.add(new EmailDto("d@example.com"));
        targetEmailList.add(new EmailDto("e@example.com"));
        targetEmailList.add(new EmailDto("f@example.com"));
        account.setEmails(targetEmailList);

        this.accessorOnlyBeanMapper.collectionMap(form, account);

        assertThat(account.getEmails(), is(sourceEmailList));

    }

    @Test
    public void testBNMP0106004() {

        AccountForm form = new AccountForm();

        List<EmailDto> sourceEmailList = new ArrayList<EmailDto>();
        sourceEmailList.add(new EmailDto("a@example.com"));
        sourceEmailList.add(new EmailDto("b@example.com"));
        sourceEmailList.add(new EmailDto("c@example.com"));
        form.setEmails(sourceEmailList);

        Account account = new Account();

        List<EmailDto> targetEmailList = new ArrayList<EmailDto>();
        targetEmailList.add(new EmailDto("d@example.com"));
        targetEmailList.add(new EmailDto("e@example.com"));
        targetEmailList.add(new EmailDto("f@example.com"));
        account.setEmails(targetEmailList);

        this.setterPreferredBeanMapper.collectionMap(form, account);

        assertThat(account.getEmails(), is(sourceEmailList));

    }

    @Test
    public void testBNMP0106005() {

        AccountForm form = new AccountForm();

        List<EmailDto> sourceEmailList = new ArrayList<EmailDto>();
        sourceEmailList.add(new EmailDto("a@example.com"));
        sourceEmailList.add(new EmailDto("b@example.com"));
        sourceEmailList.add(new EmailDto("c@example.com"));
        form.setEmails(sourceEmailList);
        List<EmailDto> assertSourceEmailList = new ArrayList<EmailDto>(sourceEmailList);

        Account account = new Account();

        List<EmailDto> targetEmailList = new ArrayList<EmailDto>();
        targetEmailList.add(new EmailDto("d@example.com"));
        targetEmailList.add(new EmailDto("e@example.com"));
        targetEmailList.add(new EmailDto("f@example.com"));
        account.setEmails(targetEmailList);
        List<EmailDto> assertTargetEmailList = new ArrayList<EmailDto>(targetEmailList);

        this.adderPreferredBeanMapper.collectionMap(form, account);

        assertTargetEmailList.addAll(assertSourceEmailList);

        assertThat(account.getEmails(), is(assertTargetEmailList));

    }

    @Test
    public void testBNMP0106006() {

        AccountForm form = new AccountForm();

        List<EmailDto> sourceEmailList = new ArrayList<EmailDto>();
        sourceEmailList.add(new EmailDto("a@example.com"));
        sourceEmailList.add(new EmailDto("b@example.com"));
        sourceEmailList.add(new EmailDto("c@example.com"));
        form.setEmails(sourceEmailList);

        Account account = new Account();

        List<EmailDto> targetEmailList = new ArrayList<EmailDto>();
        targetEmailList.add(new EmailDto("d@example.com"));
        targetEmailList.add(new EmailDto("e@example.com"));
        targetEmailList.add(new EmailDto("f@example.com"));
        account.setEmails(targetEmailList);

        this.targetImmutableBeanMapper.collectionMap(form, account);

        assertThat(account.getEmails(), is(sourceEmailList));

    }

    @Test
    public void testBNMP0107001() {

        Map<String, String> map = new HashMap<>();
        map.put("id", "1");
        map.put("mapName", "SourceName");

        Target target = this.beanMapper.mapToBeanMap(map);

        assertThat(target.getId(), is(1));
        assertThat(target.getName(), is("SourceName"));
    }

    @Test
    public void testBNMP0108001() {

        Source source1 = new Source();

        source1.setId(1);
        source1.setName("SourceName");

        MultipleMappingSource source2 = new MultipleMappingSource();

        source2.setId(2);
        source2.setTitle("SourceTitle");

        MultipleMappingTarget target = this.beanMapper.multipleMap(source1,
                source2);

        assertThat(target.getId(), is(1));
        assertThat(target.getName(), is("SourceName"));
        assertThat(target.getTitle(), is("SourceTitle"));
    }

    @Test
    public void testBNMP0109001() {

        SourceInterfaceImpl source = new SourceInterfaceImpl();

        source.setId(1);
        source.setName("SourceName");

        Target target = this.beanMapper.interfaceMap(source);

        assertThat(target.getId(), is(1));
        assertThat(target.getName(), nullValue());
    }

    @Test
    public void testBNMP0109002() {

        SourceInterfaceImpl source1 = new SourceInterfaceImpl();

        source1.setId(1);
        source1.setName("SourceName");

        MultipleMappingTarget target1 = this.beanMapper.multipleInterfaceMap(
                source1);

        assertThat(target1.getId(), is(1));
        assertThat(target1.getName(), is("SourceName"));

        MultipleSourceInterfaceImpl source2 = new MultipleSourceInterfaceImpl();

        source2.setId(2);
        source2.setTitle("SourceTitle");

        MultipleMappingTarget target2 = this.beanMapper.multipleInterfaceMap(
                source2);

        assertThat(target2.getId(), is(2));
        assertThat(target2.getTitle(), is("SourceTitle"));
    }

    @Test
    public void testBNMP0110001() {

        Source source = new Source();

        source.setId(1);
        source.setName("SourceName");

        Target target = new Target();

        target.setId(2);
        target.setName("TargetName");

        this.beanMapper.exceptingMap(source, target);

        assertThat(target.getId(), is(1));
        assertThat(target.getName(), is("TargetName"));
    }

    @Test
    public void testBNMP0201001() {

        CustomMappingSource source = new CustomMappingSource();

        source.setName("SourceName");
        source.setTitle("SourceTitle");

        CustomMappingTarget target = this.customBeanMapper.customMap(source);

        assertThat(target.getName(), is("SOURCENAME"));
        assertThat(target.getTitle(), is("SOURCETITLE"));
    }

    @Test
    public void testBNMP0201002() {

        CustomMappingSource source = new CustomMappingSource();

        source.setName("SourceName");
        source.setTitle("SourceTitle");

        CustomMappingTarget target = this.beanMapper.customMap(source);

        assertThat(target.getName(), is("SOURCENAME"));
        assertThat(target.getTitle(), is("SourceTitle"));
    }

    @Test
    public void testBNMP0201003() {

        CustomMappingSource source = new CustomMappingSource();

        source.setName("SourceName");
        source.setTitle("SourceTitle");

        CustomMappingTarget target = this.beanMapper.separateClassCustomMap(
                source);

        assertThat(target.getName(), is("SOURCENAME"));
        assertThat(target.getTitle(), is("SourceTitle"));
    }

    @Test
    public void testBNMP0202001() {

        Source source = new Source();

        source.setId(1);
        source.setName(null);

        Target target = this.beanMapper.nullDefaultValueMap(source);

        assertThat(target.getId(), is(1));
        assertThat(target.getName(), is("DefaultName"));
    }

    @Test
    public void testBNMP0202002() {

        Source source = new Source();

        source.setId(1);
        source.setName(null);

        Target target = new Target();

        target.setId(2);
        target.setName("TargetName");

        this.beanMapper.nullIgnoreMap(source, target);

        assertThat(target.getId(), is(1));
        assertThat(target.getName(), is("TargetName"));
    }

    @Test
    public void testBNMP0203001() {

        // 通常の文字列がマッピングできることの確認
        CustomMappingSource source = new CustomMappingSource();

        source.setName("sourceName");
        source.setTitle("sourceTitle");

        CustomMappingTarget target = new CustomMappingTarget();

        target.setName("TargetName");
        target.setTitle("TargetTitle");

        this.conditionalBeanMapper.conditionalMap(source, target);

        assertThat(target.getName(), is("sourceName"));
        assertThat(target.getTitle(), is("sourceTitle"));

        // 空文字すべてがnullにマッピングされることの確認
        CustomMappingSource emptySource = new CustomMappingSource();

        source.setName("");
        source.setTitle("");

        CustomMappingTarget emptyTarget = new CustomMappingTarget();

        target.setName("TargetName");
        target.setTitle("TargetTitle");

        this.conditionalBeanMapper.conditionalMap(emptySource, emptyTarget);

        assertThat(emptyTarget.getName(), nullValue());
        assertThat(emptyTarget.getTitle(), nullValue());
    }

    @Test
    public void testBNMP0203002() {

        CustomMappingSource source = new CustomMappingSource();

        source.setName("");
        source.setTitle("");

        CustomMappingTarget target = new CustomMappingTarget();

        target.setName("TargetName");
        target.setTitle("TargetTitle");

        this.beanMapper.conditionalMap(source, target);

        assertThat(target.getName(), nullValue());
        assertThat(target.getTitle(), is(""));
    }

    @Test
    public void testBNMP0204001() {

        FormatSource source = new FormatSource();

        source.setNumber(new BigDecimal("123456"));
        source.setDate("2022-10-10 11:11:11");

        FormatTarget target = this.beanMapper.formattedMap(source);

        assertThat(target.getNumber(), is("123,456"));
        assertThat(target.getDate(), is(LocalDateTime.of(2022, 10, 10, 11, 11,
                11)));
    }

    @Test
    public void testBNMP0301001() {

        Logger logger = (Logger) LoggerFactory.getLogger(
                DefaultConstructorTarget.class);

        logger.addAppender(this.mockAppender);

        Source source = new Source();

        source.setId(1);
        source.setName("SourceName");

        DefaultConstructorTarget target = this.beanMapper.defaultConstructorMap(
                source);

        assertThat(target.getId(), is(1));
        assertThat(target.getName(), is("SourceName"));

        verify(this.mockAppender, times(1)).doAppend(this.captorLoggingEvent
                .capture());
        String message = this.captorLoggingEvent.getValue().getMessage();
        assertThat(message, is("Default annotation constructor was called !!"));

    }

    @Test
    public void testBNMP0301002() {

        Logger logger = (Logger) LoggerFactory.getLogger(
                PublicConstructorTarget.class);

        logger.addAppender(this.mockAppender);

        Source source = new Source();

        source.setId(1);
        source.setName("SourceName");

        PublicConstructorTarget target = this.beanMapper.publicConstructorMap(
                source);

        assertThat(target.getId(), is(1));
        assertThat(target.getName(), is("SourceName"));

        verify(this.mockAppender, times(1)).doAppend(this.captorLoggingEvent
                .capture());
        String message = this.captorLoggingEvent.getValue().getMessage();
        assertThat(message, is("Only one public constructor was called !!"));

    }

    @Test
    public void testBNMP0301003() {

        Logger logger = (Logger) LoggerFactory.getLogger(
                NoArgumentConstructorTarget.class);

        logger.addAppender(this.mockAppender);

        Source source = new Source();

        source.setId(1);
        source.setName("SourceName");

        NoArgumentConstructorTarget target = this.beanMapper
                .noArgumentConstructorMap(source);

        assertThat(target.getId(), is(1));
        assertThat(target.getName(), is("SourceName"));

        verify(this.mockAppender, times(1)).doAppend(this.captorLoggingEvent
                .capture());
        String message = this.captorLoggingEvent.getValue().getMessage();
        assertThat(message, is("No argument constructor was called !!"));

    }
}
