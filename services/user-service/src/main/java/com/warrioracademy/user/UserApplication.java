package com.warrioracademy.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;


@LoadBalancerClient("user")
@EnableDiscoveryClient
@RefreshScope
@EnableJpaAuditing(auditorAwareRef = "auditorAwareProvider")
@SpringBootApplication
public class UserApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserApplication.class, args);
	}

}
