package io.credable.connectors.controllers;

import io.credable.connectors.dto.DatabaseConfigRequest;
import io.credable.connectors.dto.GenericResponse;
import io.credable.connectors.services.DatabaseConfigService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

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
}
