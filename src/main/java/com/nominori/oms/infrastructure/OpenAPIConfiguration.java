package com.nominori.oms.infrastructure;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;

@OpenAPIDefinition(
        info = @Info(
                title = "3D Workshop API",
                description = "The 3D Assets Workshop REST API powers an online shop for managing and selling 3D assets effortlessly.",
                contact = @Contact(
                        name = "nominori-dev",
                        url = "https://github.com/nominori-dev",
                        email = "nominori@bdv.pw"
                ),
                license = @License(
                        name = "MIT License",
                        url = "https://github.com/nominori-dev/3d-workshop-backend/LICENSE"
                )
        )
)
public class OpenAPIConfiguration {
}
