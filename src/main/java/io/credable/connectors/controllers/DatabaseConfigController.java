package io.credable.connectors.controllers;

import io.credable.connectors.dto.DatabaseConfigRequest;
import io.credable.connectors.dto.GenericResponse;
import io.credable.connectors.services.DatabaseConfigService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RequestMapping("api/v1/connectors/database")
@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class DatabaseConfigController {
    private final DatabaseConfigService databaseConfigService;
    @PostMapping("/add")
    public GenericResponse addDatabase(@Valid @RequestBody DatabaseConfigRequest databaseConfigRequest)
    {
       return databaseConfigService.addDatabaseConfig(databaseConfigRequest);
    }
    @GetMapping("/all")
    public GenericResponse getAll() {
        return databaseConfigService.getAllDatabaseConfig();
    }

    @GetMapping("/details/{id}")
    public GenericResponse getConfigDetails(@PathVariable("id") UUID id) {
        return databaseConfigService.getConfigDetails(id);
    }

    @PutMapping("/edit/{id}")
    public GenericResponse updateConfig(@Valid @RequestBody DatabaseConfigRequest databaseConfigRequest, @PathVariable("id") UUID id) {
        return databaseConfigService.updateDatabaseConfig(databaseConfigRequest, id);
    }

    @GetMapping("/test/{id}")
    public GenericResponse testConfigConnection(@PathVariable("id") UUID id) {
        return databaseConfigService.testDatabaseConfig(id);
    }
}
