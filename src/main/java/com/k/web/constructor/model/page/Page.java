package com.k.web.constructor.model.page;

import com.k.web.constructor.model.site.Site;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * @author a.kolenchenko
 * @since 19.05.17
 */
@Entity
@Table(name = "PAGE")
@Data
public class Page {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name = "site_id")
    private Site site;

}
