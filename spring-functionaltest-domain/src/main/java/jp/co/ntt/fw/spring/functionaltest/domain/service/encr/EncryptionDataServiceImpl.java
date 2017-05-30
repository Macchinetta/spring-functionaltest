/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.domain.service.encr;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.security.crypto.codec.Hex;
import org.springframework.security.crypto.codec.Utf8;
import org.springframework.security.crypto.encrypt.BytesEncryptor;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.security.crypto.encrypt.TextEncryptor;
import org.springframework.security.crypto.keygen.BytesKeyGenerator;
import org.springframework.security.crypto.keygen.KeyGenerators;
import org.springframework.security.crypto.keygen.StringKeyGenerator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StreamUtils;
import org.terasoluna.gfw.common.exception.SystemException;

@Transactional
@Service
public class EncryptionDataServiceImpl implements EncryptionDataService {

    @Value("${encr.encryption.secret}")
    String secret;

    @Value("${encr.encryption.salt}")
    String salt;

    @Value("${encr.encryption.publickey}")
    Resource publicKeyFile;

    @Value("${encr.encryption.privatekey}")
    Resource privateKeyFile;

    @Value("${encr.encryption.privatekey4openssl}")
    Resource privateKey4OpensslFile;

    @Override
    public String encryptText(String rawText) {
        TextEncryptor textEncryptor = Encryptors.text(secret, salt);
        return textEncryptor.encrypt(rawText);
    }

    @Override
    public String decryptText(String encryptedText) {
        TextEncryptor textEncryptor = Encryptors.text(secret, salt);
        return textEncryptor.decrypt(encryptedText);
    }

    @Override
    public String encryptQueryableText(String rawText) {
        TextEncryptor queryableTextEncryptor = Encryptors.queryableText(secret,
                salt);
        return queryableTextEncryptor.encrypt(rawText);
    }

    @Override
    public byte[] encryptBytes(byte[] rawBytes) {
        BytesEncryptor bytesEncryptor = Encryptors.standard(secret, salt);
        return bytesEncryptor.encrypt(rawBytes);
    }

    @Override
    public byte[] decryptBytes(byte[] encryptedBytes) {
        BytesEncryptor bytesEncryptor = Encryptors.standard(secret, salt);
        return bytesEncryptor.decrypt(encryptedBytes);
    }

    @Override
    public byte[] generateBytesKey(int keyLength) {
        BytesKeyGenerator generator = KeyGenerators.secureRandom(keyLength);
        return generator.generateKey();
    }

    @Override
    public String generateStringKey() {
        StringKeyGenerator generator = KeyGenerators.string();
        return generator.generateKey();
    }

    @Override
    public BytesKeys generateSameBytesKey(int keyLength) {
        BytesKeyGenerator generator = KeyGenerators.shared(keyLength);

        byte[] key1 = generator.generateKey();
        byte[] key2 = generator.generateKey();

        return new BytesKeys(key1, key2);
    }

    @Override
    public KeyPair generateKeyPairByJCA() {
        try {
            KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");
            generator.initialize(2048);
            return generator.generateKeyPair();
        } catch (NoSuchAlgorithmException e) {
            throw new SystemException("e.sf.encr.9001", "key generation error (NoSuchAlgorithm).", e);
        }
    }

    @Override
    public byte[] encryptByJCAWithPublicKeyOfOpenSSL(String rawText) {
        try (InputStream stream = publicKeyFile.getInputStream()) {
            KeySpec publicKeySpec = new X509EncodedKeySpec(StreamUtils
                    .copyToByteArray(stream));
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            return encryptAndSaveByPublicKey(rawText, keyFactory
                    .generatePublic(publicKeySpec));
        } catch (IOException e) {
            throw new SystemException("e.sf.encr.9007", "input/output error.", e);
        } catch (NoSuchAlgorithmException e) {
            throw new SystemException("e.sf.encr.9002", "encryption error (NoSuchAlgorithm).", e);
        } catch (InvalidKeySpecException e) {
            throw new SystemException("e.sf.encr.9010", "encryption error (InvalidKeySpec).", e);
        }
    }

    @Override
    public String decryptByJCAWithPrivateKeyOfOpenSSL(byte[] encryptedBytes) {
        try (InputStream stream = privateKeyFile.getInputStream()) {
            KeySpec privateKeySpec = new PKCS8EncodedKeySpec(StreamUtils
                    .copyToByteArray(stream));
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            return decryptByPrivateKey(encryptedBytes, keyFactory
                    .generatePrivate(privateKeySpec));
        } catch (IOException e) {
            throw new SystemException("e.sf.encr.9007", "input/output error.", e);
        } catch (NoSuchAlgorithmException e) {
            throw new SystemException("e.sf.encr.9002", "encryption error (NoSuchAlgorithm).", e);
        } catch (InvalidKeySpecException e) {
            throw new SystemException("e.sf.encr.9010", "encryption error (InvalidKeySpec).", e);
        }
    }

    @Override
    public byte[] encryptByPublicKey(String rawText, PublicKey publicKey) {
        try {
            Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
            cipher.init(Cipher.ENCRYPT_MODE, publicKey);
            return cipher.doFinal(rawText.getBytes(StandardCharsets.UTF_8));
        } catch (NoSuchAlgorithmException e) {
            throw new SystemException("e.sf.encr.9002", "encryption error (NoSuchAlgorithm).", e);
        } catch (NoSuchPaddingException e) {
            throw new SystemException("e.sf.encr.9003", "encryption error (NoSuchPadding).", e);
        } catch (InvalidKeyException e) {
            throw new SystemException("e.sf.encr.9004", "encryption error (InvalidKey).", e);
        } catch (IllegalBlockSizeException e) {
            throw new SystemException("e.sf.encr.9005", "encryption error (IllegalBlockSize).", e);
        } catch (BadPaddingException e) {
            throw new SystemException("e.sf.encr.9006", "encryption error (BadPadding).", e);
        }
    }

    @Override
    public String decryptByPrivateKey(byte[] encryptedBytes,
            PrivateKey privateKey) {
        try {
            Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
            cipher.init(Cipher.DECRYPT_MODE, privateKey);
            return new String(cipher.doFinal(encryptedBytes), StandardCharsets.UTF_8);
        } catch (NoSuchAlgorithmException e) {
            throw new SystemException("e.sf.encr.9002", "encryption error (NoSuchAlgorithm).", e);
        } catch (NoSuchPaddingException e) {
            throw new SystemException("e.sf.encr.9003", "encryption error (NoSuchPadding).", e);
        } catch (InvalidKeyException e) {
            throw new SystemException("e.sf.encr.9004", "encryption error (InvalidKey).", e);
        } catch (IllegalBlockSizeException e) {
            throw new SystemException("e.sf.encr.9005", "encryption error (IllegalBlockSize).", e);
        } catch (BadPaddingException e) {
            throw new SystemException("e.sf.encr.9006", "encryption error (BadPadding).", e);
        }
    }

    @Override
    public byte[] encryptAndSaveByPublicKey(String rawText, PublicKey publicKey) {
        try {
            byte[] encryptedBytes = encryptByPublicKey(rawText, publicKey);
            Files.write(Paths.get(System.getProperty("java.io.tmpdir"),
                    "encryptedByJCA.dat"), encryptedBytes);
            return encryptedBytes;
        } catch (IOException e) {
            throw new SystemException("e.sf.encr.9007", "input/output error.", e);
        }
    }

    @Override
    public String openSSLDecrypt(byte[] encryptedBytes) {
        String tmpDir = System.getProperty("java.io.tmpdir");
        Path encryptedFilePath = Paths.get(tmpDir, "encryptedByJCA.dat");
        Path decryptedFilePath = Paths.get(tmpDir, "decryptedByOpenSSL.txt");
        Path privateKeyFilePath = Paths.get(tmpDir, "private.pem");

        try (InputStream stream = privateKey4OpensslFile.getInputStream()) {

            Files.write(privateKeyFilePath, StreamUtils.copyToByteArray(stream));

            Process proc = Runtime.getRuntime().exec(
                    "openssl rsautl -decrypt -inkey " + privateKeyFilePath
                            + " -in " + encryptedFilePath + " -out "
                            + decryptedFilePath);
            proc.waitFor();
            byte[] decryptedBytes = Files.readAllBytes(decryptedFilePath);

            return new String(decryptedBytes, StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new SystemException("e.sf.encr.9007", "input/output error.", e);
        } catch (InterruptedException e) {
            throw new SystemException("e.sf.encr.9009", "interrupted error.", e);
        } finally {
            try {
                Files.deleteIfExists(encryptedFilePath);
                Files.deleteIfExists(decryptedFilePath);
                Files.deleteIfExists(privateKeyFilePath);
            } catch (IOException e) {
            }
        }
    }

    @Override
    public byte[] openSSLEncrypt(String rawText) {
        String tmpDir = System.getProperty("java.io.tmpdir");
        Path rawTextFilePath = Paths.get(tmpDir, "rawText.txt");
        Path encryptedFilePath = Paths.get(tmpDir, "encryptedByOpenSSL.dat");
        Path publicKeyFilePath = Paths.get(tmpDir, "public.der");

        try (InputStream stream = publicKeyFile.getInputStream()) {
            Files.write(publicKeyFilePath, StreamUtils.copyToByteArray(stream));
            Files.write(rawTextFilePath, rawText
                    .getBytes(StandardCharsets.UTF_8));

            Process proc = Runtime.getRuntime().exec(
                    "openssl rsautl -encrypt -keyform DER -pubin -inkey "
                            + publicKeyFilePath + " -in " + rawTextFilePath
                            + " -out " + encryptedFilePath);
            proc.waitFor();
            byte[] encryptedBytes = Files.readAllBytes(encryptedFilePath);

            return encryptedBytes;
        } catch (IOException e) {
            throw new SystemException("e.sf.encr.9007", "input/output error.", e);
        } catch (InterruptedException e) {
            throw new SystemException("e.sf.encr.9009", "interrupted error.", e);
        } finally {
            try {
                Files.deleteIfExists(rawTextFilePath);
                Files.deleteIfExists(encryptedFilePath);
                Files.deleteIfExists(publicKeyFilePath);
            } catch (IOException e) {
            }
        }
    }

    @Override
    public String encryptTextByAesWithGcm(String rawText) {
        TextEncryptor gcmTextEncryptor = Encryptors.delux(secret, salt);
        return gcmTextEncryptor.encrypt(rawText);
    }

    @Override
    public String decryptTextByAesWithGcm(String encryptedText) {
        TextEncryptor gcmTextEncryptor = Encryptors.delux(secret, salt);
        return gcmTextEncryptor.decrypt(encryptedText);
    }

    @Override
    public String encryptBytesByAesWithGcm(String rawText) {
        return new String(Hex.encode(encryptBytesByAesWithGcm(Utf8
                .encode(rawText))));
    }

    @Override
    public String decryptBytesByAesWithGcm(String encryptedText) {
        return Utf8.decode(decryptBytesbyAesWithGcm(Hex.decode(encryptedText)));
    }

    private byte[] encryptBytesByAesWithGcm(byte[] rawBytes) {
        BytesEncryptor gcmBytesEncryptor = Encryptors.stronger(secret, salt);
        return gcmBytesEncryptor.encrypt(rawBytes);
    }

    private byte[] decryptBytesbyAesWithGcm(byte[] encryptedBytes) {
        BytesEncryptor gcmBytesEncryptor = Encryptors.stronger(secret, salt);
        return gcmBytesEncryptor.decrypt(encryptedBytes);
    }

    @Override
    public String encryptByHybrid(String text, PublicKey key) {
        return new String(Hex.encode(encryptByHybrid(Utf8.encode(text), key)));
    }

    // encryptByHybridメソッドのソースコード参考サイトは以下。
    // <https://github.com/dsyer/spring-security-rsa/blob/master/src/main/java/org/springframework/security/rsa/crypto/RsaSecretEncryptor.java>
    private byte[] encryptByHybrid(byte[] text, PublicKey key) {
        byte[] random = KeyGenerators.secureRandom(16).generateKey();
        BytesEncryptor aes = Encryptors.standard(
                new String(Hex.encode(random)), salt);
        try (ByteArrayOutputStream result = new ByteArrayOutputStream()) {
            final Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.ENCRYPT_MODE, key);
            byte[] secret = cipher.doFinal(random);

            byte[] data = new byte[2];
            data[0] = (byte) ((secret.length >> 8) & 0xFF);
            data[1] = (byte) (secret.length & 0xFF);
            result.write(data);

            result.write(secret);
            result.write(aes.encrypt(text));

            return result.toByteArray();
        } catch (NoSuchAlgorithmException e) {
            throw new SystemException("e.sf.encr.9002", "encryption error (NoSuchAlgorithm).", e);
        } catch (NoSuchPaddingException e) {
            throw new SystemException("e.sf.encr.9003", "encryption error (NoSuchPadding).", e);
        } catch (InvalidKeyException e) {
            throw new SystemException("e.sf.encr.9004", "encryption error (InvalidKey).", e);
        } catch (IllegalBlockSizeException e) {
            throw new SystemException("e.sf.encr.9005", "encryption error (IllegalBlockSize).", e);
        } catch (BadPaddingException e) {
            throw new SystemException("e.sf.encr.9006", "encryption error (BadPadding).", e);
        } catch (IOException e) {
            throw new SystemException("e.sf.encr.9007", "input/output error.", e);
        }

    }

    @Override
    public String decryptByHybrid(String text, PrivateKey key) {
        return Utf8.decode(decryptByHybrid(Hex.decode(text), key));
    }

    // decryptByHybridメソッドのソースコード参考サイトは以下。
    // <https://github.com/dsyer/spring-security-rsa/blob/master/src/main/java/org/springframework/security/rsa/crypto/RsaSecretEncryptor.java>
    private byte[] decryptByHybrid(byte[] text, PrivateKey key) {

        try (ByteArrayInputStream input = new ByteArrayInputStream(text);
                ByteArrayOutputStream output = new ByteArrayOutputStream()) {
            byte[] b = new byte[2];
            input.read(b);
            int length = ((b[0] & 0xFF) << 8) | (b[1] & 0xFF);

            byte[] random = new byte[length];
            input.read(random);
            final Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.DECRYPT_MODE, key);
            String secret = new String(Hex.encode(cipher.doFinal(random)));
            byte[] buffer = new byte[text.length - random.length - 2];
            input.read(buffer);
            BytesEncryptor aes = Encryptors.standard(secret, salt);
            output.write(aes.decrypt(buffer));

            return output.toByteArray();
        } catch (NoSuchAlgorithmException e) {
            throw new SystemException("e.sf.encr.9002", "encryption error (NoSuchAlgorithm).", e);
        } catch (NoSuchPaddingException e) {
            throw new SystemException("e.sf.encr.9003", "encryption error (NoSuchPadding).", e);
        } catch (InvalidKeyException e) {
            throw new SystemException("e.sf.encr.9004", "encryption error (InvalidKey).", e);
        } catch (IllegalBlockSizeException e) {
            throw new SystemException("e.sf.encr.9005", "encryption error (IllegalBlockSize).", e);
        } catch (BadPaddingException e) {
            throw new SystemException("e.sf.encr.9006", "encryption error (BadPadding).", e);
        } catch (IOException e) {
            throw new SystemException("e.sf.encr.9007", "input/output error.", e);
        }
    }

}
