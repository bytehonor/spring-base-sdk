package com.bytehonor.sdk.define.spring.lang;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author lijianqiang
 *
 */
public abstract class SafeRunner implements Runnable {

    private static final Logger LOG = LoggerFactory.getLogger(SafeRunner.class);

    @Override
    public final void run() {
        try {
            runInSafe();
        } catch (Exception e) {
            LOG.error("SafeTask error", e);
        }
    }

    public abstract void runInSafe();
}