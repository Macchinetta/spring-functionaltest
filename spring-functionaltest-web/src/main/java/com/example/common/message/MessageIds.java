/*
 * Copyright(c) 2025 NTT Corporation.
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
package com.example.common.message;

/**
 * Message Id
 */
public class MessageIds {
    /** label.aa.bb.year=Year */
    public static final String LABEL_AA_BB_YEAR = "label.aa.bb.year";
    /** label.aa.bb.month=Month */
    public static final String LABEL_AA_BB_MONTH = "label.aa.bb.month";
    /** label.aa.bb.day=Day */
    public static final String LABEL_AA_BB_DAY = "label.aa.bb.day";
    /** i.ex.an.0001={0} upload completed. */
    public static final String I_EX_AN_0001 = "i.ex.an.0001";
    /** w.ex.an.2001=The recommended change interval has passed password. Please change your password. */
    public static final String W_EX_AN_2001 = "w.ex.an.2001";
    /** e.ex.an.8001=Cannot upload, Because the file size must be less than {0}MB. */
    public static final String E_EX_AN_8001 = "e.ex.an.8001";
    /** e.ex.an.8002=Cannot upload, Because the file size must be less than {0,number,#}MB. */
    public static final String E_EX_AN_8002 = "e.ex.an.8002";
    /** e.ex.an.9001=There are inconsistencies in the data. */
    public static final String E_EX_AN_9001 = "e.ex.an.9001";
    /** i.ex.od.0001=Please confirm order content. <font style="color: red; font-size: 16px;">If this orders submitted, cannot cancel.</font> */
    public static final String I_EX_OD_0001 = "i.ex.od.0001";
    /** i.ex.od.0002=Change panelElement. */
    public static final String I_EX_OD_0002 = "i.ex.od.0002";
    /** label.sf.cmmn.systemName=Spring Functional Test MSMN */
    public static final String LABEL_SF_CMMN_SYSTEMNAME = "label.sf.cmmn.systemName";
    /** label.sf.cmmn.defaultUserName=\u30B2\u30B9\u30C8 */
    public static final String LABEL_SF_CMMN_DEFAULTUSERNAME = "label.sf.cmmn.defaultUserName";

    private MessageIds() {}
}
