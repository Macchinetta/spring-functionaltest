/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.app.dam3;

import org.dozer.DozerConverter;
import org.springframework.security.crypto.codec.Hex;

public class BookFormConverter extends DozerConverter<String, byte[]> {
    public BookFormConverter() {
        super(String.class, byte[].class);
    }

    @Override
    public byte[] convertTo(String source, byte[] destination) {
        byte[] dest = null;
        if (source != null) {
            dest = Hex.decode(source);
        }
        return dest;
    }

    @Override
    public String convertFrom(byte[] source, String destination) {
        String src = null;
        if (source != null) {
            src = new String(Hex.encode(source)).toUpperCase();
        }
        return src;
    }
}
