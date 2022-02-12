package com.emrezorlu.app.demo.forexrateconverter.fxrates.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.emrezorlu.app.demo.forexrateconverter.fxrates.entity.FxRateEntity;

@Repository
public interface FxRateRepository extends MongoRepository<FxRateEntity, String> {

}
