package com.library.servicelibrary;

import com.library.servicelibrary.repository.repository.impl.CustomRepositoryImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(repositoryBaseClass = CustomRepositoryImpl.class)
public class ServiceLibraryApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceLibraryApplication.class, args);
    }

}
