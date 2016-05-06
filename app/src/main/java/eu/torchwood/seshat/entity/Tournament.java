package eu.torchwood.seshat.entity;

import java.util.Date;

/**
 * Created by jop on 5/6/16.
 */
public class Tournament {
    private Long id;
    private String description;
    private Date created;

    public Tournament(String description){
        this.description = description;
        this.created = new Date();
    }

    public Tournament(Long id, String description, Date created) {
        this.id = id;
        this.description = description;
        this.created = created;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }
}
