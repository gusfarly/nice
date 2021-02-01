package com.ggastudios.nice.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface RecordRepository  extends MongoRepository<Record,String>{

    int countByIdUserAndApplicationAndLevel(String idUser,String idApplication,int level);

    Record findByIdUserAndApplicationAndLevel(String idUser, String application, int level);

    List<Record> findAllByApplicationAndLevelOrderByScoreDesc(String application, int level, Pageable pageable);

    List<Record> findAllByApplicationAndLevelAndScoreGreaterThanEqualOrderByScoreDesc(String idApplication,int level,
            int Score,Pageable pageable);

    List<Record> findAllByApplicationAndLevelAndScoreLessThanOrderByScoreDesc(String idApplication,int level,int
            Score, Pageable pageable);

    int countByApplicationAndLevelAndScoreGreaterThanEqualOrderByDateUpdateDesc(String idApplication, int level,int
            Score);

}
