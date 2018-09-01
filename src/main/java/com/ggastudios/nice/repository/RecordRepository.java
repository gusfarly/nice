package com.ggastudios.nice.repository;

import com.ggastudios.nice.DTO.Record;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface RecordRepository  extends MongoRepository<Record,String>{

    int countByIdUserAndApplicationAndLevel(String idUser,String idApplication,int level);

    Record findByIdUserAndApplicationAndLevel(String idUser, String application, int level);

    List<Record> findAllByApplicationAndLevelOrderByScoreDesc(String application, int level, Pageable pageable);

    List<Record> findAllByApplicationAndLevelAndScoreLessThanEqualOrderByScoreDesc(String idApplication,int level,int Score,Pageable pageable);

    int countByApplicationAndLevelAndScoreLessThanEqual(String idApplication, int level,int Score);

}
