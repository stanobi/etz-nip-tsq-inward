package com.etz.nipinward.service;

import com.etz.nipinward.exception.ExpectationFailedException;
import com.etz.nipinward.exception.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import nfp.ssm.core.SSMLib;
import org.springframework.stereotype.Service;

import java.nio.file.Path;

@Slf4j
@Service
public class NIPEncryptionService {

    private final NIPKeyService nipKeyService;

    public NIPEncryptionService(NIPKeyService nipKeyService) {
        this.nipKeyService = nipKeyService;
    }

    public String encrypt(String cleartext) throws ExpectationFailedException, NotFoundException {
        Path publicKeyPath = nipKeyService.getNIPPublicKey();
        Path privateKeyPath = nipKeyService.getPrivateKey();

        SSMLib theLib = new SSMLib(publicKeyPath.toAbsolutePath().toString(),
                privateKeyPath.toAbsolutePath().toString());

        String ciphertext = "";
        try {
            log.info("Clear Text: " + cleartext);
            ciphertext = theLib.encryptMessage(cleartext);
            log.info("Ciper Text: " + ciphertext);
        } catch (Exception ex) {
            log.error("Unable to encrypt data : ", ex);
            throw new ExpectationFailedException();
        }
        return ciphertext;
    }

    public String encryptForTSQ(String cleartext) throws ExpectationFailedException, NotFoundException {
        Path publicKeyPath = nipKeyService.getNIPPublicKey();
        Path privateKeyPath = nipKeyService.getPrivateKey();

        SSMLib theLib = new SSMLib(publicKeyPath.toAbsolutePath().toString(),
                privateKeyPath.toAbsolutePath().toString());

        String ciphertext = "";
        try {
            log.info("Clear Text: " + cleartext);
            ciphertext = theLib.encryptMessage(cleartext);
            log.info("Ciper Text: " + ciphertext);
        } catch (Exception ex) {
            log.error("Unable to encrypt data : ", ex);
            throw new ExpectationFailedException();
        }
        return ciphertext;
    }

    public String decrypt(String ciphertext) throws NotFoundException, ExpectationFailedException {
        Path publicKeyPath = nipKeyService.getPublicKey();
        Path privateKeyPath = nipKeyService.getPrivateKey();

        String cleartext = "";
        SSMLib theLib = new SSMLib(publicKeyPath.toAbsolutePath().toString(),
                privateKeyPath.toAbsolutePath().toString());

        try {
            log.info("Ciper Text " + ciphertext);
            cleartext = theLib.decryptFile(ciphertext, nipKeyService.getPrivateKeyPass());
            log.info("Clear Text: " + cleartext);
        } catch (Exception ex) {
            log.error("Unable to decrypt data : ", ex);
            throw new ExpectationFailedException();
        }
        return cleartext;
    }

    public String decryptForTSQ(String ciphertext) throws NotFoundException, ExpectationFailedException {
        Path publicKeyPath = nipKeyService.getPublicKey();
        Path privateKeyPath = nipKeyService.getPrivateKey();

        String cleartext = "";
        SSMLib theLib = new SSMLib(publicKeyPath.toAbsolutePath().toString(),
                privateKeyPath.toAbsolutePath().toString());

        try {
            log.info("Ciper Text " + ciphertext);
            cleartext = theLib.decryptFile(ciphertext, nipKeyService.getPrivateKeyPass());
            log.info("Clear Text: " + cleartext);
        } catch (Exception ex) {
            log.error("Unable to decrypt data : ", ex);
            throw new ExpectationFailedException();
        }
        return cleartext;
    }

}
