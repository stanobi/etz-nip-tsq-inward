package com.etz.nipinward.service;

import com.etz.nipinward.config.AppConfig;
import com.etz.nipinward.dto.nibss.TSQuerySingleRequest;
import com.etz.nipinward.dto.nibss.TSQuerySingleResponse;
import com.etz.nipinward.exception.ExpectationFailedException;
import com.etz.nipinward.exception.NotFoundException;
import com.etz.nipinward.model.NIBSSTransactionLog;
import com.etz.nipinward.soapws.nibss.tsq.NIPTSQInterface_Service;
import com.etz.nipinward.util.CommonUtil;
import com.sun.xml.ws.fault.ServerSOAPFaultException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.net.MalformedURLException;
import java.net.URL;

@Slf4j
@Service
@RequiredArgsConstructor
public class FTCreditAfterEffect {

    private final NIPEncryptionService nipEncryptionService;
    private final CoreBankingService coreBankingService;
    private final AppConfig appConfig;


    public void confirmTSQAndPerformFTCredit(NIBSSTransactionLog transactionLog) {
        //confirm from TSQ that all is fine
        try {
            if (isTSQPresentAndSuccessful(transactionLog.getSessionId())) {
                //then actually credit account;
                coreBankingService.performFTCredit(transactionLog);
            }
        } catch (ExpectationFailedException | NotFoundException e) {
            log.error("Unable to perform TSQ", e);
        }
    }

    private boolean isTSQPresentAndSuccessful(String sessionId) throws ExpectationFailedException, NotFoundException {
        TSQuerySingleRequest asTSQuerySingleRequest = getTsQuerySingleRequest(sessionId);
        String xmlRequestString = CommonUtil.convertXMLObjectToXMLString(asTSQuerySingleRequest, TSQuerySingleRequest.class);
        String encryptedXmlRequestString = nipEncryptionService.encryptForTSQ(xmlRequestString);
        String txnStatusQuerySingleItemResponse = null;
        try {
            NIPTSQInterface_Service nipTsqInterfaceService = new NIPTSQInterface_Service(new URL(appConfig.getNip().getTsqUrl()));
            txnStatusQuerySingleItemResponse = nipTsqInterfaceService.getNIPTQSInterfacePort().txnstatusquerysingleitem(encryptedXmlRequestString);
        } catch (ServerSOAPFaultException | MalformedURLException e) {
            log.error("Exception Occurred at NIP Server while trying to processTransactionStatusQuery : ", e);
            throw new ExpectationFailedException("Unable to process request due to Server Error from NIP");
        }
        String decryptedXmlResponseString = nipEncryptionService.decryptForTSQ(txnStatusQuerySingleItemResponse);
        TSQuerySingleResponse tsQuerySingleResponse = CommonUtil.convertXMLStringToXMLObject(decryptedXmlResponseString, TSQuerySingleResponse.class);
        return tsQuerySingleResponse != null && "00".equals(tsQuerySingleResponse.getResponseCode());
    }

    private TSQuerySingleRequest getTsQuerySingleRequest(String sessionId) {
        TSQuerySingleRequest tsQuerySingleRequest = new TSQuerySingleRequest();
        tsQuerySingleRequest.setChannelCode("1");
        tsQuerySingleRequest.setSessionID(sessionId);
        tsQuerySingleRequest.setSourceInstitutionCode(appConfig.getNip().getBankCode());
        return tsQuerySingleRequest;
    }
}
