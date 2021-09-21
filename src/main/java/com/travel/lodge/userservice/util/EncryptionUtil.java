package com.travel.lodge.userservice.util;

import com.travel.lodge.userservice.exception.KeyGenerationException;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;

@Component
public class EncryptionUtil {

    @Value("${pin.encryption.initVector}")
    private String initVector;

    @Value("${pin.encryption.key}")
    private String key;

    public String encrypt(String value) {

        var iv = new IvParameterSpec(initVector.getBytes(StandardCharsets.UTF_8));
        var secretKeySpec = new SecretKeySpec(key.getBytes(StandardCharsets.UTF_8), "AES");

        Cipher cipher;
        var encrypted = new byte[0];
        try {
            cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
            cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, iv);
            encrypted = cipher.doFinal(value.getBytes());
        } catch (Exception e) {
            throw new KeyGenerationException(e.getMessage());
        }

        return Base64.encodeBase64String(encrypted);

    }
}
