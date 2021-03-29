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
package jp.co.ntt.fw.spring.functionaltest.app.bnmp;

import org.joda.time.DateTime;

import com.github.dozermapper.core.DozerConverter;

/**
 * JodaDateTimeToJodaDateTimeConverter<br>
 * <p>
 * dozerでJodaTimeのDateTimeクラスをデフォルト設定でマッピングすることができない為、このクラスを用意。
 * </p>
 */
public class JodaDateTimeToJodaDateTimeConverter extends
                                                 DozerConverter<DateTime, DateTime> {

    public JodaDateTimeToJodaDateTimeConverter() {
        super(DateTime.class, DateTime.class);
    }

    @Override
    public DateTime convertTo(DateTime source, DateTime destination) {
        return new DateTime(source);
    }

    @Override
    public DateTime convertFrom(DateTime source, DateTime destination) {
        return new DateTime(source);
    }

}
