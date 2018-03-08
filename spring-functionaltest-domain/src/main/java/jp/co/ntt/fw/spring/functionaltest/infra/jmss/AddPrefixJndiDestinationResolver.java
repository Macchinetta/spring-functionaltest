/*
 * Copyright 2014-2018 NTT Corporation.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
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
