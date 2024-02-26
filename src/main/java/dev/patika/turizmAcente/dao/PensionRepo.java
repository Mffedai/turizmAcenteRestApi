package dev.patika.turizmAcente.dao;

import dev.patika.turizmAcente.entity.Pension;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PensionRepo extends JpaRepository<Pension, Long> {
}
