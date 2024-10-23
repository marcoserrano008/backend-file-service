package com.social.file_service.command;

import com.social.file_service.entity.File;
import com.social.file_service.entity.FileData;
import com.social.file_service.exception.FileNotFoundException;
import com.social.file_service.repository.FileDataRepository;
import com.social.file_service.repository.FileRepository;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class FileDataReadCmd extends AbstractCommand {

    @Setter
    private String fileId;

    @Getter
    private byte[] bytes;

    @Getter
    private File file;

    private final FileRepository fileRepository;

    private final FileDataRepository fileDataRepository;

    public FileDataReadCmd(FileRepository fileRepository, FileDataRepository fileDataRepository) {
        this.fileRepository = fileRepository;
        this.fileDataRepository = fileDataRepository;
    }

    @Override
    protected void onExecute() {
        this.file = findFileById(this.fileId);
        FileData fileData = findFileDataByFileId(this.fileId);
        if (Objects.nonNull(fileData)) {
            this.bytes = fileData.getValue();
        } else {
            this.bytes = new byte[0];
        }
    }

    private File findFileById(String fileId) {
        return this.fileRepository.findById(fileId)
                .orElseThrow(() -> new FileNotFoundException("File does not exist"));
    }

    private FileData findFileDataByFileId(String fileId) {
        return fileDataRepository.findByFileId(fileId).orElse(null);
    }
}
