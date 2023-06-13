package org.acme.javafx.service;

import org.acme.javafx.models.entities.GitVirtualRepository;

import java.util.List;

@Deprecated
public interface GitHubService {
    List<GitVirtualRepository> getAllRepositories(String username);
}
