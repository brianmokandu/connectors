package io.credable.connectors.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.credable.connectors.DatabaseType;
import io.credable.connectors.util.DatabaseEnumValidator;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class DatabaseConfigRequest {
    private UUID id;
    @NotNull
    @NotBlank
    private String host;
    private int port;
    @NotNull
    @NotBlank
    private String username;
    private String password;
    @NotNull
    @NotBlank
    private String database;
    @NotNull
    @NotBlank
    @DatabaseEnumValidator(value = {DatabaseType.MYSQL,DatabaseType.MSSQL, DatabaseType.H2,DatabaseType.ORACLE,DatabaseType.POSTGRESQL,DatabaseType.SQLITE })
    private String type;
    @NotNull
    @NotBlank
    private String schema;
}