package com.murtaza.mymart.order.service;

import com.razorpay.*;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class RazorPayClientService {

    @Autowired
    private RazorpayClient razorpayClient;

    public String createPayment(double amount, String currency, String receipt) throws Exception {

        JSONObject orderRequest = new JSONObject();
        orderRequest.put("amount", amount * 100); // amount in paise (INR 100 = 10000 paise)
        orderRequest.put("currency", currency);
        orderRequest.put("receipt", receipt);
        orderRequest.put("payment_capture", 1);

        Order razorpayOrder = razorpayClient.orders.create(orderRequest);
        String razorpayOrderId = razorpayOrder.get("id");
        log.info(razorpayOrderId);

        return razorpayOrderId;
    }


    public String refundPayment2(String razorpayPaymentId, Integer amountInPaise) throws RazorpayException {
        // amountInPaise must be paise (e.g. Rs 500 => 50000)
        if (razorpayPaymentId == null || razorpayPaymentId.isBlank()) {
            throw new IllegalArgumentException("paymentId is null/empty");
        }
        if (amountInPaise == null || amountInPaise <= 0) {
            throw new IllegalArgumentException("amountInPaise must be > 0");
        }

        try {
            // 1) fetch payment from Razorpay
            Payment payment = razorpayClient.payments.fetch(razorpayPaymentId);
            Object statusObj = payment.get("status");
            String status = statusObj == null ? null : statusObj.toString();

            // amount fields from Razorpay are integers in paise
            Number amountObj = (Number) payment.get("amount");              // captured amount (paise)
            Number refundedObj = (Number) payment.get("amount_refunded");  // already refunded (paise)

            int capturedAmount = amountObj == null ? 0 : amountObj.intValue();
            int alreadyRefunded = refundedObj == null ? 0 : refundedObj.intValue();
            int refundable = capturedAmount - alreadyRefunded;

            log.info("== Payment fetched: id={}, status={}, amount(paise)={}, amount_refunded(paise)={}, refundable(paise)={}",
                    razorpayPaymentId, status, capturedAmount, alreadyRefunded, refundable);

            // 2) validations
            if (!"captured".equalsIgnoreCase(status)) {
                String msg = "Refund not allowed: payment status is " + status;
                log.warn(msg);
                throw new RazorpayException(msg);
            }

            if (amountInPaise > refundable) {
                String msg = String.format("Requested refund (%d paise) is greater than refundable (%d paise).", amountInPaise, refundable);
                log.warn(msg);
                throw new RazorpayException(msg);
            }

            // 3) build refund request payload and log
            JSONObject refundRequest = new JSONObject();
            refundRequest.put("amount", amountInPaise);
            log.info("== Refund request payload for paymentId {} : {}", razorpayPaymentId, refundRequest.toString());

            // 4) attempt refund
            Refund refund = razorpayClient.payments.refund(razorpayPaymentId, refundRequest);

            log.info("== Refund response: {}", refund.toString());
            return refund.toString();

        } catch (RazorpayException e) {
            // Log more context to help debugging
            log.error("RazorpayException while refunding paymentId={}, amount={} : {}", razorpayPaymentId, amountInPaise, e.getMessage(), e);
            // If ApiClient included response body, e.getMessage() often contains it; log stacktrace too.
            throw e; // rethrow so controller can handle / return proper status
        } catch (Exception e) {
            log.error("Unexpected error while attempting refund: {}", e.getMessage(), e);
            throw new RuntimeException(e);
        }
    }

    public String refundPayment(String razorpayPaymentId, Integer amountInPaise) throws RazorpayException {
        if (razorpayPaymentId == null || razorpayPaymentId.isBlank()) {
            throw new IllegalArgumentException("paymentId is null/empty");
        }
        if (amountInPaise == null || amountInPaise <= 0) {
            throw new IllegalArgumentException("amountInPaise must be > 0");
        }

        try {
            // fetch payment and compute refundable
            Payment payment = razorpayClient.payments.fetch(razorpayPaymentId);
            String status = payment.get("status") == null ? null : payment.get("status").toString();
            int captured = payment.get("amount") == null ? 0 : ((Number) payment.get("amount")).intValue();
            int refunded = payment.get("amount_refunded") == null ? 0 : ((Number) payment.get("amount_refunded")).intValue();
            int refundable = captured - refunded;

            log.info("Payment fetched id={} status={} captured(paise)={} refunded(paise)={} refundable(paise)={}",
                    razorpayPaymentId, status, captured, refunded, refundable);

            if (!"captured".equalsIgnoreCase(status)) {
                throw new RazorpayException("Refund not allowed: payment status is " + status);
            }
            if (amountInPaise > refundable) {
                throw new RazorpayException("Requested refund (" + amountInPaise + ") is greater than refundable (" + refundable + ")");
            }

            // Use the /v1/refunds API (explicit payment_id) — avoids ambiguity of endpoints
            JSONObject req = new JSONObject();
            req.put("payment_id", razorpayPaymentId);
            req.put("amount", amountInPaise); // paise
            log.info("Calling refunds.create with payload: {}", req.toString());

            Refund refund = razorpayClient.refunds.create(req);

            log.info("Refund success: {}", refund.toString());
            return refund.toString();

        } catch (RazorpayException e) {
            // log the SDK message and stack trace — the SDK message often contains API response body
            log.error("RazorpayException refunding paymentId={}, amount={} : {}", razorpayPaymentId, amountInPaise, e.getMessage(), e);
            throw e;
        } catch (Exception e) {
            log.error("Unexpected error refunding paymentId={}, amount={} : {}", razorpayPaymentId, amountInPaise, e.getMessage(), e);
            throw new RuntimeException(e);
        }
    }


}
