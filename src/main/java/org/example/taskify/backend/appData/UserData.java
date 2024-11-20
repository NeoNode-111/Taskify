package org.example.taskify.backend.appData;

import lombok.Builder;

@Builder
public record UserData(
    String username,
    String email
)
{}
