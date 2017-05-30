/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.app.aply;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class JspFormListForm implements Serializable {

    private static final long serialVersionUID = 1L;

    private List<JspForm> jspFormList;

    JspFormListForm() {
        jspFormList = new ArrayList<JspForm>();
        for (int i = 0; i < 3; i++) {
            jspFormList.add(new JspForm());
        }
    }

    public List<JspForm> getJspFormList() {
        return jspFormList;
    }

    public void setJspFormList(List<JspForm> jspFormList) {
        this.jspFormList = jspFormList;
    }
}
