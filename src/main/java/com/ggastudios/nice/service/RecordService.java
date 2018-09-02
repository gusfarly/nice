package com.ggastudios.nice.service;

import com.ggastudios.nice.DTO.Record;
import com.ggastudios.nice.DTO.RecordResponse;
import com.ggastudios.nice.repository.RecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RecordService {

    @Autowired
    private RecordRepository recordRepository;

    public RecordResponse insert(Record record) {
        int registros = recordRepository.countByIdUserAndApplicationAndLevel(record.getIdUser(), record.getApplication(), record.getLevel());
        if (registros > 0) {
            return this.update(record);
        }
        recordRepository.save(record);
        return new RecordResponse(recordRepository.save(record));
    }

    public List<RecordResponse> getTopTen(String idApplication, int level){

        List<Record> recordList = recordRepository.findAllByApplicationAndLevelOrderByScoreDesc(idApplication,level,PageRequest.of(0,10));
        return listRecordToListRecordResponse(recordList);
    }

    public List<RecordResponse> getPreviousTen( Record record){

        List<Record> recordList = recordRepository.findAllByApplicationAndLevelAndScoreLessThanEqualOrderByScoreDesc(record.getApplication(),record.getLevel(),record.getScore(),PageRequest.of(0,10));
        int position = recordRepository.countByIdUserAndApplicationAndLevel(record.getIdUser(),record.getApplication(),record.getLevel());
        List<RecordResponse> responseRecord = listRecordToListRecordResponse(recordList);
        for (RecordResponse r : responseRecord){
            r.setPosition(position++);
        }
        return listRecordToListRecordResponse(recordList);
    }

    private RecordResponse update(Record record){
        Record recordToUpdate = recordRepository.findByIdUserAndApplicationAndLevel(record.getIdUser(), record.getApplication(), record.getLevel());
        recordToUpdate.setScore(record.getScore());
        recordToUpdate.setName(record.getName());
        return new RecordResponse(recordRepository.save(record));
    }

    private List<RecordResponse> listRecordToListRecordResponse(List<Record> recordList){
        List<RecordResponse> recordResponses = new ArrayList<>();
        for (Record record : recordList){
            recordResponses.add(new RecordResponse(record));
        }
        return recordResponses;
    }

}
