package com.taskmanager.backend.iam.domain.model.commands;

public record SignInCommand(String username, String password) {
}