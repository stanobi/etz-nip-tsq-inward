package com.etz.nipinward.service;

import com.etz.http.etc.Card;
import com.etz.http.etc.HttpHost;
import com.etz.http.etc.TransCode;
import com.etz.http.etc.VCardRequest;
import com.etz.http.etc.XProcessor;
import com.etz.http.etc.XRequest;
import com.etz.http.etc.XResponse;
import com.etz.nipinward.config.AppConfig;
import com.etz.nipinward.dto.autoswitch.AutoswitchResponseDTO;
import com.etz.nipinward.model.NIBSSMandateAdviceRequest;
import com.etz.nipinward.model.NIBSSRequestLog;
import com.etz.nipinward.model.NIBSSTransactionLog;
import com.etz.nipinward.repo.NIBSSMandateAdviceRequestRepo;
import com.etz.nipinward.repo.NIBSSRequestLogRepo;
import com.etz.nipinward.repo.NIBSSTransactionLogRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Slf4j
@Service
@RequiredArgsConstructor
public class CoreBankingService {

  private final AppConfig appConfig;
  private final NIBSSRequestLogRepo nibssRequestLogRepo;
  private final NIBSSMandateAdviceRequestRepo nibssMandateAdviceRequestRepo;
  private final NIBSSTransactionLogRepo nibssTransactionLogRepo;

  private VCardRequest generateRequest(String accountNumber) {
    VCardRequest request = new VCardRequest();
    request.setOtherReference("ETZ-NIPS" + generateRandom(10));
    request.setMobileNumber(accountNumber);
    request.setRequestType(TransCode.CARDQUERY);
    request.setSchemeCode(appConfig.getAutoswitch().getSchemeCode());
    return request;
  }

  private HttpHost getHost() {
    HttpHost host = new HttpHost();
    host.setServerAddress(appConfig.getAutoswitch().getIp());
    host.setPort(Integer.parseInt(appConfig.getAutoswitch().getPort()));
    host.setSecureKey(appConfig.getAutoswitch().getKey());
    return host;
  }

  private long generateRandom(int length) {
    Random random = new Random();
    char[] digits = new char[length];
    digits[0] = ((char)(random.nextInt(9) + 49));
    for (int i = 1; i < length; i++) {
      digits[i] = ((char)(random.nextInt(10) + 48));
    }
    return Long.parseLong(new String(digits));
  }
  
  public AutoswitchResponseDTO getCardDetails(String accountNumber) {
    XProcessor processor = new XProcessor();
    HttpHost host = getHost();
    VCardRequest request = generateRequest(accountNumber);

    try {
      String desc = null;
      VCardRequest response = processor.process(host, request);
      if(response == null) {
        return generateResponse("SEVERE_ERROR", "getCardDetails - AUTOCLIENT Error");
      }
      log.info("getCardDetails - Response code: {}", response.getResponse());
      if (response.getResponse() == 0) {
        log.info("getCard CardNumber: {}",response.getCardNumber());
        log.info("getCard AccountNumber: {}",response.getAccountNumber());
        log.info("getCard Status: {}",response.getStatus());
        desc = response.getFirstName().trim() + " " + response.getLastName().split("~")[0].trim() + "~" + response.getCardNumber().trim() + "~" + response.getCardBalance();
        return generateResponse("SUCCESS", desc);
      }
      desc = "Fetching Mobile Money details Failed:: ResponseCode: " + response.getResponse();
      return generateResponse("ERROR", desc);
    } catch (Exception ex) {
      log.error("Unable to getCardDetail from Autoswitch ", ex);
      return generateResponse("SEVERE_ERROR", "getCardDetails - AUTOCLIENT Error: " + ex.getMessage());
    }
  }

  private AutoswitchResponseDTO generateResponse(String response, String responseDetail) {
    return new AutoswitchResponseDTO(response, responseDetail);
  }
  
  public void saveRequest(String sessionID, String source, String method,
                          LocalDateTime date, String channel, String destCode,
                          String responseCode, String ip) {
    try {
      NIBSSRequestLog nibssRequestLog = new NIBSSRequestLog();
      nibssRequestLog.setSessionId(sessionID);
      nibssRequestLog.setSourceCode(source);
      nibssRequestLog.setMethodCall(method);
      nibssRequestLog.setTransactionDate(date);
      nibssRequestLog.setChannel(channel);
      nibssRequestLog.setDestinationCode(destCode);
      nibssRequestLog.setResponseCode(responseCode);
      nibssRequestLog.setRemoteIp(ip);
      nibssRequestLogRepo.save(nibssRequestLog);
    } catch (Exception e) {
      log.error("unable to save request to database ", e);
    }
  }
  
  public String mandateExist(String mandateRefNo, String debitAccNo) {
    NIBSSMandateAdviceRequest mandateAdviceRequest = nibssMandateAdviceRequestRepo.findByRefAndDebitAccount(mandateRefNo, debitAccNo);
    if(mandateAdviceRequest == null || mandateAdviceRequest.getAmount() == null) {
      return "NOT FOUND";
    }
    return mandateAdviceRequest.getAmount().toString();
  }
  
  public String getResponseCode(String method, String sessionID) {
    return nibssRequestLogRepo.getResponseCodeByMethodCallAndSessionId(method, sessionID)
            .orElse("");
  }

  private Card getCard(String pin, String num, String expDate) {
    Card card = new Card();
    card.setCardPin(pin);
    card.setCardNumber(num);
    card.setCardExpiration(expDate);
    return card;
  }

  private XRequest generateXRequest(String operation, String num, String merchant, String pin, String expDate,
                                    String ref, String tnxDesc, double fee, double amount) {
    XRequest request = new XRequest();
    request.setCard(getCard(pin, num, expDate));
    if (operation.equalsIgnoreCase("BalanceEnquiry")) {
      request.setTransCode(TransCode.BALANCE);
      request.setChannelId(appConfig.getAutoswitch().getBeChannelId());
    } else if (operation.startsWith("FundTransfer")) {
      setTransCode(operation, request);
      request.setMerchantCode(merchant);
      request.setChannelId(appConfig.getAutoswitch().getFtChannelId());
      request.setTransAmount(amount);
    }
    request.setDescription(tnxDesc);
    request.setFee(fee);
    request.setReference(ref);
    return request;
  }

  private void setTransCode(String operation, XRequest request) {
    if (operation.equalsIgnoreCase("FundTransfer")) {
      request.setTransCode(TransCode.NOTIFICATION);
    } else if (operation.equalsIgnoreCase("FundTransferAdvice")) {
      request.setTransCode(TransCode.REVERSAL);
    }
  }

  private AutoswitchResponseDTO generateResponse(String operation, String responseCode, XResponse response) {
    if (operation.equalsIgnoreCase("BalanceEnquiry")) {
      log.info("{} - Balance: {}",operation,response.getBalance());
      return generateResponse(responseCode, String.valueOf(response.getBalance()));
    }
    log.info(operation + " ResponseCode: " + response.getResponse());
    return generateResponse(responseCode, operation + " ResponseCode: " + response.getResponse());
  }

  private AutoswitchResponseDTO generateResponseByResponseCode(String operation, XResponse response) {
    log.info("{} - Response code: {}",operation ,response.getResponse());
    if (response.getResponse() == 0) {
      return generateResponse(operation, "SUCCESS", response);
    } else if ((response.getResponse() == -1) || (response.getResponse() == 32)
            || (response.getResponse() == 31)) {
      return generateResponse(operation, "ERRORTIMEOUT", response);
    }
    String desc = operation + " ResponseCode: " + response.getResponse();
    log.error("FAILED:: {}", desc);
    return generateResponse("ERROR", desc);
  }

  public AutoswitchResponseDTO autoclientTnx(String op, String num, String merchant, String pin, String expDate,
                              String ref, String tnxDesc, double fee, double amount) {

    logAutoclientTnxRequest(op, num, merchant, pin, expDate, ref, tnxDesc, fee, amount);
    XProcessor processor = new XProcessor();
    String desc = "";
    HttpHost host = getHost();
    XRequest request = generateXRequest(op, num, merchant, pin, expDate, ref, tnxDesc, fee, amount);

    try {
      XResponse res = processor.process(host, request);
      if (res == null) {
        return generateResponse("SEVERE_ERROR", "autoclientTnx - AUTO-CLIENT ERROR");
      }
      log.info("{} - Response code: {}",op ,res.getResponse());
      return generateResponseByResponseCode(op, res);
    } catch (Exception e) {
      desc = "autoclientTnx - AUTOCLIENT ERROR: " + e.getMessage();
      log.error(desc);
      return generateResponse("SEVERE_ERROR", desc);
    }
  }

  private void logAutoclientTnxRequest(String op, String num, String merchant, String pin, String expDate,
                                  String ref, String tnxDesc, double fee, double amount) {
    log.info("OP: {}", op);
    log.info("MERCHANT: {}", merchant);
    log.info("NUM: {}", num);
    log.info("PIN: {}", pin);
    log.info("EXPDATE: {}", expDate);
    log.info("REF: {}", ref);
    log.info("TNXDESC: {}", tnxDesc);
    log.info("FEE: {}", fee);
    log.info("AMOUNT: {}", amount);
  }
  
  public void insertTnx(String sessionId, String ref, String channel, String amount,
                        String source, String dest, String respCode, String eTzRespCode, String payRef,
                        String beneficiaryAccNo, String debitAccNo, String mandateRef, String tnxType,
                        String transNarration, String ip) {
    NIBSSTransactionLog nibssTransactionLog = new NIBSSTransactionLog();
    nibssTransactionLog.setSessionId(sessionId);
    nibssTransactionLog.setMandateReference(ref);
    nibssTransactionLog.setChannel(channel);
    nibssTransactionLog.setAmount(amount);
    nibssTransactionLog.setSourceCode(source);
    nibssTransactionLog.setDestinationCode(dest);
    nibssTransactionLog.setResponseCode(respCode);
    nibssTransactionLog.setSourceResponseCode(eTzRespCode);
    nibssTransactionLog.setTransactionDate(LocalDateTime.now());
    nibssTransactionLog.setPaymentReference(payRef);
    nibssTransactionLog.setBeneficiaryAccountNumber(beneficiaryAccNo);
    nibssTransactionLog.setDebitAccountNumber(debitAccNo);
    nibssTransactionLog.setMandateReference(mandateRef);
    nibssTransactionLog.setTransType(tnxType);
    nibssTransactionLog.setTransNarration(transNarration);
    nibssTransactionLog.setRemoteIp(ip);
    nibssTransactionLogRepo.save(nibssTransactionLog);

  }
  
  public String getUniqueRef(String sessionId) {
    return nibssTransactionLogRepo.getUniqueRef(sessionId).orElse("");
  }
  
  public void updateTnx(String sessionId, String respCode, String eTzRespCode) {
    Optional<NIBSSTransactionLog> transactionLogOptional = nibssTransactionLogRepo.getNIBSSTransactionLogBySessionId(sessionId);
    if (transactionLogOptional.isPresent()) {
      NIBSSTransactionLog nibssTransactionLog = transactionLogOptional.get();
      nibssTransactionLog.setResponseCode(respCode);
      nibssTransactionLog.setSourceResponseCode(eTzRespCode);
      nibssTransactionLogRepo.save(nibssTransactionLog);
    }
  }

  public Optional<NIBSSTransactionLog> findTransactionBySessionId(String sessionId) {
    try {
      return nibssTransactionLogRepo.getNIBSSTransactionLogBySessionId(sessionId);
    } catch (Exception e) {
      log.error("Unable to fetch transactions ", e);
      return Optional.empty();
    }
  }

  public void updateTransactionBySessionId(String sessionId, String responseCode, String sourceResponseCode) {
    try {
      Optional<NIBSSTransactionLog> transactionLogOptional = nibssTransactionLogRepo.getNIBSSTransactionLogBySessionId(sessionId);
      if (transactionLogOptional.isPresent()) {
        NIBSSTransactionLog nibssTransactionLog = transactionLogOptional.get();
        nibssTransactionLog.setResponseCode(responseCode);
        nibssTransactionLog.setSourceResponseCode(sourceResponseCode);
        nibssTransactionLog.setCredited("00".equals(responseCode));
        nibssTransactionLog.setRetrialCount(nibssTransactionLog.getRetrialCount()+1);
        nibssTransactionLogRepo.save(nibssTransactionLog);
      }
    } catch (Exception e) {
      log.error("Unable to fetch/save transactions ", e);
    }
  }

  public List<NIBSSTransactionLog> findAllPendingCreditedTransaction() {
    Page<NIBSSTransactionLog> nibssTransactionLogs = nibssTransactionLogRepo.findAllLessThanMaxRetrialCount(appConfig.getMaxRetrialCount(), PageRequest.of(0, appConfig.getMaxTransactionForRetrialAtATime()));
    return nibssTransactionLogs.getContent();
  }

  public void performFTCredit(NIBSSTransactionLog transactionLog) {

    AutoswitchResponseDTO responseDTO = getCardDetails("234"+transactionLog.getBeneficiaryAccountNumber());
    String cardNum = responseDTO.getResponseDetails().split("~")[1];
    AutoswitchResponseDTO response = autoclientTnx("FundTransfer", appConfig.getNip().getNipCardNo(), cardNum,
            appConfig.getNip().getCardPin(), appConfig.getNip().getCardExpiryDate(), transactionLog.getPaymentReference(),
            transactionLog.getTransNarration(), 0.0D, Double.parseDouble(transactionLog.getAmount()));
    String rspCode = response.getResponse();
    if (rspCode.equalsIgnoreCase("SUCCESS")) {
      updateTransactionBySessionId(transactionLog.getSessionId(), "00", response.getResponseDetails());
    } else if (rspCode.equalsIgnoreCase("ERROR")) {
      updateTransactionBySessionId(transactionLog.getSessionId(), "96", response.getResponseDetails().split(":")[1]);
    } else if (rspCode.equalsIgnoreCase("ERRORTIMEOUT")) {
      updateTransactionBySessionId(transactionLog.getSessionId(), "00", response.getResponseDetails().split(":")[1]);
    } else if (rspCode.equalsIgnoreCase("SEVERE_ERROR")) {
      updateTransactionBySessionId(transactionLog.getSessionId(), "91", response.getResponseDetails());
    }

  }
}
