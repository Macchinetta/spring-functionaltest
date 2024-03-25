package jp.co.ntt.fw.spring.functionaltest.app.stpr;

import org.terasoluna.gfw.common.codepoints.CodePoints;
import org.terasoluna.gfw.common.codepoints.catalog.JIS_X_0201_Katakana;

public class HalfwidthKatakana extends CodePoints {

    private static final long serialVersionUID = 1L;

    public HalfwidthKatakana() {
        super(new JIS_X_0201_Katakana().subtract(
                new CodePoints(0xFF61 /* ｡ */, 0xFF62 /* ｢ */, 0xFF63 /* ｣ */, 0xFF64 /* ､ */, 0xFF65 /* ･ */)));
    }
}
