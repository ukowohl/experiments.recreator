package de.ukowohl.experiments.recreator.actuator;

import de.ukowohl.experiments.recreator.service.CallableSchemaValidator;
import lombok.AllArgsConstructor;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;


@AllArgsConstructor
public class DatabaseHealthCheck implements HealthIndicator {

    private final CallableSchemaValidator validator;

    @Override
    public Health health() {
        Health.Builder healthBuilder = validator.dbSchemaIsValid() ? Health.up() : Health.down();

        return healthBuilder.build();
    }
}
