package com.k.web.constructor.event;

import com.k.web.constructor.model.user.User;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

/**
 * @author a.kolenchenko
 * @since 19.05.17
 */
public class UserRegistrationEvent extends ApplicationEvent {

    @Getter
    private User user;

    /**
     * Create a new ApplicationEvent.
     *
     * @param user the object on which the event initially occurred (never {@code null})
     */
    public UserRegistrationEvent(User user) {
        super(user);
        this.user = user;
    }
}
