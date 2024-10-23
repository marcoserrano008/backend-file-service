package com.social.file_service.dto.builder;

import com.social.file_service.dto.response.FileResponse;
import com.social.file_service.entity.File;
import org.springframework.beans.BeanUtils;

public class FileResponseBuilder {

    private final FileResponse instance;

    private FileResponseBuilder() {
        instance = new FileResponse();
    }

    public static FileResponseBuilder getInstance(File request) {
        return (new FileResponseBuilder()).setFile(request);
    }

    public FileResponse build() {
        return instance;
    }

    private FileResponseBuilder setFile(File request) {
        BeanUtils.copyProperties(request, instance);
        return this;
    }
}
