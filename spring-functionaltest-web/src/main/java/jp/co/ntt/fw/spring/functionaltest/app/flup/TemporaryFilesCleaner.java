/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.app.flup;

import java.io.IOException;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TemporaryFilesCleaner {

    private static final Logger logger = LoggerFactory
            .getLogger(TemporaryFilesCleaner.class);

    private boolean deletion;

    @Inject
    TemporaryFilesHelper temporaryFilesHelper;

    public void turnOnDeletion() {
        this.deletion = true;
    }

    public void turnOffDeletion() {
        this.deletion = false;
    }

    private boolean isOnDeletion() {
        return deletion;
    }

    public void cleanup() {
        if (!isOnDeletion()) {
            return;
        }
        turnOffDeletion();
        if (!temporaryFilesHelper.existsTemporaryDirectory()) {
            logger.debug("temporaryDirectory does not exist.");
            return;
        }
        try {
            temporaryFilesHelper.clearTemporarydirectory();
        } catch (IOException e) {
            logger.error("I/O error occurred in the Temporary Files Cleaner.",
                    e);
        }
    }

}
