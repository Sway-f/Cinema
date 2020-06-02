package com.lanfang.cinema.system.Service;

import com.lanfang.cinema.system.Domain.Record;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface RecordService {
    Record saveRecord(Record record);
     void deletRecord(Long id);
    Page<Record> getRecord(Long user_id, Long f_id, Pageable pageable);
    Page<Record> getAll(Long user_id, Pageable pageable);
}
