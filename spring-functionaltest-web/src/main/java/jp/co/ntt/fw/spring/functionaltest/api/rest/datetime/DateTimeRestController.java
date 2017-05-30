/*
 * Copyright(c) 2014-2017 NTT Corporation.
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
    @RequestMapping(value = "getAmericaDateTime", method = { RequestMethod.POST })
    @ResponseStatus(HttpStatus.OK)
    public DateTimeResource getAmericaDateTime(
            @RequestBody DateTimeResource dateTimeResource) {

        java.time.LocalDateTime dateTime = java.time.LocalDateTime.of(
                dateTimeResource.getDate(), dateTimeResource.getTime());
        ZonedDateTime zoneDt = ZonedDateTime.of(dateTime, ZoneId
                .of("America/Los_Angeles"));

        dateTimeResource.setDateTime(zoneDt);

        return dateTimeResource;
    }

}
