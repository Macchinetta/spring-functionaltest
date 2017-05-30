/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.domain.service.encr;

import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;

public interface EncryptionDataService {

    String encryptText(String rawText);

    String decryptText(String encryptedText);

    String encryptQueryableText(String rawText);

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
