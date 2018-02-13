/*
 * Copyright 2014-2017 NTT Corporation.
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
 *
 */
package jp.co.ntt.fw.spring.functionaltest.app.prmn;

import java.nio.charset.StandardCharsets;

import org.springframework.security.crypto.codec.Hex;
import org.springframework.util.StringValueResolver;

public class EncryptedValueResolver implements StringValueResolver { // (1)

    private final StringValueResolver valueResolver;

    EncryptedValueResolver(StringValueResolver stringValueResolver) { // (2)
        this.valueResolver = stringValueResolver;
    }

    @Override
    public String resolveStringValue(String strVal) { // (3)

        // Values obtained from the property file to the naming
        // as seen with the encryption target
        String decrpyted = strVal;
        String value = valueResolver.resolveStringValue(strVal); // (4)

        // Target messages only, implement coding
        if (value.startsWith("Encrypted:")) { // (5)
            value = value.substring(10); // (6)
            decrpyted = new String(Hex.decode(value), StandardCharsets.UTF_8);
        }
        return decrpyted;
    }
}
