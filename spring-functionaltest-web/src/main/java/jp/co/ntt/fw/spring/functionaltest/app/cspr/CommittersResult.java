/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.app.cspr;

import java.io.Serializable;
import java.util.List;

import jp.co.ntt.fw.spring.functionaltest.domain.model.Committer;

public class CommittersResult implements Serializable {

    private static final long serialVersionUID = 1L;

    private List<Committer> committers;

    public List<Committer> getCommitters() {
        return committers;
    }

    public void setCommitters(List<Committer> committers) {
        this.committers = committers;
    }

}
