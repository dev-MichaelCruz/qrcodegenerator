package com.michaelcruz.qrcodegenerator.controller;

import com.google.zxing.WriterException;
import com.michaelcruz.qrcodegenerator.dto.QrCodeGenerateRequestDto;
import com.michaelcruz.qrcodegenerator.dto.QrCodeGenerateResponseDto;
import com.michaelcruz.qrcodegenerator.service.QrCodeGeneratorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/qrcode")
public class QrCodeController {

    private final QrCodeGeneratorService qrCodeGeneratorService;
    public QrCodeController(QrCodeGeneratorService qrCodeGeneratorService) {
        this.qrCodeGeneratorService = qrCodeGeneratorService;
    }

    @PostMapping
    public ResponseEntity<QrCodeGenerateResponseDto> generate(
            @RequestBody QrCodeGenerateRequestDto qrCodeGenerateDto) throws IOException, WriterException {
        try {
            QrCodeGenerateResponseDto response =
                    this.qrCodeGeneratorService.generateAndUploadQrCode(qrCodeGenerateDto.text());

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }
}