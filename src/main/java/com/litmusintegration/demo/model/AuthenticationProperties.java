package com.litmusintegration.demo.model;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import java.util.UUID;

@Configuration
public class AuthenticationProperties {
    private String execution_id = UUID.randomUUID().toString();
    @Value("${auth_api_url}")
    private String auth_api_url;
    @Value("${auth_api_port}")
    private String auth_api_port;
    @Value("${litmus_username}")
    private String litmus_username;
    @Value("${litmus_password}")
    private String litmus_password;
    @Value("${server_api_url}")
    private String server_api_url;
    @Value("${server_api_port}")
    private String server_api_port;
    @Value("${scenario_yaml_path}")
    private String scenario_yaml_path;
    @Value("${cluster_type}")
    private String cluster_type;
    @Value("${backoff_minutes}")
    private Integer backoff_minutes;
    @Value("${timeout_minutes}")
    private Integer timeout_minutes;

    public String getExecution_id() {
        return execution_id;
    }

    public String getAuth_api_url() {
        return auth_api_url;
    }

    public String getAuth_api_port() {
        return auth_api_port;
    }

    public String getLitmus_username() {
        return litmus_username;
    }

    public String getLitmus_password() {
        return litmus_password;
    }

    public String getServer_api_url() {
        return server_api_url;
    }

    public String getServer_api_port() {
        return server_api_port;
    }

    public String getScenario_yaml_path() {
        return scenario_yaml_path;
    }

    public String getCluster_type() {
        return cluster_type;
    }

    public Integer getBackoff_minutes() {
        return backoff_minutes;
    }

    public Integer getTimeout_minutes() {
        return timeout_minutes;
    }
}
