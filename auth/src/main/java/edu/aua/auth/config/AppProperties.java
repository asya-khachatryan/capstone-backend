package edu.aua.auth.config;

import jakarta.annotation.PostConstruct;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Base64;

@Component
@ConfigurationProperties(prefix = "app")
public class AppProperties {

    private final Auth auth = new Auth();

    public static class Auth {

        private String tokenSecret;
        private long tokenExpirationMsec;

        @PostConstruct
        public void init() {
            tokenSecret = Base64.getEncoder().encodeToString(
                    tokenSecret.getBytes());
        }

        public String getTokenSecret() {
            return tokenSecret;
        }

        public void setTokenSecret(final String tokenSecret) {
            this.tokenSecret = tokenSecret;
        }

        public long getTokenExpirationMsec() {
            return tokenExpirationMsec;
        }

        public void setTokenExpirationMsec(final long tokenExpirationMsec) {
            this.tokenExpirationMsec = tokenExpirationMsec;
        }
    }

    public Auth getAuth() {
        return auth;
    }
}
