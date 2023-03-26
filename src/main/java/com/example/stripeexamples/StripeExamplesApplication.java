package com.example.stripeexamples;

import com.example.stripeexamples.card.CardOperations;
import com.example.stripeexamples.card.TransactionsOnCard;
import com.example.stripeexamples.cardholder.CardHolderOperations;
import com.stripe.Stripe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class StripeExamplesApplication implements CommandLineRunner {

    @Value("${stripe.apiKey}")
    private String apiSecretKey;

    @Value("${stripe.cardHolderId}")
    private String cardHolderId;

    @Autowired
    private CardHolderOperations cardHolderOperations;

    @Autowired
    private CardOperations cardOperations;

    @Autowired
    private TransactionsOnCard transactionsOnCard;

    public static void main(String[] args) {
        SpringApplication.run(StripeExamplesApplication.class, args);
    }

    @Override
    public void run(String... args) {
        Stripe.apiKey = apiSecretKey;
        Stripe.setMaxNetworkRetries(2);

        /*=================== CardHolder Operations ============================= */

//        String cardHolderId = cardHolderOperations.createCardHolderWithoutActivation();
//        System.out.println("The cardholder id is :::: " + cardHolderId);
//
//        cardHolderOperations.activateCardHolder(cardHolderId);
//        cardHolderOperations.setSpendingLimit(cardHolderId);
//        cardHolderOperations.setMetadata(cardHolderId);

//        String idempotencyKey = UUID.randomUUID().toString();
//        System.out.println("The IdempotencyKey is ::: " + idempotencyKey);

//        String cardHolderIdWithActivation = cardHolderOperations.createCardHolderWithActivationSpendingLimitAndMetadata(idempotencyKey);
//        System.out.println("The cardholder id with activation is :::: " + cardHolderIdWithActivation);

//        String cardHolderIdForCompanyWithActivation = cardHolderOperations.createCardHolderWithActivationSpendingLimitForCompany("97d38bde-22c7-4713-acfa-18e3d96835ef");
//        System.out.println("The cardholder id for company with activation is :::: " + cardHolderIdForCompanyWithActivation);

        /*=================== Card Operations ============================= */

//        String virtualCardWithoutActivation = cardOperations.createVirtualCardWithoutActivation(cardHolderId);
//        System.out.println("The virtual card created withoutActivation is :::: " + virtualCardWithoutActivation);
//
//        cardOperations.setCardActiveWithSpendingLimitAndMetadata(virtualCardWithoutActivation);
//
//        String virtualCardWithActivation = cardOperations.createVirtualCardWithActivation(cardHolderId);
//        System.out.println("The virtual card created with Activation is :::: " + virtualCardWithActivation);
//        cardOperations.setCardActiveWithSpendingLimitAndMetadata(virtualCardWithActivation);
//
//        String virtualCardWithActivationSpendingLimitMetadata = cardOperations.createVirtualCardWithActivationSpendingLimitMetadata(cardHolderId);
//        System.out.println("The virtual card created with Activation, spending limit and metadata is :::: " + virtualCardWithActivationSpendingLimitMetadata);

//         cardOperations.listCardWithAutoPagination();
        /*=================== Transactions on Card ============================= */

//        /* With manual confirmation */
//        transactionsOnCard.transactionWithManualConfirmation();

//        /* With automatic confirmation */
//        transactionsOnCard.transactionWithAutomaticConfirmation();

    }
}
