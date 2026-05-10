package com.autobots.automanager.controles;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GersonControle {

    @GetMapping(value = "/gerson", produces = MediaType.IMAGE_JPEG_VALUE)
    public byte[] imagem() throws IOException {
        return Files.readAllBytes(Path.of("src/main/resources/static/gerson.png"));
    }
}