package com.EmpolyeeManagement.app;
import org.springframework.boot.SpringApplication;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication
@EnableDiscoveryClient
@ComponentScan(basePackages = "com.EmpolyeeManagement.app")  // Make sure to scan the right package
public class EmployeeManagementApp {
    public static void main(String[] args) {
        SpringApplication.run(EmployeeManagementApp.class, args);
    }
}
