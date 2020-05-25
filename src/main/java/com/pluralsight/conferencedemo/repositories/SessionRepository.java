package com.pluralsight.conferencedemo.repositories;

import com.pluralsight.conferencedemo.models.Session;
import org.springframework.data.jpa.repository.JpaRepository;

// JPA Repositories are interfaces

// extending JpaRepository gives us all of the ORM Features
public interface SessionRepository extends JpaRepository<Session, Long> {
}
