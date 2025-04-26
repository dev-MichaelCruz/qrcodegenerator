package com.michaelcruz.qrcodegenerator.controller;

import com.michaelcruz.qrcodegenerator.dto.QrCodeGenerateRequestDto;
import com.michaelcruz.qrcodegenerator.dto.QrCodeGenerateResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/qrcode")
public class QrCodeController {

    @PostMapping
    public ResponseEntity<QrCodeGenerateResponseDto> generate(
            @RequestBody QrCodeGenerateRequestDto qrCodeGenerateDto) {
        return null;
    }
}
