package com.etz.nipinward.config;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.annotation.Validated;

@Configuration
@NoArgsConstructor
@ConfigurationProperties(prefix = "app.config")
@Validated
public class AppConfig {

    @NotNull(message = "app.config.key.* is required")
    private AppKeyConfig key;

    @NotNull(message = "app.config.nip.* is required")
    private AppNIPAPIConfig nip;

    @NotNull(message = "app.config.autoswitch.* is required")
    private AppAutoswitchConfig autoswitch;

    @NotBlank(message = "app.config.topicName is required")
    private String topicName;

    @NotBlank(message = "maxRetrialCount is required")
    @Min(value = 2, message = "minimum maxRetrialCount is 2")
    private Integer maxRetrialCount;

    @NotBlank(message = "maxTransactionForRetrialAtATime is required")
    @Min(value = 10, message = "minimum maxTransactionForRetrialAtATime is 10")
    private Integer maxTransactionForRetrialAtATime;

    public AppKeyConfig getKey() {
        return key;
    }

    public void setKey(AppKeyConfig key) {
        this.key = key;
    }

    public void setNip(AppNIPAPIConfig nip) {
        this.nip = nip;
    }

    public AppNIPAPIConfig getNip() {
        return nip;
    }

    public void setAutoswitch(AppAutoswitchConfig autoswitch) {
        this.autoswitch = autoswitch;
    }

    public AppAutoswitchConfig getAutoswitch() {
        return autoswitch;
    }

    public String getTopicName() {
        return topicName;
    }

    public void setTopicName(String topicName) {
        this.topicName = topicName;
    }

    public Integer getMaxRetrialCount() {
        return maxRetrialCount;
    }

    public void setMaxRetrialCount(Integer maxRetrialCount) {
        this.maxRetrialCount = maxRetrialCount;
    }

    public Integer getMaxTransactionForRetrialAtATime() {
        return maxTransactionForRetrialAtATime;
    }

    public void setMaxTransactionForRetrialAtATime(Integer maxTransactionForRetrialAtATime) {
        this.maxTransactionForRetrialAtATime = maxTransactionForRetrialAtATime;
    }
}
