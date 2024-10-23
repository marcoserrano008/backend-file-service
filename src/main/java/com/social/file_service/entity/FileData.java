package com.social.file_service.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import com.social.file_service.entity.Constants.FileDataDocument;
import org.springframework.data.mongodb.core.mapping.Field;

@Getter
@Setter
@Document(collection = FileDataDocument.COLLECTION)
public class FileData {

    @Id
    private String id;

    @Field(FileDataDocument.Value.FIELD)
    private byte[] value;

    @DBRef
    @Field(FileDataDocument.File.FIELD)
    private File file;
}
