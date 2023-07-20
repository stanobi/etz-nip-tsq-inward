package com.etz.nipinward.events;

import com.etz.nipinward.model.NIBSSTransactionLog;
import com.etz.nipinward.service.CoreBankingService;
import com.etz.nipinward.service.FTCreditAfterEffect;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Slf4j
@Component
@RequiredArgsConstructor
public class FTCreditListener {

    private final CoreBankingService coreBankingService;
    private final FTCreditAfterEffect ftCreditAfterEffect;

    @KafkaListener(topics = "${app.config.topicName}")
    public void processFTCredit(String sessionId){

        //once the sessionId is gotten
        log.info("Received sessionId from the queue : {}", sessionId);

        //get full data from database
        Optional<NIBSSTransactionLog> transactionLogOptional = coreBankingService.findTransactionBySessionId(sessionId);
        if (!transactionLogOptional.isPresent()) {
            log.info("no transaction found with sessionId : {}", sessionId);
            return;
        }

        //if it has not being credited and retrial is ,
        NIBSSTransactionLog nibssTransactionLog = transactionLogOptional.get();
        if (nibssTransactionLog.isCredited()) {
            log.info("SessionId : {} is already credited", sessionId);
            return;
        }

        //then perform TSQ,
        //if TSQ is 00 then credit account with a prefix(234)
        ftCreditAfterEffect.confirmTSQAndPerformFTCredit(nibssTransactionLog);

    }

}
