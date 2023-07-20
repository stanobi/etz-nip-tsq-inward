package com.etz.nipinward.repo;

import com.etz.nipinward.model.NIBSSMandateAdviceRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface NIBSSMandateAdviceRequestRepo extends JpaRepository<NIBSSMandateAdviceRequest, Long> {

    @Query("select m from NIBSSMandateAdviceRequest m where m.ref = ?1 and m.debitAccount = ?2 ")
    NIBSSMandateAdviceRequest findByRefAndDebitAccount(String ref, String debitAccount);

}
