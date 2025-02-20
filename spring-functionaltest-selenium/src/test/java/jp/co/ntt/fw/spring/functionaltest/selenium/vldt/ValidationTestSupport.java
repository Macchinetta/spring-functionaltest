package jp.co.ntt.fw.spring.functionaltest.selenium.vldt;

import java.util.HashMap;
import java.util.Map;

import jp.co.ntt.fw.spring.functionaltest.selenium.BrowserLocale;
import jp.co.ntt.fw.spring.functionaltest.selenium.FunctionTestSupport;

public abstract class ValidationTestSupport extends FunctionTestSupport {

    protected static final String ID_VALIDATE = "validate";

    protected static final String ID_ERRORS = ".errors";

    private Map<BrowserLocale, String> localeDateFormatMap;

    {
        localeDateFormatMap = new HashMap<>();
        localeDateFormatMap.put(BrowserLocale.JAPAN, "yyyy/MM/dd");
        localeDateFormatMap.put(BrowserLocale.ENGLISH_US, "MM/dd/yy");
    }

    protected String getLocalDateFormat(BrowserLocale locale) {
        return this.localeDateFormatMap.get(locale);
    }

    protected void setUpLocalDriver() {

    }

}
