package jp.co.ntt.fw.spring.functionaltest.app.stpr;

import org.terasoluna.gfw.common.codepoints.ConsistOf;
import org.terasoluna.gfw.common.codepoints.catalog.JIS_X_0208_Hiragana;
import org.terasoluna.gfw.common.codepoints.catalog.JIS_X_0208_Katakana;

public class ConsistOfTestBean {

    @ConsistOf(JIS_X_0208_Hiragana.class)
    private String name1;

    @ConsistOf({ JIS_X_0208_Hiragana.class, JIS_X_0208_Katakana.class })
    private String name2;

    public ConsistOfTestBean() {
    }

    public ConsistOfTestBean(String name1, String name2) {
        this.name1 = name1;
        this.name2 = name2;
    }

    public String getName1() {
        return this.name1;
    }

    public void setName1(String name1) {
        this.name1 = name1;
    }

    public String getName2() {
        return this.name2;
    }

    public void setName2(String name2) {
        this.name2 = name2;
    }

}
