package br.com.lucasbpo.course.resources.exceptions;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.time.Instant;

public record StandardError(
        @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "GMT")
        Instant timestamp,
        Integer status,
        String error,
        String message,
        String path
) implements Serializable {}
