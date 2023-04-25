package io.credable.connectors.db.repo;

import io.credable.connectors.db.entity.DatabaseConfig;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface DatabaseConfigRepo extends JpaRepository<DatabaseConfig, UUID> {
}
