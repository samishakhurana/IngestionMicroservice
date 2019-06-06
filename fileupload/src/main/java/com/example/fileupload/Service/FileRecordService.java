package com.example.fileupload.Service;

import com.example.fileupload.Entity.FileRecord;
import com.example.fileupload.Entity.Response;
import com.example.fileupload.IngestionRepository.FileRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FileRecordService {

    @Autowired
    public FileRecordRepository fileRecordRepository;

    public Response getAllFileRecords() {

        Response resp=new Response();
        resp.setStatus(200);
        resp.setMessage("Success");
        resp.setError(null);

        List<FileRecord> result=fileRecordRepository.findAll();

        resp.setData(result);

        return resp;
    }
}
