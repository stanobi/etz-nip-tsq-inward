package com.etz.nipinward.config;

import com.etz.nipinward.service.NIPKeyService;
import org.assertj.core.util.Preconditions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class EncryptionKeyConfig {

    private final AppConfig appConfig;

    public EncryptionKeyConfig(AppConfig appConfig) {
        this.appConfig = appConfig;
    }

    @Bean
    @Scope("prototype")
    public NIPKeyService nipKeyService() {
        String baseKeyFolder = Preconditions.checkNotNull(appConfig.getKey().getBaseFolder(), "app.config.key.baseFolder is required to be set on the application properties.");
        String publicKeyName = Preconditions.checkNotNull(appConfig.getKey().getPublicKeyName(), "app.config.key.publicKeyName is required to be set on the application properties.");
        String nipPublicKeyName = Preconditions.checkNotNull(appConfig.getKey().getNipPublicKeyName(), "app.config.key.nipPublicKeyName is required to be set on the application properties.");
        String privateKeyName = Preconditions.checkNotNull(appConfig.getKey().getPrivateKeyName(), "app.config.key.privateKeyName is required to be set on the application properties.");
        String privateKeyPass = Preconditions.checkNotNull(appConfig.getKey().getPrivateKeyPass(), "app.config.key.privateKeyPass is required to be set on the application properties.");
        return new NIPKeyService(baseKeyFolder, nipPublicKeyName, publicKeyName, privateKeyName, privateKeyPass);
    }
}
