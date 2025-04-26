package com.michaelcruz.qrcodegenerator.service;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

import com.michaelcruz.qrcodegenerator.dto.QrCodeGenerateResponseDto;
import com.michaelcruz.qrcodegenerator.ports.StoragePort;

import org.springframework.stereotype.Service;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.UUID;

@Service
public class QrCodeGeneratorService {

    private final StoragePort storagePort;

    public QrCodeGeneratorService(StoragePort storagePort) {
        this.storagePort = storagePort;
    }

    public QrCodeGenerateResponseDto generateAndUploadQrCode(String text) throws WriterException, IOException {
        QRCodeWriter qrCodeWriter = new QRCodeWriter();

        BitMatrix bitMatrix = qrCodeWriter.encode(
                text, BarcodeFormat.QR_CODE, 200, 200);

        ByteArrayOutputStream pngOutpuStream = new ByteArrayOutputStream();
        MatrixToImageWriter.writeToStream(bitMatrix, "PNG", pngOutpuStream);

        byte[] pngQrCodeData = pngOutpuStream.toByteArray();

        String url = storagePort.uploadFile(
                pngQrCodeData, UUID.randomUUID().toString(), "image/png");

        return new QrCodeGenerateResponseDto(url);
    }
}
