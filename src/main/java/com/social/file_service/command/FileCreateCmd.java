package com.social.file_service.command;

import com.social.file_service.dto.builder.FileResponseBuilder;
import com.social.file_service.dto.response.FileResponse;
import com.social.file_service.entity.File;
import com.social.file_service.exception.FileSaveException;
import com.social.file_service.repository.FileRepository;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;

@Service
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class FileCreateCmd extends AbstractCommand {

    @Setter
    private MultipartFile multipartFile;

    @Getter
    private FileResponse response;

    private final FileRepository fileRepository;

    private final FileDataCreateCmd fileDataCreateCmd;

    public FileCreateCmd(FileRepository fileRepository, FileDataCreateCmd fileDataCreateCmd) {
        this.fileRepository = fileRepository;
        this.fileDataCreateCmd = fileDataCreateCmd;
    }

    @Override
    protected void onExecute() throws FileSaveException {
        File file = buildFile(this.multipartFile);
        fileRepository.save(file);
        saveFileContent(file, this.multipartFile);
        this.response = buildResponse(file);
    }

    private File buildFile(MultipartFile multipartFile) {
        File file = new File();
        file.setName(multipartFile.getOriginalFilename());
        file.setSize(multipartFile.getSize());
        file.setMimeType(multipartFile.getContentType());
        return file;
    }

    private FileResponse buildResponse(File file) {
        FileResponse fileResponse = FileResponseBuilder.getInstance(file).build();
        String baseUrl = ServletUriComponentsBuilder.fromCurrentContextPath().build().toUriString();
        String downloadUrl = String.format("%s/file/%s/download", baseUrl, file.getId());
        fileResponse.setDownloadUrl(downloadUrl);
        return fileResponse;
    }

    private void saveFileContent(File file, MultipartFile multipartFile) {
        try {
            byte[] bytes = multipartFile.getBytes();
            fileDataCreateCmd.setFile(file);
            fileDataCreateCmd.setBytes(bytes);
            fileDataCreateCmd.execute();
        } catch (IOException e) {
            throw new FileSaveException("Error saving file content", e);
        }
    }
}
