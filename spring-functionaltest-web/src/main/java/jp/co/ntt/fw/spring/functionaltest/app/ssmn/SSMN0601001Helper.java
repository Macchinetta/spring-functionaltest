/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.app.ssmn;

import org.springframework.stereotype.Component;

@Component
public class SSMN0601001Helper {

    public void delay(long millis) {
        // delay処理
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
