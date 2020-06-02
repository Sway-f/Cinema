package com.lanfang.cinema.system.Service.Impl;

import com.lanfang.cinema.system.Dao.RecordDao;
import com.lanfang.cinema.system.Domain.Record;
import com.lanfang.cinema.system.Service.RecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Transactional
@Service
public class RecordServiceImpl implements RecordService {
    @Autowired
    RecordDao recordDao;
    @Override
    public Record saveRecord(Record record) {
        return recordDao.save(record);
    }

    @Override
    public void deletRecord(Long id) {
        recordDao.deleteById(id);
    }

    @Override
    public Page<Record> getRecord(Long user_id, Long f_id, Pageable pageable) {
        return recordDao.findByUser_idAndF_id(user_id ,f_id,f_id,user_id,pageable);
    }

    @Override
    public Page<Record> getAll(Long user_id, Pageable pageable) {
        return recordDao.findByUser_id(user_id,pageable);
    }

}
