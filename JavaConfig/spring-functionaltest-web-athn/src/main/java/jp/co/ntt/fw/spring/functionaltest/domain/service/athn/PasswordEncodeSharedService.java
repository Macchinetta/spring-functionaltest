/*
 * Copyright(c) 2024 NTT Corporation.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 */
package jp.co.ntt.fw.spring.functionaltest.domain.service.athn;

public interface PasswordEncodeSharedService {

    String passwordEncodeBCrypt(String rawPassword);

    String passwordEncodePbkdf2(String rawPassword);

    String passwordEncodeSCrypt(String rawPassword);

    String passwordEncodeArgon2(String rawPassword);

    String passwordEncodeDelegating(String rawPassword);

    String passwordEncodeCustomPbkdf2(String rawPassword);

    String passwordEncodeMessageDigest(String rawPassword, String userSalt);

    String passwordEncodeMessageDigestDelegating(String rawPassword,
            String userSalt);
}
