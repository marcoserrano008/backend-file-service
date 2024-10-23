package com.social.file_service.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import com.social.file_service.entity.Constants.FileDocument;

@Getter
@Setter
@Document(collection = FileDocument.COLLECTION)
public class File {

    @Id
    private String id;

    @Field(FileDocument.MimeType.FIELD)
    private String mimeType;

    @Field(FileDocument.Name.FIELD)
    private String name;

    @Field(FileDocument.Size.FIELD)
    private Long size;
}
