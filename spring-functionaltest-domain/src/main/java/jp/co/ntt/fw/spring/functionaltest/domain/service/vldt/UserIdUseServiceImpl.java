/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.domain.service.vldt;

import org.springframework.stereotype.Service;

@Service
public class UserIdUseServiceImpl implements UserIdUseService {

    @Override
    public String convertUserId(String userId) {
        String convertUserId = "afterNull".equals(userId) ? null : userId;
        return convertUserId;
    }

}
