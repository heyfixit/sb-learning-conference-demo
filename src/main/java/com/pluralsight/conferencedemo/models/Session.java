package com.pluralsight.conferencedemo.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.List;

// Entity name is name of db table
// Class name is singular, represents single instance
@Entity(name = "sessions")

// Ignoring these two properties is required as there's no serializer for their types
// These are stub methods for handling lazy loading and eager loading, hibernate adds these when
// relationships exist on a class.
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"}) // this parameter is a Set
public class Session {

    // convention is to use @column annotation such that we can have Java standard camelcase names
    // for this demo, to be quick, we can use the exact column names and jpa will autobind to those columns
    // JPA = java persistence api

    // annotation to specify which attribute is PK
    @Id
    // Specifies how Id field gets populated on each created record
    // IDENTITY strategy - JPA will use postgres created sequence for PK values
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long session_id;
    private String session_name;
    private String session_description;
    private Integer session_length;

    @ManyToMany
    @JoinTable(
            name = "session_speakers",
            joinColumns = @JoinColumn(name = "session_id"),
            inverseJoinColumns = @JoinColumn(name = "speaker_id")
    )
    private List<Speaker> speakers;

    // add a default constructor to all entities - helps with serialization/deserialization
    public Session() {
    }

    // to generate getters and setters:
    // Right click in the class, go to Generate... and do Getters and Setters
    // can select multiples
    public List<Speaker> getSpeakers() {
        return speakers;
    }

    public void setSpeakers(List<Speaker> speakers) {
        this.speakers = speakers;
    }

    public Long getSession_id() {
        return session_id;
    }

    public void setSession_id(Long session_id) {
        this.session_id = session_id;
    }

    public String getSession_name() {
        return session_name;
    }

    public void setSession_name(String session_name) {
        this.session_name = session_name;
    }

    public String getSession_description() {
        return session_description;
    }

    public void setSession_description(String session_description) {
        this.session_description = session_description;
    }

    public Integer getSession_length() {
        return session_length;
    }

    public void setSession_length(Integer session_length) {
        this.session_length = session_length;
    }
}
