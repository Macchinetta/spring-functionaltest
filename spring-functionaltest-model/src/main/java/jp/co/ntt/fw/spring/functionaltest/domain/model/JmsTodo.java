/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.domain.model;

import java.io.Serializable;

import javax.validation.constraints.Null;

import org.joda.time.DateTime;

public class JmsTodo implements Serializable {

    private static final long serialVersionUID = 1L;

    private String jmsTodoId;

    @Null
    private String description;

    private DateTime datetime;

    private boolean finished;

    public String getJmsTodoId() {
        return jmsTodoId;
    }

    public void setJmsTodoId(String jmsTodoId) {
        this.jmsTodoId = jmsTodoId;
    }

    public DateTime getDatetime() {
        return datetime;
    }

    public void setDatetime(DateTime datetime) {
        this.datetime = datetime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isFinished() {
        return finished;
    }

    public void setFinished(boolean finished) {
        this.finished = finished;
    }

    @Override
    public String toString() {
        return "JmsTodo [jmsTodoId=" + jmsTodoId + ", description="
                + description + ", datetime=" + datetime + ", finished="
                + finished + "]";
    }
}
