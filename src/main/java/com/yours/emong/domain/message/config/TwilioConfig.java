package com.yours.emong.domain.message.config;

//import com.twilio.Twilio;
import com.twilio.Twilio;
import jakarta.annotation.PostConstruct;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "twilio")
@Data
public class TwilioConfig {

    private String accountSid;
    private String authToken;
    private String fromPhoneNumber;

    @PostConstruct //클래스의 빈 주입(의존성 주입) 이 이뤄진 후 실행.
    public void init() {
        Twilio.init(accountSid, authToken);
    }
}
