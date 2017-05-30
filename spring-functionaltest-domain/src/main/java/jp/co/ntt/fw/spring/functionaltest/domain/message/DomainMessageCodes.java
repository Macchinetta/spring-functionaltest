/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.domain.message;

/**
 * Message codes of domain layer message.
 * @author DomainMessageCodesGenerator
 */
public class DomainMessageCodes {

    private DomainMessageCodes() {
        // NOP
    }

    /** e.sf.mm.5001=Specified member not found. member id : {0} */
    public static final String E_SF_MM_5001 = "e.sf.mm.5001";

    /** e.sf.mm.8001=Cannot use specified member id member id : {0} */
    public static final String E_SF_MM_8001 = "e.sf.mm.8001";
}
