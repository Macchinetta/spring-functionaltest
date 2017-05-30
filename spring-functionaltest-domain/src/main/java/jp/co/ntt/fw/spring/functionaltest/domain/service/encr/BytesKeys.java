/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.domain.service.encr;

public class BytesKeys {
    private final byte[] key1;

    private final byte[] key2;

    public BytesKeys(byte[] key1, byte[] key2) {
        this.key1 = key1;
        this.key2 = key2;
    }

    public byte[] getKey1() {
        return key1;
    }

    public byte[] getKey2() {
        return key2;
    }
}
