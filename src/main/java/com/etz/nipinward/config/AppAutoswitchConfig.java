package com.etz.nipinward.config;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AppAutoswitchConfig {

    private String ip;
    private String port;
    private String key;
    private String schemeCode;
    private String beChannelId;
    private String ftChannelId;

}
