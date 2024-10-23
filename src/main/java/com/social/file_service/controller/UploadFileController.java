package com.social.file_service.controller;

import com.social.file_service.command.FileCreateCmd;
import com.social.file_service.dto.response.FileResponse;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.annotation.RequestScope;
import org.springframework.web.multipart.MultipartFile;

@RequestScope
@RestController
@RequestMapping("/file")
public class UploadFileController {

    private final FileCreateCmd command;

    public UploadFileController(FileCreateCmd command) {
        this.command = command;
    }

    @Operation(summary = "Upload a file",
            description = "Upload a file using multipart form data.")
    @PostMapping(consumes = "multipart/form-data")
    public ResponseEntity<FileResponse> uploadFile(@RequestPart(value = "multipartFile") MultipartFile multipartFile) {
        this.command.setMultipartFile(multipartFile);
        this.command.execute();
        return ResponseEntity.ok(this.command.getResponse());
    }
}
