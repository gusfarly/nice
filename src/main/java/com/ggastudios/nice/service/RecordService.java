package com.ggastudios.nice.service;

import com.ggastudios.nice.repository.Record;
import com.ggastudios.nice.DTO.RecordResponse;
import com.ggastudios.nice.repository.RecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
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
        record.createDateUpdate();
        recordRepository.save(record);
        return new RecordResponse(recordRepository.save(record));
    }

    public List<RecordResponse> getTopTen(String idApplication, int level){

        List<Record> recordList = recordRepository.findAllByApplicationAndLevelOrderByScoreDesc(idApplication,level,PageRequest.of(0,10));
        List<RecordResponse> listRecordResponse = listRecordToListRecordResponse(recordList);
        int posicion = 0;
        for (RecordResponse recordResponse : listRecordResponse){
            recordResponse.setPosition(++posicion);
        }
        return listRecordResponse;
    }

    public List<RecordResponse> getPreviousTen(String idApplication, int level,int score){

        List<Record> recordList = recordRepository.findAllByApplicationAndLevelAndScoreGreaterThanEqualOrderByScoreDesc(
                idApplication,level,score,PageRequest.of(0,10));
        if (recordList.size() < 10){
            recordList.addAll(recordRepository.findAllByApplicationAndLevelAndScoreLessThanOrderByScoreDesc(
                    idApplication,level,score,PageRequest.of(0,10 - recordList.size())));
        }
        int position = 0;
        if (recordList.size() > 0) {
            position = recordRepository.countByApplicationAndLevelAndScoreGreaterThanEqualOrderByDateUpdateDesc
                    (idApplication, level, recordList.get(0).getScore());
        }
        if (position == 0) position = 1;
        List<RecordResponse> responseRecord = listRecordToListRecordResponse(recordList);
        for (RecordResponse r : responseRecord){
            r.setPosition(position++);
        }
        return responseRecord;
    }

    private RecordResponse update(Record record){
        Record recordToUpdate = recordRepository.findByIdUserAndApplicationAndLevel(record.getIdUser(), record.getApplication(), record.getLevel());
        if (record.getScore() > recordToUpdate.getScore()) {
            recordToUpdate.setScore(record.getScore());
        }
        recordToUpdate.setName(record.getName());
        recordToUpdate.createDateUpdate();
        return new RecordResponse(recordRepository.save(recordToUpdate));
    }

    private List<RecordResponse> listRecordToListRecordResponse(List<Record> recordList){
        List<RecordResponse> recordResponses = new ArrayList<>();
        for (Record record : recordList){
            recordResponses.add(new RecordResponse(record));
        }
        return recordResponses;
    }

}
