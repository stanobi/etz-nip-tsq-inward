package com.etz.nipinward.config;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AppKeyConfig {

    private String baseFolder;
    private String nipPublicKeyName;
    private String publicKeyName;
    private String privateKeyName;
    private String privateKeyPass;

}
