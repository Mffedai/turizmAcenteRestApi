package dev.patika.turizmAcente.dao;

import dev.patika.turizmAcente.entity.Session;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SessionRepo extends JpaRepository<Session, Long> {

}
