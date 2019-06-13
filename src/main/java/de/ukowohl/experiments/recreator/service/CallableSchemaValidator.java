package de.ukowohl.experiments.recreator.service;

import org.hibernate.integrator.spi.Integrator;

public interface CallableSchemaValidator extends Integrator {

    boolean dbSchemaIsValid();

}
