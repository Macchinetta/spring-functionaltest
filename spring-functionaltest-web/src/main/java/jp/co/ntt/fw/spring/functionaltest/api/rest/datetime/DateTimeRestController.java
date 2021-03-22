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
package jp.co.ntt.fw.spring.functionaltest.api.rest.datetime;

import java.time.ZoneId;
import java.time.ZonedDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("datetime")
@RestController
public class DateTimeRestController {

    /**
     * 指定した日時をタイムゾーン「America/Los_Angeles」のZonedDateTimeにして、返却する。
     * <ul>
     * <li>特定のMemberリソースを取得</li>
     * </ul>
     * @param dateTimeResource
     * @return
     */
    @RequestMapping(value = "getAmericaDateTime", method = {
            RequestMethod.POST })
    @ResponseStatus(HttpStatus.OK)
    public DateTimeResource getAmericaDateTime(
            @RequestBody DateTimeResource dateTimeResource) {

        java.time.LocalDateTime dateTime = java.time.LocalDateTime.of(
                dateTimeResource.getDate(), dateTimeResource.getTime());
        ZonedDateTime zoneDt = ZonedDateTime.of(dateTime, ZoneId.of(
                "America/Los_Angeles"));

        dateTimeResource.setDateTime(zoneDt);

        return dateTimeResource;
    }

}
