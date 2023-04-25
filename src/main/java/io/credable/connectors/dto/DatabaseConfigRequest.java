package io.credable.connectors.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.credable.connectors.DatabaseType;
import io.credable.connectors.util.DatabaseEnumValidator;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class DatabaseConfigRequest {
    private UUID id;
    @NotNull
    @NotBlank
    private String host;
    @NotNull
    private Integer port;
    @NotNull
    @NotBlank
    private String username;
    private String password;
    @NotNull
    @NotBlank
    private String databaseName;
    @NotNull
    @DatabaseEnumValidator(value = {DatabaseType.MYSQL,DatabaseType.MSSQL, DatabaseType.H2,DatabaseType.ORACLE,DatabaseType.POSTGRESQL,DatabaseType.SQLITE })
    private String databaseType;
    @NotNull
    @NotBlank
    private String schemaName;
    private boolean useTls=false;

}
