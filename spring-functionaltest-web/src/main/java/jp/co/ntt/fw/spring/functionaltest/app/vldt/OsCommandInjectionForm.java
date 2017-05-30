/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.app.vldt;

import javax.validation.constraints.Pattern;

public class OsCommandInjectionForm {
    @Pattern(regexp = "batch0\\d\\.sh")
    private String cmdStr;

    @Pattern(regexp = "[\\w=_]+")
    private String arg;

    public String getCmdStr() {
        return cmdStr;
    }

    public void setCmdStr(String cmdStr) {
        this.cmdStr = cmdStr;
    }

    public String getArg() {
        return arg;
    }

    public void setArg(String arg) {
        this.arg = arg;
    }

}
