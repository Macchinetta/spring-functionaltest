package jp.co.ntt.fw.spring.functionaltest.app.stpr;

import org.terasoluna.gfw.common.codepoints.CodePoints;
import org.terasoluna.gfw.common.codepoints.catalog.JIS_X_0208_Hiragana;
import org.terasoluna.gfw.common.codepoints.catalog.JIS_X_0208_Katakana;

public class FullwidthHiraganaKatakana extends CodePoints {

    private static final long serialVersionUID = 1L;

    public FullwidthHiraganaKatakana() {
        super(new JIS_X_0208_Hiragana().union(new JIS_X_0208_Katakana()));
    }
}
