package com.k.web.constructor.model.site;

import com.k.web.constructor.model.page.Page;
import com.k.web.constructor.model.user.User;
import lombok.Data;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Set;

/**
 * @author a.kolenchenko
 * @since 19.05.17
 */
@Entity
@Table(name = "SITE")
@Data
public class Site {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "site")
    private Set<Page> pages;

}
