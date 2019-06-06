package com.example.fileupload.IngestionRepository;

import com.example.fileupload.Entity.FileRecord;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface FileRecordRepository extends MongoRepository<FileRecord, String> {
    List<FileRecord> getBystatus(String received);
}
