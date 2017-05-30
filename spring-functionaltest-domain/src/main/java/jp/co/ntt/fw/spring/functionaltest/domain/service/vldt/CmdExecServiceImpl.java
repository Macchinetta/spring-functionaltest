/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.domain.service.vldt;

import org.springframework.stereotype.Service;

@Service
public class CmdExecServiceImpl implements CmdExecService {

    @Override
    public String exec(String cmdStr, String arg) {
        return "success";
    }

}
