package com.etz.nipinward.repo;

import com.etz.nipinward.model.NIBSSTransactionLog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface NIBSSTransactionLogRepo extends JpaRepository<NIBSSTransactionLog, Long> {

    @Query("select n.uniqueTransId from NIBSSTransactionLog n where n.sessionId = ?1 ")
    Optional<String> getUniqueRef(String sessionId);

    @Query("select n from NIBSSTransactionLog n where n.sessionId = ?1 ")
    Optional<NIBSSTransactionLog> getNIBSSTransactionLogBySessionId(String sessionId);

    @Query("select n from NIBSSTransactionLog n where n.responseCode = '00' and n.credited = false and n.retrialCount > 0 and n.retrialCount < ?1 order by n.transactionDate desc ")
    Page<NIBSSTransactionLog> findAllLessThanMaxRetrialCount(int maxRetrialCount, Pageable pageable);
}
