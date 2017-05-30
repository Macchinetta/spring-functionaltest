/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.app.fldw;

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
        boolean isIE = useragent.indexOf("msie") != -1 && !isOpera
                && (useragent.indexOf("webtv") == -1);
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
