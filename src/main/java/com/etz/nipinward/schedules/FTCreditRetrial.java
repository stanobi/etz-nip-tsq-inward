package com.etz.nipinward.schedules;

import com.etz.nipinward.model.NIBSSTransactionLog;
import com.etz.nipinward.service.CoreBankingService;
import com.etz.nipinward.service.FTCreditAfterEffect;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class FTCreditRetrial {

    private final CoreBankingService coreBankingService;
    private final FTCreditAfterEffect ftCreditAfterEffect;

    @Scheduled(fixedDelay = 60000) //1min delay
    public void processRetrial() {

        //fetch records by creditState(false) and Retrial Count (>0)
        List<NIBSSTransactionLog> allPendingCreditedTransaction = coreBankingService.findAllPendingCreditedTransaction();

        for (NIBSSTransactionLog nibssTransactionLog : allPendingCreditedTransaction) {
            //perform TSQ
            //credit account
            ftCreditAfterEffect.confirmTSQAndPerformFTCredit(nibssTransactionLog);
        }
    }

}
