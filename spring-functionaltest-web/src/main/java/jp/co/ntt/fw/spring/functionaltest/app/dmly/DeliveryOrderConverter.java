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
package jp.co.ntt.fw.spring.functionaltest.app.dmly;

import java.util.Date;

import org.dozer.DozerConverter;
import org.joda.time.LocalDateTime;

public class DeliveryOrderConverter extends
                                    DozerConverter<LocalDateTime, Date> {
    public DeliveryOrderConverter() {
        super(LocalDateTime.class, Date.class);
    }

    @Override
    public Date convertTo(LocalDateTime source, Date destination) {
        Date dest = null;
        if (source != null) {
            dest = source.toDate();
        }
        return dest;
    }

    @Override
    public LocalDateTime convertFrom(Date source, LocalDateTime destination) {
        LocalDateTime dest = null;
        if (source != null) {
            dest = LocalDateTime.fromDateFields(source);
        }
        return dest;
    }
}
