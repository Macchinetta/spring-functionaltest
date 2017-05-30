/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.infra.jmss;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Session;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.support.destination.JndiDestinationResolver;

public class AddPrefixJndiDestinationResolver extends JndiDestinationResolver {

    @Value("${jndi.prefix}")
    private String jndiPrefix;

    private static final String QUEUE_NAME_PREFIX = "jms/queue/";

    private static final String TOPIC_NAME_PREFIX = "jms/topic/";

    @Override
    public Destination resolveDestinationName(Session session,
            String destinationName, boolean pubSubDomain) throws JMSException {
        if (destinationName != null) {
            if (pubSubDomain) {
                destinationName = TOPIC_NAME_PREFIX + destinationName;
            } else {
                destinationName = QUEUE_NAME_PREFIX + destinationName;
            }
            if (!isResourceRef()) {
                destinationName = jndiPrefix + destinationName;
            }
        }

        return super.resolveDestinationName(session, destinationName,
                pubSubDomain);
    }
}
