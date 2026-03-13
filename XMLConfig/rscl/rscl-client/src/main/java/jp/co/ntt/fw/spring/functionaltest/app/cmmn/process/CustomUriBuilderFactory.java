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

import java.net.URI;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Collection;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.MultiValueMap;
import org.springframework.web.util.DefaultUriBuilderFactory;
import org.springframework.web.util.UriBuilder;
import org.springframework.web.util.UriComponentsBuilder;

/**
 * Custom UriBuilderFactory to convert LocalDate parameters to String in
 * "yyyyMMdd" format.
 */
public class CustomUriBuilderFactory extends DefaultUriBuilderFactory {

    private static final Logger logger = LoggerFactory.getLogger(CustomUriBuilderFactory.class);

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyyMMdd");

    // Default Constructor
    public CustomUriBuilderFactory() {
        super();
    }

    // Constructor with baseUri
    public CustomUriBuilderFactory(String baseUriTemplate) {
        super(baseUriTemplate);
    }

    // Constructor with UriComponentsBuilder
    public CustomUriBuilderFactory(UriComponentsBuilder baseUri) {
        super(baseUri);
    }

    // The following methods must be overridden so that UriBuilder is not used.

    @Override
    public UriBuilder uriString(String uriTemplate) {
        return new CustomUriBuilder(super.uriString(uriTemplate));
    }

    @Override
    public UriBuilder builder() {
        return new CustomUriBuilder(super.builder());
    }

    private class CustomUriBuilder implements UriBuilder {

        private final UriBuilder delegate;

        public CustomUriBuilder(UriBuilder delegate) {
            this.delegate = delegate;
        }

        @Override
        public URI build(Map<String, ?> uriVariables) {
            logger.info("[CustomUriBuilder] before {}", uriVariables.toString());
            Map<String, Object> convertedUriVariables = uriVariables.entrySet().stream()
                    .collect(Collectors.toMap(Map.Entry::getKey, this::convert));
            logger.info("[CustomUriBuilder] after {}", convertedUriVariables.toString());
            return delegate.build(convertedUriVariables);
        }

        @Override
        public URI build(Object... uriVariables) {
            logger.info("[CustomUriBuilder] before {}", Arrays.toString(uriVariables));
            Object[] convertedUriVariables = Arrays.stream(uriVariables)
                    .map(this::convert)
                    .toArray();
            logger.info("[CustomUriBuilder] after {}", Arrays.toString(convertedUriVariables));
            return delegate.build(convertedUriVariables);
        }

        private Object convert(Object value) {
            if (value instanceof LocalDate date) {
                return FORMATTER.format(date);
            }
            return value;
        }

        // Delegate all other methods to the original UriBuilder

        @Override
        public UriBuilder scheme(String scheme) {
            return delegate.scheme(scheme);
        }

        @Override
        public UriBuilder userInfo(String userInfo) {
            return delegate.userInfo(userInfo);
        }

        @Override
        public UriBuilder host(String host) {
            return delegate.host(host);
        }

        @Override
        public UriBuilder port(int port) {
            return delegate.port(port);
        }

        @Override
        public UriBuilder port(String port) {
            return delegate.port(port);
        }

        @Override
        public UriBuilder path(String path) {
            return delegate.path(path);
        }

        @Override
        public UriBuilder replacePath(String path) {
            return delegate.replacePath(path);
        }

        @Override
        public UriBuilder pathSegment(String... pathSegments) throws IllegalArgumentException {
            return delegate.pathSegment(pathSegments);
        }

        @Override
        public UriBuilder query(String query) {
            return delegate.query(query);
        }

        @Override
        public UriBuilder replaceQuery(String query) {
            return delegate.replaceQuery(query);
        }

        @Override
        public UriBuilder queryParam(String name, Object... values) {
            return delegate.queryParam(name, values);
        }

        @Override
        public UriBuilder queryParam(String name, Collection<?> values) {
            return delegate.queryParam(name, values);
        }

        @Override
        public UriBuilder queryParamIfPresent(String name, Optional<?> value) {
            return delegate.queryParamIfPresent(name, value);
        }

        @Override
        public UriBuilder queryParams(MultiValueMap<String, String> params) {
            return delegate.queryParams(params);
        }

        @Override
        public UriBuilder replaceQueryParam(String name, Object... values) {
            return delegate.replaceQueryParam(name, values);
        }

        @Override
        public UriBuilder replaceQueryParam(String name, Collection<?> values) {
            return delegate.replaceQueryParam(name, values);
        }

        @Override
        public UriBuilder replaceQueryParams(MultiValueMap<String, String> params) {
            return delegate.replaceQueryParams(params);
        }

        @Override
        public UriBuilder fragment(String fragment) {
            return delegate.fragment(fragment);
        }

        @Override
        public String toUriString() {
            return delegate.toUriString();
        }
    }

}
