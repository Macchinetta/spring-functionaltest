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
package jp.co.ntt.fw.spring.functionaltest.domain.service.encr;

import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;

public interface EncryptionDataService {

    String encryptText(String rawText);

    String decryptText(String encryptedText);

    byte[] encryptBytes(byte[] rawBytes);

    byte[] decryptBytes(byte[] encryptedBytes);

    byte[] generateBytesKey(int keyLength);

    String generateStringKey();

    BytesKeys generateSameBytesKey(int keyLength);

    KeyPair generateKeyPairByJCA();

    byte[] encryptByJCAWithPublicKeyOfOpenSSL(String rawText);

    String decryptByJCAWithPrivateKeyOfOpenSSL(byte[] encryptedBytes);

    byte[] encryptByPublicKey(String rawText, PublicKey publicKey);

    String decryptByPrivateKey(byte[] encryptedBytes, PrivateKey privateKey);

    String openSSLDecrypt(byte[] encryptedBytes);

    byte[] encryptAndSaveByPublicKey(String rawText, PublicKey publicKey);

    byte[] openSSLEncrypt(String rawText);

    String encryptTextByAesWithGcm(String rawText);

    String decryptTextByAesWithGcm(String encryptedText);

    String encryptBytesByAesWithGcm(String rawText);

    String decryptBytesByAesWithGcm(String encryptedText);

    String encryptByHybrid(String text, PublicKey key);

    String decryptByHybrid(String text, PrivateKey key);
}
