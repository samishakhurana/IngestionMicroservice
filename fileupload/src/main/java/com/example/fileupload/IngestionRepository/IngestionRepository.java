package com.example.fileupload.IngestionRepository;

import com.example.fileupload.Entity.QuestionEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IngestionRepository extends MongoRepository<QuestionEntity, Integer> {

    Page<QuestionEntity> findBydifficultyLevel(String difficultyLevel,Pageable pageable);

    Page<QuestionEntity> findByquesType(String quesType,Pageable pageable);

    QuestionEntity findByquesId(int quesId);

    Page<QuestionEntity> findAll(Pageable pageable);

    Page<QuestionEntity> findByscreenedFlag(boolean screenedFlag , Pageable pageable );

    QuestionEntity findByuniqueKey(String uniqueKey);

    Page<QuestionEntity> findByCategoryAndQuesType(String category ,String quesType, Pageable pageable );

//    Page<QuestionEntity> findByCategoryAndByQuesType(String category, String quesType, PageRequest pageRequest);
}



