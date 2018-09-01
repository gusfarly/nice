package com.ggastudios.nice.repository;

import com.ggastudios.nice.DTO.Record;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface RecordRepository  extends MongoRepository<Record,String>{

    int countByIdUserAndApplicationAndLevel(String idUser,int idApplication,int level);

    Record findByIdUserAndApplicationAndLevel(String idUser, int application, int level);

    List<Record> findAllByApplicationAndLevelOrderByScoreDesc(int application, int level, Pageable pageable);

    List<Record> findAllByApplicationAndLevelAndScoreLessThanEqualOrderByScoreDesc(int idApplication,int level,int Score,Pageable pageable);

}
