package org.example.taskify.backend.appData;

import lombok.Builder;

@Builder
public record TaskStatistic(
    int doneTasks,
    int undoneTasks,
    int forgottenTasks,
    int totalTasks
    )
{}
