package org.acme.javafx.service.impl;

import org.acme.javafx.models.entities.GitVirtualRepository;
import org.acme.javafx.service.GitHubService;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.GenericType;
import java.util.List;

@ApplicationScoped
@Deprecated
public class GitHubServiceImpl implements GitHubService {

    private static final String GITHUB_API_URL = "https://api.github.com";

    @Override
    public List<GitVirtualRepository> getAllRepositories(String username) {
        try {
            return ClientBuilder.newClient()
                    .target(GITHUB_API_URL)
                    .path("/users/{username}/repos")
                    .resolveTemplate("username", username)
                    .request()
                    .get(new GenericType<List<GitVirtualRepository>>() {
                    });
        } catch (WebApplicationException e) {
            throw new WebApplicationException("Error" + e.getMessage());
        } catch (Exception e) {
            throw new RuntimeException("Failed to retrieve repositories from GitHub API", e);
        }
    }
}
