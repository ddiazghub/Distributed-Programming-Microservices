/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.uninorte.distributed.programming.distributedserviceclient.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.ShortBufferException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 *
 * @author david
 */
@Service
public class FileCipher {
    private Cipher cipher;
    private byte[] salt;
    private org.slf4j.Logger log = LoggerFactory.getLogger(FileCipher.class);
    
    public FileCipher() throws NoSuchAlgorithmException, NoSuchPaddingException { 
        this.cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        this.salt = new byte[16];
    }
    
    public SecretKey createKey(String password) throws NoSuchAlgorithmException, InvalidKeySpecException {
        KeySpec spec = new PBEKeySpec(password.toCharArray(), this.salt, 65536, 256);
        SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
        byte[] key = factory.generateSecret(spec).getEncoded();

        return new SecretKeySpec(key, "AES");
    }
    
    public byte[] encrypt(Path path, String password) {
        byte[] content = null;
        
        try {
            content = Files.readAllBytes(path);
            cipher.init(Cipher.ENCRYPT_MODE, this.createKey(password));
            byte[] iv = cipher.getIV();
            byte[] encrypted = new byte[iv.length + cipher.getOutputSize(content.length)];
            System.arraycopy(iv, 0, encrypted, 0, 16);
            cipher.doFinal(content, 0, content.length, encrypted, 16);
            
            return encrypted;
        } catch (NoSuchAlgorithmException | InvalidKeySpecException | ShortBufferException | IOException | InvalidKeyException | BadPaddingException | IllegalBlockSizeException ex) {
            log.warn("Error", ex);
            
            return content;
        }
    }
    
    public void decrypt(Path filepath, String password, byte[] content) throws IOException {
        try {
            cipher.init(Cipher.DECRYPT_MODE, this.createKey(password), new IvParameterSpec(content, 0, 16));
            byte[] decrypted = new byte[cipher.getOutputSize(content.length)];
            cipher.doFinal(content, 16, content.length - 16, decrypted, 0);
            Files.write(filepath, decrypted);
        } catch (NoSuchAlgorithmException | InvalidKeySpecException | InvalidAlgorithmParameterException | InvalidKeyException | BadPaddingException | IllegalBlockSizeException | ShortBufferException e) {
            log.error("Error", e);
            Files.write(filepath, content);
            
            throw new RuntimeException("Error during decryption");
        }
    }
}
