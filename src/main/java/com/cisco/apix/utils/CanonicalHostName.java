/**
 *
 */
package com.cisco.apix.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class CanonicalHostName {

    private static final Logger logger = LoggerFactory.getLogger(CanonicalHostName.class);

    public String getCanonicalHostName() {
        String canonicalHostName = null;
        try {
            InetAddress iAddress = InetAddress.getLocalHost();
            canonicalHostName = iAddress.getCanonicalHostName();
        } catch (UnknownHostException uhe) {
            logger.error("Unable to get host name.. defaulting host name");
        } finally {
            if (canonicalHostName == null) {
                canonicalHostName = "DEFAULT_CANONICAL_HOST";
            }
        }
        return canonicalHostName;
    }

}
