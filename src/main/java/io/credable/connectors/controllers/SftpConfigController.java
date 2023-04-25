package io.credable.connectors.controllers;

import io.credable.connectors.dto.GenericResponse;
import io.credable.connectors.dto.SftpConfigRequest;
import io.credable.connectors.services.SftpConfigService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RequestMapping("api/v1/connectors/sftp")
@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class SftpConfigController {
    private final SftpConfigService sftpConfigService;

    @PostMapping("/add")
    public GenericResponse addConfig(@Valid @RequestBody SftpConfigRequest sftpConfigRequest) {
        return sftpConfigService.addSftpConfig(sftpConfigRequest);
    }

    @GetMapping("/all")
    public GenericResponse getAll() {
        return sftpConfigService.getAll();
    }

    @GetMapping("/details/{id}")
    public GenericResponse getConfigDetails(@PathVariable("id") UUID id) {
        return sftpConfigService.getConfigDetails(id);
    }

    @PutMapping("/edit/{id}")
    public GenericResponse updateConfig(@Valid @RequestBody SftpConfigRequest sftpConfigRequest, @PathVariable("id") UUID id) {
        return sftpConfigService.editSftpConfig(sftpConfigRequest, id);
    }

    @GetMapping("/test/{id}")
    public GenericResponse testConfigConnection(@PathVariable("id") UUID id) {
        return sftpConfigService.testConfigConnection(id);
    }
}
