package eu.torchwood.seshat.entity;

import java.util.Date;

/**
 * Created by jop on 5/6/16.
 */
public class Event {

    private Long id;
    private Long tournamentId;
    private String description;
    private String format;
    private int rounds;
    private Date created;

    public Event(Long tournamentId){
        this.tournamentId = tournamentId;
        this.created = new Date();
    }

    public Event(Long id, Long tournamentId, String description, String format, int rounds, Date created) {
        this.id = id;
        this.tournamentId = tournamentId;
        this.description = description;
        this.created = created;
        this.format = format;
        this.rounds = rounds;
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

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public int getRounds() {
        return rounds;
    }

    public void setRounds(int rounds) {
        this.rounds = rounds;
    }

    public Long getTournamentId() {
        return tournamentId;
    }

    public void setTournamentId(Long tournamentId) {
        this.tournamentId = tournamentId;
    }
}
