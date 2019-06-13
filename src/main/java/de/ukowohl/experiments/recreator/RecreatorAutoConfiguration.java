package de.ukowohl.experiments.recreator;

import de.ukowohl.experiments.recreator.config.RecreatorConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({
        RecreatorConfiguration.class
})
public class RecreatorAutoConfiguration {
}
