# Actuator Extension for Schema Validation

## Purpose
Recreating database state on test systems on demand can cause loss of database connections as soon as you drop/modify schema during runtime of your spring-boot container. Yes I know that this behaviour is actually a wrong behabiour from the beginning, sometimes you still do have such use cases. This Actuator Extension is an experiment in order to add hibernate schema validation during runtime of the container whenever it is necessary.

## Usage
Just plugin the dependency into your POM and make sure that ```@EnableAutoConfiguration``` is activated.

```
<dependency>
    <groupId>de.ukowohl.experiments.recreator</groupId>
    <artifactId>recreator</artifactId>
    <version>0.1.0</version>
</dependency>
```

## Customization
You can customize the mechanism how your database schema  will be validated by registering a custom bean implementing ```de.ukowohl.experiments.recreator.service.CallableSchemaValidator```
