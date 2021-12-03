package com.rk.logging.tracing.configs;

import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.models.Operation;
import io.swagger.v3.oas.models.media.StringSchema;
import io.swagger.v3.oas.models.parameters.Parameter;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.customizers.OperationCustomizer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.HandlerMethod;

/**
 * Open API Configuration
 */
@Configuration
public class OpenApiConfiguration {
    @Value("${spring.sleuth.baggage.remote-fields}")
    private String requestIdHeaderName;

    /**
     * Bean of Global Headers
     *
     * @return
     */
    @Bean
    public OperationCustomizer customGlobalHeaders() {
        return (Operation operation, HandlerMethod handlerMethod) -> {
            Parameter missingParam1 = new Parameter().in(ParameterIn.HEADER.toString()).schema(new StringSchema())
                    .name(requestIdHeaderName).description("Request ID for tracing")
                    .example("ABC-12345").required(false);
            operation.addParametersItem(missingParam1);
            return operation;
        };
    }

}
