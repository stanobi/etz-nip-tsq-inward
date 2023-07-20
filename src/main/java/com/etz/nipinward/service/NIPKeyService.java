package com.etz.nipinward.service;

import com.etz.nipinward.exception.NotFoundException;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Slf4j
public class NIPKeyService {

    private final String baseFolder;
    private final String nipPublicKeyName;
    private final String publicKeyName;
    private final String privateKeyName;
    private final String privateKeyPass;

    public NIPKeyService(String baseFolder, String nipPublicKeyName, String publicKeyName,
                         String privateKeyName, String privateKeyPass) {
        this.baseFolder = baseFolder;
        this.nipPublicKeyName = nipPublicKeyName;
        this.publicKeyName = publicKeyName;
        this.privateKeyName = privateKeyName;
        this.privateKeyPass = privateKeyPass;
    }

    public Path getNIPPublicKey() throws NotFoundException {
        Path publicKeyPath = Paths.get(getBaseFolder(), getNipPublicKeyName());
        checkFileExists(publicKeyPath);
        return publicKeyPath;
    }

    public Path getPublicKey() throws NotFoundException {
        Path publicKeyPath = Paths.get(getBaseFolder(), getPublicKeyName());
        checkFileExists(publicKeyPath);
        return publicKeyPath;
    }

    public Path getPrivateKey() throws NotFoundException {
        Path privateKeyPath = Paths.get(getBaseFolder(), getPrivateKeyName());
        checkFileExists(privateKeyPath);
        return privateKeyPath;
    }

    private void checkFileExists(final Path path) throws NotFoundException {
        if(!Files.exists(path)) {
            log.error("Unable to find key in file path, {}", path.toAbsolutePath());
            throw new NotFoundException(path.toAbsolutePath()+ " could not be found");
        }
    }

    public String getBaseFolder() {
        File file = new File(baseFolder);
        if (!file.exists() || !file.isDirectory()) {
            file.mkdir();
        }
        return baseFolder;
    }

    public String getNipPublicKeyName() {
        return nipPublicKeyName;
    }

    public String getPublicKeyName() {
        return publicKeyName;
    }

    public String getPrivateKeyName() {
        return privateKeyName;
    }

    public String getPrivateKeyPass() {
        return privateKeyPass;
    }

}