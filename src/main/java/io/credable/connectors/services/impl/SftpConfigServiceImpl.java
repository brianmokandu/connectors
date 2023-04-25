package io.credable.connectors.services.impl;

import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import io.credable.connectors.db.entity.SftpConfig;
import io.credable.connectors.db.repo.SftpConfigRepo;
import io.credable.connectors.dto.GenericResponse;
import io.credable.connectors.dto.SftpConfigRequest;
import io.credable.connectors.exception.CustomException;
import io.credable.connectors.services.SftpConfigService;
import io.credable.connectors.util.DataMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.UUID;

import static java.util.Objects.isNull;

@Log4j2
@Service
@RequiredArgsConstructor
public class SftpConfigServiceImpl implements SftpConfigService {
    private final SftpConfigRepo sftpConfigRepo;
    @Override
    public GenericResponse addSftpConfig(SftpConfigRequest sftpConfigRequest) {
        if (isNull(sftpConfigRequest.getPassphrase()) && isNull(sftpConfigRequest.getPrivateKey()))
            throw  new CustomException(HttpStatus.BAD_REQUEST,"You must provide either privateKey or passphrase or both.");
        var sftpConfig = sftpConfigRepo.save(DataMapper.doMap(sftpConfigRequest, SftpConfig.class));
        return  GenericResponse.builder().data(DataMapper.doMap(sftpConfig, SftpConfigRequest.class)).message("Added config.").build();
    }

    @Override
    public GenericResponse getConfigDetails(UUID id) {
        var sftpConfig = sftpConfigRepo.findById(id).orElseThrow(()->new CustomException(HttpStatus.BAD_REQUEST,"Config not found."));
        return  GenericResponse.builder().data(DataMapper.doMap(sftpConfig, SftpConfigRequest.class)).message("Retrieved config.").build();
    }

    @Override
    public GenericResponse testConfigConnection(UUID id) {
        SftpConfig sftpConfig = sftpConfigRepo.findById(id)
                .orElseThrow(() -> new CustomException(HttpStatus.BAD_REQUEST,"Invalid sftpConfigId"));
        String result;
        if (sftpConfig.getPrivateKey() != null) {
            log.info("Testing with private key");
            result = testWithPrivateKey(sftpConfig);
        }
        else {
            log.info("Testing with passphrase");
            result = testWithPassphrase(sftpConfig);
        }
        return GenericResponse.builder().data(result).message("Testing connection.").build();
    }
    private String testWithPrivateKey(SftpConfig sftpConfig)
    {
        String host = sftpConfig.getHost();
        String username = sftpConfig.getUsername();
        String privateKey = sftpConfig.getPrivateKey();
        String passphrase = sftpConfig.getPassphrase();
        int port = sftpConfig.getPort() != 0 ? sftpConfig.getPort() : 22;
        String result = "SFTP connection successful.";
        try {
            JSch jsch = new JSch();
            jsch.addIdentity(privateKey, passphrase);
            Session session = jsch.getSession(username, host, port);
            session.setConfig("StrictHostKeyChecking", "no");
            session.connect();
            session.disconnect();
        } catch (JSchException e) {
            result = "SFTP connection test failed: " + e.getMessage();
        }

        return result;

    }
    private String testWithPassphrase(SftpConfig ftpConfig)
    {

        String host = ftpConfig.getHost();
        String username = ftpConfig.getUsername();
        String password = ftpConfig.getPassphrase();
        int port = ftpConfig.getPort() != 0 ? ftpConfig.getPort() : 22;

        String result = "SSH connection test successful";
        try {
            JSch jsch = new JSch();
            Session session = jsch.getSession(username, host, port);
            session.setPassword(password);
            session.setConfig("StrictHostKeyChecking", "no");
            session.connect();
            session.disconnect();
        } catch (JSchException e) {
            result = "SSH connection test failed: " + e.getMessage();
        }

        return result;
    }

    @Override
    public GenericResponse getAll() {
        var sftpConfigs = sftpConfigRepo.findAll();
        return  GenericResponse.builder().data(sftpConfigs).message("Retrieved all configs.").build();
    }

    @Override
    public GenericResponse editSftpConfig(SftpConfigRequest sftpConfigRequest, UUID configId) {
        if (isNull(sftpConfigRequest.getPassphrase()) && isNull(sftpConfigRequest.getPrivateKey()))
            throw  new CustomException(HttpStatus.BAD_REQUEST,"You must provide either privateKey or passphrase or both.");
        var sftpConfig = sftpConfigRepo.findById(configId).orElseThrow(()->new CustomException(HttpStatus.BAD_REQUEST,"Config not found."));
        sftpConfig.setPassphrase(sftpConfigRequest.getPassphrase());
        sftpConfig.setPrivateKey(sftpConfigRequest.getPrivateKey());
        sftpConfig.setHost(sftpConfigRequest.getHost());
        sftpConfig.setPort(sftpConfigRequest.getPort());
        sftpConfig.setUsername(sftpConfigRequest.getUsername());
        sftpConfigRepo.save(sftpConfig);
        return  GenericResponse.builder().data(DataMapper.doMap(sftpConfig, SftpConfigRequest.class)).message("Updated config.").build();
    }
}
