/*
 * Copyright 2014-2018 NTT Corporation.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package jp.co.ntt.fw.spring.functionaltest.infra.spring.data;

import java.util.Calendar;

import javax.inject.Inject;

import org.joda.time.DateTime;
import org.springframework.data.auditing.DateTimeProvider;
import org.springframework.stereotype.Component;
import org.terasoluna.gfw.common.date.jodatime.JodaTimeDateFactory;

@Component
public class AuditDateTimeProvider implements DateTimeProvider {

    @Inject
    JodaTimeDateFactory dateFactory;

    @Override
    public Calendar getNow() {
        DateTime currentDateTime = dateFactory.newDateTime();
        return currentDateTime.toGregorianCalendar();
    }

}
