package com.k.web.constructor.event.listener;

import com.k.web.constructor.event.UserRegistrationEvent;
import com.k.web.constructor.model.token.RegistrationToken;
import com.k.web.constructor.model.token.RegistrationTokenRepository;
import com.k.web.constructor.model.user.User;
import com.k.web.constructor.service.ConfigurationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.mail.internet.MimeMessage;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

@Component
@Async(value = "mailingServiceTaskExecutor")
public class MailingServiceListener implements ApplicationListener<UserRegistrationEvent> {

    private static final Logger LOGGER = LoggerFactory.getLogger(MailingServiceListener.class);

    private String endpoint;

    @Autowired
    private JavaMailSenderImpl sender;
    @Autowired
    private ConfigurationService configurationService;
    @Autowired
    private RegistrationTokenRepository tokenRepository;

    @PostConstruct
    private void init() {
        endpoint = String.format("%s://%s:%d/registration/token/",
                configurationService.getScheme(), configurationService.getHost(), configurationService.getPort());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void onApplicationEvent(UserRegistrationEvent event) {
        User user = event.getUser();
        RegistrationToken token = createRegistrationToken(user);
        try {
            MimeMessage message = sender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message);
            helper.setFrom("Constructor");
            helper.setSubject("Constructor. Registration token.");
            helper.setTo(user.getEmail());
            helper.setText("<html><body>Please <a href='" + endpoint + token.getToken()
                    + "'>verify</a> your email.</body></html>", true);
            sender.send(message);
        } catch (Exception e) {
            LOGGER.error("Cannot send registration confirmation message to \"" + user.getEmail() + "\"", e);
        }
    }

    private RegistrationToken createRegistrationToken(User user) {
        RegistrationToken token = new RegistrationToken();
        token.setUser(user);
        token.setToken(UUID.randomUUID().toString());
        token.setToken(Long.toHexString(ThreadLocalRandom.current().nextLong(Integer.MAX_VALUE, Long.MAX_VALUE)));
        token.setExpiration(Instant.now().plus(1, ChronoUnit.DAYS));

        return tokenRepository.save(token);
    }

}
