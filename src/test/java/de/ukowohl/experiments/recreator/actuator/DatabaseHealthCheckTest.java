package de.ukowohl.experiments.recreator.actuator;

import de.ukowohl.experiments.recreator.service.CallableSchemaValidator;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.actuate.health.Health;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class DatabaseHealthCheckTest {

    @Mock
    private CallableSchemaValidator validator;

    @InjectMocks
    private DatabaseHealthCheck databaseHealthCheck;

    @Test
    void shouldReturnUp() {
        executeTest(true, Health.up());
    }

    @Test
    void shouldReturnDown() {
        executeTest(false, Health.down());
    }

    private void executeTest(boolean isUp, Health.Builder expected) {
        doReturn(isUp).when(validator).dbSchemaIsValid();

        Health health = databaseHealthCheck.health();

        Assertions.assertThat(health).isEqualTo(expected.build());
        verify(validator).dbSchemaIsValid();
    }
}
