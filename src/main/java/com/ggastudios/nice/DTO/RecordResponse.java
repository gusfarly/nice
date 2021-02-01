package com.ggastudios.nice.DTO;

import com.ggastudios.nice.repository.Record;
import lombok.Data;

@Data
public class RecordResponse {

    private String name;
    private int score;
    private int position;
    private String idUser;
    private  int level;

    public RecordResponse(Record record) {
        this.name = record.getName();
        this.score = record.getScore();
        this.idUser = record.getIdUser();
        this.level = record.getLevel();
    }

}
