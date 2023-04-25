package io.credable.connectors.services;

import io.credable.connectors.dto.DatabaseConfigRequest;
import io.credable.connectors.dto.GenericResponse;

public interface DatabaseConfigService {
    GenericResponse addDatabaseConfig(DatabaseConfigRequest databaseConfigRequest);
}
