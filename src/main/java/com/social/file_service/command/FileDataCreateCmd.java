package com.social.file_service.command;

import com.social.file_service.entity.File;
import com.social.file_service.entity.FileData;
import com.social.file_service.repository.FileDataRepository;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Service
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class FileDataCreateCmd extends AbstractCommand {

    @Setter
    private File file;

    @Setter
    private byte[] bytes;

    @Getter
    private FileData response;

    private final FileDataRepository fileDataRepository;

    public FileDataCreateCmd(FileDataRepository fileDataRepository) {
        this.fileDataRepository = fileDataRepository;
    }

    @Override
    protected void onExecute() {
        this.response = new FileData();
        this.response.setFile(this.file);
        this.response.setValue(this.bytes);
        fileDataRepository.save(this.response);
    }
}
