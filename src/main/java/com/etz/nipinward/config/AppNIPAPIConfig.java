package com.etz.nipinward.config;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AppNIPAPIConfig {

    private String tsqUrl;
    private String bankCode;
    private String nipCardNo;
    private String etzCode;
    private String etzKeyPass;
    private String cardPin;
    private String cardExpiryDate;

}
