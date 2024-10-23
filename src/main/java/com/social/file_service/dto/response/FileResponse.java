package com.social.file_service.dto.response;

import lombok.Data;

@Data
public class FileResponse {

    private String id;

    private String mimeType;

    private String name;

    private Long size;

    private String downloadUrl;
}
