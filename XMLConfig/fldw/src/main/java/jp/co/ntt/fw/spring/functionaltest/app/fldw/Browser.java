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
package jp.co.ntt.fw.spring.functionaltest.app.fldw;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import org.springframework.util.StringUtils;

/**
 * @see "http://0xcc.net/pub/webdb/bk-05.html"
 */
public enum Browser {
    FIREFOX {
        @Override
        String appendAndEncodeFilename(String filename) {
            try {
                return "filename*=''" + URLEncoder.encode(filename, "UTF-8");
            } catch (UnsupportedEncodingException e) {
                throw new IllegalStateException(e);
            }
        }
    },
    EDGE, // デフォルト実装使用
    CHROME; // デフォルト実装使用

    String appendAndEncodeFilename(String filename) {
        try {
            return "filename=" + URLEncoder.encode(filename, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new IllegalStateException(e);
        }
    }

    public static Browser detect(String userAgent) {

        if (!StringUtils.hasLength(userAgent)) {
            throw new IllegalArgumentException("'User-Agent' is empty");
        } else if (userAgent.contains("Firefox")) {
            return FIREFOX;
        } else if (userAgent.contains("Edge")) {
            return EDGE;
        } else if (userAgent.contains("Chrome")) {
            // HeadlessChromeも含む
            // Chrome以外のブラウザもChromeを含むため、Chromeの判定は一番最後に実施する
            return CHROME;
        }

        throw new IllegalArgumentException("The given 'User-Agent' is unknown : " + userAgent);
    }
}
