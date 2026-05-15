package com.autobots.automanager.controles;

import java.io.IOException;

import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GersonControle {

    @GetMapping(value = "/gerson", produces = MediaType.IMAGE_PNG_VALUE)
    public byte[] imagem() throws IOException {
        return new ClassPathResource("static/gerson.png").getInputStream().readAllBytes();
    }
}