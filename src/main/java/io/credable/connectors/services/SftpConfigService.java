package io.credable.connectors.services;


import io.credable.connectors.dto.GenericResponse;
import io.credable.connectors.dto.SftpConfigRequest;

import java.util.UUID;

public interface SftpConfigService {
    GenericResponse addSftpConfig(SftpConfigRequest sftpConfigRequest);
    GenericResponse getConfigDetails(UUID id);
    GenericResponse testConfigConnection(UUID id);
    GenericResponse getAll();
    GenericResponse editSftpConfig(SftpConfigRequest sftpConfigRequest, UUID configId);

}
