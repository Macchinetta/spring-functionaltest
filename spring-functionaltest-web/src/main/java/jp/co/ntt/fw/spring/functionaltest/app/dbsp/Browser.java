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
package jp.co.ntt.fw.spring.functionaltest.app.dbsp;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * @see "http://0xcc.net/pub/webdb/bk-05.html"
 */
public enum Browser {
    IE {
        @Override
        public String appendAndEncodeFilename(String filename) {
            // SJISに変換
            try {
                // 古いIE
                // return "filename=" + URLEncoder.encode(filename, "SJIS");
                // 最近のIEはUTF-8
                return "filename=" + URLEncoder.encode(filename, "UTF-8");
            } catch (UnsupportedEncodingException e) {
                // 発生しない
                throw new IllegalStateException(e);
            }
        }
    },
    CHROME {
        @Override
        public String appendAndEncodeFilename(String filename) {
            // UTF-8に変換
            try {
                return "filename=" + URLEncoder.encode(filename, "UTF-8");
            } catch (UnsupportedEncodingException e) {
                // 発生しない
                throw new IllegalStateException(e);
            }
        }
    },
    FIREFOX {
        @Override
        public String appendAndEncodeFilename(String filename) {
            // UTF-8に変換
            try {
                return "filename*=''" + URLEncoder.encode(filename, "UTF-8");
            } catch (UnsupportedEncodingException e) {
                // 発生しない
                throw new IllegalStateException(e);
            }
        }
    };

    abstract public String appendAndEncodeFilename(String filename);

    public static Browser detect(String userAgent) {
        String useragent = userAgent.toLowerCase();
        boolean isChrome = useragent.indexOf(" chrome/") != -1;
        // boolean isSafari = !isChrome && useragent.indexOf("safari") != -1;
        boolean isOpera = useragent.indexOf("opera") != -1;
        boolean isIE = useragent.indexOf("msie") != -1 && !isOpera && (useragent
                .indexOf("webtv") == -1);
        boolean isFirefox = useragent.indexOf(" firefox/") != -1;

        if (isChrome) {
            return CHROME;
        } else if (isIE) {
            return IE;
        } else if (isFirefox) {
            return FIREFOX;
        }

        throw new IllegalArgumentException("The given 'User-Agent' is unknown : "
                + userAgent);
    }
}
