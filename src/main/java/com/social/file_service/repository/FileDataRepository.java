package com.social.file_service.repository;

import com.social.file_service.entity.FileData;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface FileDataRepository extends MongoRepository<FileData, String> {

    Optional<FileData> findByFileId(String fileId);
}

