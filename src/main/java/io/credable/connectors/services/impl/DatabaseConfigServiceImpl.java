package io.credable.connectors.services.impl;

import io.credable.connectors.dto.DatabaseConfigRequest;
import io.credable.connectors.dto.GenericResponse;
import io.credable.connectors.services.DatabaseConfigService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Log4j2
public class DatabaseConfigServiceImpl implements DatabaseConfigService {
    @Override
    public GenericResponse addDatabaseConfig(DatabaseConfigRequest databaseConfigRequest) {
        return null;
    }
}
