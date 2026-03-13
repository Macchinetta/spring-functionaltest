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
package jp.co.ntt.fw.spring.functionaltest.app.cmmn.process;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.AbstractGenericHttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.lang.Nullable;

/**
 * Custom HTTP message converter for CSV format.
 */
public class CustomHttpMessageConverter extends AbstractGenericHttpMessageConverter<Object> {
    private static final Logger logger = LoggerFactory.getLogger(CustomHttpMessageConverter.class);

    public CustomHttpMessageConverter() {
        super(new MediaType("text", "csv"), new MediaType("application", "csv"));
    }

    @Override
    protected Object readInternal(Class<? extends Object> clazz, HttpInputMessage inputMessage)
            throws IOException, HttpMessageNotReadableException {
        List<String> lines;
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputMessage.getBody()))) {
            lines = reader.lines().toList();
        }

        if (lines.size() < 2)
            throw new HttpMessageNotReadableException("No data rows", inputMessage);

        String[] headers = lines.get(0).split(",");
        return mapping(headers, lines.get(1).split(","), clazz);
    }

    @Override
    public Object read(Type type, @Nullable Class<?> contextClass, HttpInputMessage inputMessage)
            throws IOException, HttpMessageNotReadableException {
        List<String> lines;

        logger.info("CustomHttpMessageConverter.read() called {}", type.getTypeName());

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputMessage.getBody()))) {
            lines = reader.lines().toList();
        }

        if (lines.size() < 2)
            throw new HttpMessageNotReadableException("No data rows", inputMessage);

        String[] headers = lines.get(0).split(",");
        List<String> rows = lines.subList(1, lines.size());

        if (type instanceof ParameterizedType ptype && ptype.getRawType() == List.class) {
            Class<?> ele = (Class<?>) ptype.getActualTypeArguments()[0];
            List<Object> list = new ArrayList<>(rows.size());
            for (String row : rows) {
                list.add(mapping(headers, row.split(","), ele));
            }
            return list;
        }
        Class<?> clazz = (type instanceof Class<?> c) ? c : Object.class;
        return mapping(headers, rows.get(0).split(","), clazz);
    }

    @Override
    protected void writeInternal(Object t, @Nullable Type type, HttpOutputMessage outputMessage)
            throws IOException, HttpMessageNotWritableException {
        throw new UnsupportedOperationException("Unimplemented method 'writeInternal'");
    }

    private Object mapping(String[] headers, String[] values, Class<?> targetClass) {
        Object bean;
        try {
            bean = targetClass.getDeclaredConstructor().newInstance();
            BeanWrapper bw = new BeanWrapperImpl(bean);
            for (int i = 0; i < headers.length; i++) {
                String name = headers[i];
                if (bw.isWritableProperty(name)) {
                    bw.setPropertyValue(headers[i], values[i]);
                }
            }

            logger.info("CustomHttpMessageConverter.mapping() called {}", bean.toString());

            return bean;
        } catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
                | NoSuchMethodException | SecurityException e) {
            throw new IllegalArgumentException(e);
        }
    }
}