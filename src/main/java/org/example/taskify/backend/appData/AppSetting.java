package org.example.taskify.backend.appData;

import lombok.Builder;

@Builder
public record AppSetting(
    boolean darkMode
)
{}