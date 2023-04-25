package io.credable.connectors.services;

import io.credable.connectors.dto.DatabaseConfigRequest;
import io.credable.connectors.dto.GenericResponse;

import java.util.UUID;

public interface DatabaseConfigService {
    GenericResponse addDatabaseConfig(DatabaseConfigRequest databaseConfigRequest);
    GenericResponse updateDatabaseConfig(DatabaseConfigRequest databaseConfigRequest, UUID id);
    GenericResponse getConfigDetails(UUID id);
    GenericResponse getAllDatabaseConfig();
    GenericResponse testDatabaseConfig(UUID id);
}
