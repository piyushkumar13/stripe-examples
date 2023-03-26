/*
 *  Copyright (c) 2023 DMG
 *  All Rights Reserved Worldwide.
 *
 *  THIS PROGRAM IS CONFIDENTIAL AND PROPRIETARY TO DMG
 *  AND CONSTITUTES A VALUABLE TRADE SECRET.
 */
package com.example.stripeexamples.card;

import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import com.stripe.model.PaymentMethod;
import com.stripe.param.PaymentIntentCreateParams;
import com.stripe.param.PaymentMethodCreateParams;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * NOTE : Below way is not the recommended way.
 * Recommended ways is to create a paymentIntent and return clientSecret to UI.
 * UI will use that clientSecret to confirm payment. Check following link :
 * <a href="https://stripe.com/docs/payments/accept-a-payment?ui=elements"/>
 *
 * @author Piyush Kumar.
 * @since 26/03/23.
 */
@Component
public class TransactionsOnCard {

    @Value("${stripe.crediCard.number}")
    private String creditCardNumber;

    @Value("${stripe.crediCard.cvc}")
    private String cvc;

    @Value("${stripe.crediCard.expiryMonth}")
    private Long expiryMonth;

    @Value("${stripe.crediCard.expiryYear}")
    private Long expiryYear;

    @Value("${stripe.crediCard.chargeAmount}")
    private Long chargeAmount;


    private String createPaymentMethod() {

        PaymentMethodCreateParams params = PaymentMethodCreateParams.builder()
                .setType(PaymentMethodCreateParams.Type.CARD)
                .setCard(PaymentMethodCreateParams.CardDetails.builder()
                        .setNumber(creditCardNumber)
                        .setCvc(cvc)
                        .setExpMonth(expiryMonth)
                        .setExpYear(expiryYear)
                        .build())
                .build();

        String paymentMethodId = null;
        try {
            PaymentMethod paymentMethod = PaymentMethod.create(params);
            System.out.println("The payment method response is :::: ");
            System.out.println(paymentMethod.toJson());
            paymentMethodId = paymentMethod.getId();

        } catch (StripeException e) {
            e.printStackTrace();
        }

        return paymentMethodId;
    }

    private String createPaymentIntent(String paymentMethodId) {

        PaymentIntentCreateParams params = PaymentIntentCreateParams.builder()
                .setCurrency("USD")
                .addPaymentMethodType("card")
                .setPaymentMethod(paymentMethodId)
                .setAmount(chargeAmount)
                .setDescription("Piyush Trans via apisdk")
                .setStatementDescriptor("PiyushTrans via apisdk") // Should be atmost 22 characters
                .build();

        String paymentIntentId = null;
        try {
            PaymentIntent paymentIntent = PaymentIntent.create(params);
            System.out.println("The payment intent response is :::: ");
            System.out.println(paymentIntent.toJson());
            paymentIntentId = paymentIntent.getId();

        } catch (StripeException e) {
            e.printStackTrace();
        }

        return paymentIntentId;
    }

    private void confirmPaymentIntent(String paymentIntentId) {

        try {

            PaymentIntent paymentIntent = PaymentIntent.retrieve(paymentIntentId);
            paymentIntent.confirm();
            System.out.println("PaymentIntent is confirmed");
            System.out.println("Transaction is completed");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void createPaymentIntentWithConfirmation(String paymentMethodId) {

        PaymentIntentCreateParams params = PaymentIntentCreateParams.builder()
                .setCurrency("USD")
                .addPaymentMethodType("card")
                .setPaymentMethod(paymentMethodId)
                .setAmount(chargeAmount)
                .setDescription("Piyush Trans via apisdk")
                .setStatementDescriptor("PiyushTrans via apisdk") // Should be atmost 22 characters
                .setConfirm(true)
                .build();

        try {
            PaymentIntent paymentIntent = PaymentIntent.create(params);
            System.out.println("The payment intent with confirmation response is :::: ");
            System.out.println(paymentIntent.toJson());

        } catch (StripeException e) {
            e.printStackTrace();
        }
    }

    public void transactionWithManualConfirmation(){
        String paymentMethodId = createPaymentMethod();
        System.out.println("Payment method id :::: " + paymentMethodId);

        String paymentIntentId = createPaymentIntent(paymentMethodId);
        System.out.println("Payment intent id :::: " + paymentIntentId);

        confirmPaymentIntent(paymentIntentId);

        System.out.println("Transaction with manual confirmation is completed.");
    }

    public void transactionWithAutomaticConfirmation(){
        String automaticPaymentMethodId = createPaymentMethod();
        System.out.println("Payment method id :::: " + automaticPaymentMethodId);

        createPaymentIntentWithConfirmation(automaticPaymentMethodId);

        System.out.println("Transaction with automatic confirmation is completed.");
    }
}
