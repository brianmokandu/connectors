package io.credable.connectors.services.impl;

import io.credable.connectors.db.entity.DatabaseConfig;
import io.credable.connectors.db.repo.DatabaseConfigRepo;
import io.credable.connectors.dto.DatabaseConfigRequest;
import io.credable.connectors.dto.GenericResponse;
import io.credable.connectors.exception.CustomException;
import io.credable.connectors.services.DatabaseConfigService;
import io.credable.connectors.util.DataMapper;
import io.credable.connectors.util.DataSourceFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Log4j2
public class DatabaseConfigServiceImpl implements DatabaseConfigService {
    private final DatabaseConfigRepo databaseConfigRepo;
    @Override
    public GenericResponse addDatabaseConfig(DatabaseConfigRequest databaseConfigRequest) {
        var res = DataMapper.doMap(databaseConfigRequest, DatabaseConfig.class);

        return GenericResponse.builder().data(DataMapper.doMap(databaseConfigRepo.save(res),DatabaseConfigRequest.class)).message("Saved database config.").build();
    }

    @Override
    public GenericResponse updateDatabaseConfig(DatabaseConfigRequest databaseConfigRequest, UUID id) {
        var databaseConfig = databaseConfigRepo.findById(id).orElseThrow(()->new CustomException(HttpStatus.BAD_REQUEST,"Config not found."));
        databaseConfig.setDatabaseName(databaseConfigRequest.getDatabaseName());
        databaseConfig.setHost(databaseConfigRequest.getHost());
        databaseConfig.setPort(databaseConfigRequest.getPort());
        databaseConfig.setUsername(databaseConfigRequest.getUsername());
        databaseConfig.setPassword(databaseConfigRequest.getPassword());
        databaseConfig.setDatabaseType(databaseConfigRequest.getDatabaseType());
        databaseConfig.setSchemaName(databaseConfigRequest.getSchemaName());

        return GenericResponse.builder().data(DataMapper.doMap(databaseConfigRepo.save(databaseConfig), DatabaseConfigRequest.class)).message("Config updated.").build();
    }


    @Override
    public GenericResponse getConfigDetails(UUID id) {
        var databaseConfig = databaseConfigRepo.findById(id).orElseThrow(()->new CustomException(HttpStatus.BAD_REQUEST,"Config not found."));
        return  GenericResponse.builder().data(DataMapper.doMap(databaseConfig, DatabaseConfigRequest.class)).message("Retrieved config.").build();
    }

    @Override
    public GenericResponse getAllDatabaseConfig() {
        return GenericResponse.builder().data(databaseConfigRepo.findAll()).message("Retrieved all configs.").build();
    }

    @Override
    public GenericResponse testDatabaseConfig(UUID id) {
        var databaseConfig = databaseConfigRepo.findById(id).orElseThrow(()->new CustomException(HttpStatus.BAD_REQUEST,"Config not found."));
        String url = "jdbc:%s://%s:%d/%s".formatted(databaseConfig.getDatabaseType(),databaseConfig.getHost(),databaseConfig.getPort(),databaseConfig.getDatabaseName());
        System.out.println(url);
        String username = databaseConfig.getUsername();
        String password = databaseConfig.getPassword();
        DataSource dataSource = DataSourceFactory.createDataSource(url, username, password);
        DatabaseService databaseService = new DatabaseService(dataSource);
        String result = "Connection successful";
        if (!databaseService.testConnection(databaseConfig.getSchemaName())) {
            result = "Connection failed";
        }
        return GenericResponse.builder().data(result).message(result).build();
    }
}
