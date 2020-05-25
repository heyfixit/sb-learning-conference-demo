package com.pluralsight.conferencedemo.controllers;

// Controllers are classes

import com.pluralsight.conferencedemo.models.Session;
import com.pluralsight.conferencedemo.repositories.SessionRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // this class will respond to payloads incoming and outgoing as json rest endpoints
@RequestMapping("/api/v1/sessions") // what the base route will look like
public class SessionsController {
    @Autowired // tell spring to inject this, injection has something to do with auto-instantiation and easier mocking
    private SessionRepository sessionRepository;

    @GetMapping // which http verb to use
    public List<Session> list() {
        // findAll - JPA built method
        // return type is a list of sessions - jdbc will pass this response through jackson
        return sessionRepository.findAll();
    }

    @GetMapping
    @RequestMapping("{id}")
    public Session get(@PathVariable Long id) {
        // JPA auto-built method to get a single instance and run it through jackson as a response
        return sessionRepository.getOne(id);
    }

    @PostMapping // http post verb, no request mapping because we're posting to base
    @ResponseStatus(HttpStatus.CREATED)

    // @RequestBody - Spring MVC taking in all attributes in a json payload and automatically marshalling them
    // into a Session object, which can then be passed to the sessionRepository to saveAndFlush
    // save, flush, and commit are weird, but it seems like JPA and hibernate try to limit the number of actual
    // database transactions until the bitter end, so things might be only in memory a lot more in this spring boot
    // world than in other places like rails/django/node
    public Session create(@RequestBody final Session session) {

        // When working with Spring data JPA and entities, you can save objects as you're working with them but they
        // don't get committed to the database until your flush them, saveAndFlush does both all at once.
        return sessionRepository.saveAndFlush(session);
    }

    // Spring only provides @GetMapping and @PostMapping, the other verbs need to be done this way
    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable Long id) {
        // Also need to check for children records before deleting i.e. cascading deletes
        sessionRepository.deleteById(id);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.PUT)
    public Session update(@PathVariable Long id, @RequestBody Session session) {
        // Because this is a PUT, we expect all attributes to be passed in.  A PATCh might only need the differences
        // TODO: Add validation that all attributes are passed in, otherwise return 400 bad payload
        Session existingSession = sessionRepository.getOne(id);

        // Strange Java way of merging object properties and excluding some
        // Here we're excluding the ID from the object we're intending to update
        BeanUtils.copyProperties(session, existingSession, "session_id");
        return sessionRepository.saveAndFlush(existingSession);
    }
}
