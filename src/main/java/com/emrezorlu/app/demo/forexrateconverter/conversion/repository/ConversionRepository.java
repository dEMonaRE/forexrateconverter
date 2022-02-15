package com.emrezorlu.app.demo.forexrateconverter.conversion.repository;

import java.time.LocalDateTime;
import java.util.Date;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.emrezorlu.app.demo.forexrateconverter.conversion.entity.ConversionEntity;

@Repository
public interface ConversionRepository extends MongoRepository<ConversionEntity, String> {

	Page<ConversionEntity> findAllByLocalDateTimeBetween(LocalDateTime from, LocalDateTime to, PageRequest pageOf);

	Page<ConversionEntity> findAllByDate(Date date, PageRequest pageOf);
}
