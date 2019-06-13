package de.ukowohl.experiments.recreator.config;

import de.ukowohl.experiments.recreator.actuator.DatabaseHealthCheck;
import de.ukowohl.experiments.recreator.factory.SchemaValidatorFactory;
import de.ukowohl.experiments.recreator.service.CallableSchemaValidator;
import de.ukowohl.experiments.recreator.service.impl.HibernateSchemaValidator;
import org.hibernate.integrator.spi.Integrator;
import org.hibernate.jpa.boot.spi.IntegratorProvider;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernatePropertiesCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
@AutoConfigureAfter(HibernateJpaAutoConfiguration.class)
public class RecreatorConfiguration {

    @Bean
    public HibernatePropertiesCustomizer hibernatePropertiesCustomizer(CallableSchemaValidator service) {
        return (prop -> {
            List<Integrator> integrators = new ArrayList<>();
            integrators.add(service);
            prop.put("hibernate.integrator_provider", (IntegratorProvider) () -> integrators);
        });
    }

    @Bean
    public DatabaseHealthCheck databaseHealthCheck(CallableSchemaValidator validator) {
        return new DatabaseHealthCheck(validator);
    }


    @Bean
    @ConditionalOnMissingBean(CallableSchemaValidator.class)
    public CallableSchemaValidator hibernateSchemaValidator() {
        return new HibernateSchemaValidator();
    }

    @Bean
    public SchemaValidatorFactory schemaValidatorFactory() {
        return new SchemaValidatorFactory();
    }
}
