package com.etz.nipinward.repo;

import com.etz.nipinward.model.NIBSSRequestLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface NIBSSRequestLogRepo extends JpaRepository<NIBSSRequestLog, Long> {

    @Query("select r.responseCode from NIBSSRequestLog r where r.methodCall = ?1 and r.sessionId = ?2 ")
    Optional<String> getResponseCodeByMethodCallAndSessionId(String methodCall, String sessionId);
}
