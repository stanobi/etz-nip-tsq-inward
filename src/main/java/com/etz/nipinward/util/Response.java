package com.etz.nipinward.util;

public enum Response {

    //short name to identify error code and full message
    SUCCESSFUL("00",  "Approved or completed successfully"),
    STATUS_UNKNOWN("01", "Status unknown, please wait for settlement report"),
    INVALID_SENDER("03", "Invalid Sender"),
    DO_NOT_HONOR("05", "Do not honor"),
    DORMANT_ACCOUNT("06", "Dormant Account"),
    INVALID_ACCOUNT("07", "Invalid Account"),
    ACCOUNT_NAME_MISMATCH("08", "Account Name Mismatch"),
    REQUEST_IN_PROGRESS("09", "Request processing in progress"),
    INVALID_TRANSACTION("12","Invalid transaction"),
    INVALID_AMOUNT("13", "Invalid Amount"),
    INVALID_BATCH_NUMBER("14", "Invalid Batch Number"),
    INVALID_SESSION_OR_RECORD_ID("15", "Invalid Session or Record ID"),
    UNKNOWN_BANK_CODE("16", "Unknown Bank Code"),
    INVALID_CHANNEL("17", "Invalid Channel"),
    WRONG_METHOD_CALL("18", "Wrong Method Call"),
    NO_ACTION_TAKEN("21", "No action taken"),
    UNABLE_TO_LOCATE_RECORD("25", "Unable to locate record"),
    DUPLICATE("26", "Duplicate record"),
    FORMAT_ERROR("30", "Format error"),
    SUSPECTED_FRAUD("34", "Suspected fraud"),
    CONTACT_SENDING_BANK("35", "Contact sending bank"),
    NO_SUFFICIENT_FUNDS("51", "No sufficient funds"),
    TRANSACTION_NOT_PERMITTED_TO_SENDER("57", "Transaction not permitted to sender"),
    TRANSACTION_NOT_PERMITTED_ON_CHANNEL("58", "Transaction not permitted on channel"),
    TRANSFER_LIMIT_EXCEEDED("61", "Transfer limit Exceeded"),
    SECURITY_VIOLATION("63", "Security violation"),
    EXCEEDS_WITHDRAWAL_FREQUENCY("65", "Exceeds withdrawal frequency"),
    RESPONSE_RECEIVED_TOO_LATE("68", "Response received too late"),
    UNSUCCESSFUL_ACCOUNT_AMOUNT_BLOCK("69", "Unsuccessful Account/Amount block"),
    UNSUCCESSFUL_ACCOUNT_AMOUNT_UNBLOCK("70", "Unsuccessful Account/Amount unblock"),
    EMPTY_MANDATE_REFERENCE_NUMBER("71", "Empty Mandate Reference Number"),
    BENEFICIARY_BANK_NOT_AVAILABLE("91", "Beneficiary Bank not available"),
    ROUTING_ERROR("92","Routing error"),
    DUPLICATE_TRANSACTION("94", "Duplicate transaction"),
    SYSTEM_MALFUNCTION("96", "System malfunction"),
    TIMEOUT_WAITING_FOR_RESPONSE_FROM_DESTINATION("97", "Timeout waiting for response from destination");


    String code;
    String message;

    Response(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}