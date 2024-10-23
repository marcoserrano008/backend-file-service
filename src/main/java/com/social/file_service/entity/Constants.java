package com.social.file_service.entity;

public class Constants {

    private Constants() {
        throw new RuntimeException("This is a utility class and cannot be instantiated");
    }

    public static class FileDocument {

        public static final String COLLECTION = "file";

        public static final class MimeType {

            public static final String FIELD = "mime_type";
        }

        public static final class Name {

            public static final String FIELD = "name";
        }

        public static final class Size {

            public static final String FIELD = "size";
        }
    }

    public static class FileDataDocument {

        public static final String COLLECTION = "file_data";

        public static final class Value {

            public static final String FIELD = "value";
        }

        public static final class File {

            public static final String FIELD = "file";
        }
    }
}