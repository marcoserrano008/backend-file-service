package com.social.file_service.controller;

import com.social.file_service.command.FileCreateCmd;
import com.social.file_service.command.FileDataReadCmd;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.annotation.RequestScope;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

@RequestScope
@RestController
@RequestMapping("/file")
public class DownloadFileController {

    private final FileDataReadCmd command;
    private final FileCreateCmd fileCreateCmd;

    public DownloadFileController(FileDataReadCmd command, FileCreateCmd fileCreateCmd) {
        this.command = command;
        this.fileCreateCmd = fileCreateCmd;
    }

    @Operation(summary = "Download a file by ID",
            description = "Download a file by providing its ID.")
    @GetMapping("/{id}/download")
    public ResponseEntity<InputStreamResource> downloadFile(@PathVariable("id") String fileId) {
        command.setFileId(fileId);
        command.execute();
        InputStream content = new ByteArrayInputStream(command.getBytes(), 0, command.getBytes().length);
        String fileName = command.getFile().getName();
        return ResponseEntity
                .ok()
                .header(
                        HttpHeaders.CONTENT_DISPOSITION,
                        String.format("attachment; filename=\"%s\"", fileName)
                )
                .contentType(MediaType.parseMediaType(command.getFile().getMimeType()))
                .body(new InputStreamResource(content));
    }
}
