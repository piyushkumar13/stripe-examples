package com.example.stripeexamples.cardholder;

import com.stripe.model.issuing.Cardholder;
import com.stripe.net.RequestOptions;
import com.stripe.param.issuing.CardholderCreateParams;
import com.stripe.param.issuing.CardholderUpdateParams;
import java.time.Instant;
import org.springframework.stereotype.Component;

/**
 * @author Piyush Kumar.
 * @since 25/03/23.
 */
@Component
public class CardHolderOperations {

    public String createCardHolderWithoutActivation() {

        CardholderCreateParams params = CardholderCreateParams.builder()
                .setType(CardholderCreateParams.Type.INDIVIDUAL)
                .setName("Piyush Kumar Via apisdk") // can be atmost 24 chars
                .setEmail("piyush.kumar@apisdk.com")
                .setPhoneNumber("+18888888888")
                .setBilling(CardholderCreateParams.Billing.builder()
                        .setAddress(CardholderCreateParams.Billing.Address.builder()
                                .setCity("San Francisco")
                                .setCountry("US")
                                .setLine1("1234 Main Street")
                                .setState("CA")
                                .setPostalCode("94111")
                                .build())
                        .build())
                .setStatus(CardholderCreateParams.Status.ACTIVE) /* though status is active but cardholder needs to accept terms and conditions by providing timestamp and ip address */
                .build();

        try {
            Cardholder cardholder = Cardholder.create(params);
            System.out.println("The cardholder is created :::: ");
            System.out.println(cardholder.toJson());
            return cardholder.getId();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public String createCardHolderWithActivationSpendingLimitAndMetadata(String idempotencyKey) {

        CardholderCreateParams params = CardholderCreateParams.builder()
                .setType(CardholderCreateParams.Type.INDIVIDUAL)
                .setName("Piyush Kumar Via apisdk") // can be atmost 24 chars
                .setEmail("piyush.kumar@apisdk.com")
                .setPhoneNumber("+18888888888")
                .setBilling(CardholderCreateParams.Billing.builder()
                        .setAddress(CardholderCreateParams.Billing.Address.builder()
                                .setCity("San Francisco")
                                .setCountry("US")
                                .setLine1("1234 Main Street")
                                .setState("CA")
                                .setPostalCode("94111")
                                .build())
                        .build())
                .setStatus(CardholderCreateParams.Status.ACTIVE) /* though status is active but cardholder needs to accept terms and conditions by providing timestamp and ip address */
                .setIndividual(CardholderCreateParams.Individual.builder()
                        .setFirstName("Piyush via apisdk")
                        .setLastName("Kumar")
                        .setCardIssuing(CardholderCreateParams.Individual.CardIssuing.builder()
                                .setUserTermsAcceptance(CardholderCreateParams.Individual.CardIssuing.UserTermsAcceptance.builder()
                                        .setIp("127.0.0.1")
                                        .setDate(Instant.now().getEpochSecond())
                                        .build())
                                .build())
                        .build())
                .setSpendingControls(CardholderCreateParams.SpendingControls.builder()
                        .setSpendingLimitsCurrency("USD")
                        .addSpendingLimit(CardholderCreateParams.SpendingControls.SpendingLimit.builder()
                                .setAmount(1000L)
                                .setInterval(CardholderCreateParams.SpendingControls.SpendingLimit.Interval.ALL_TIME)
                                .build())
                        .build())
                .putMetadata("workId", "some-random-uuid-via-apisdk")
                .putMetadata("ticketId", "some-random-uuid-via-apisdk")
                .build();

        RequestOptions options = RequestOptions.builder()
                .setIdempotencyKey(idempotencyKey)
                .build();


        try {
            Cardholder cardholder = Cardholder.create(params, options);
            System.out.println("The cardholder is created :::: ");
            System.out.println(cardholder.toJson());
            return cardholder.getId();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public String createCardHolderWithActivationSpendingLimitForCompany(String idempotencyKey) {


        CardholderCreateParams params = CardholderCreateParams.builder()
                .setType(CardholderCreateParams.Type.COMPANY)
                .setName("PiyushCompany Via apisdk") // can be atmost 24 chars
                .setEmail("piyush.kumar@apisdk.com")
                .setPhoneNumber("+18888888888")
                .setBilling(CardholderCreateParams.Billing.builder()
                        .setAddress(CardholderCreateParams.Billing.Address.builder()
                                .setCity("San Francisco")
                                .setCountry("US")
                                .setLine1("1234 Main Street")
                                .setState("CA")
                                .setPostalCode("94111")
                                .build())
                        .build())
                .setStatus(CardholderCreateParams.Status.ACTIVE) /* though status is active but cardholder needs to accept terms and conditions by providing timestamp and ip address */
                .setSpendingControls(CardholderCreateParams.SpendingControls.builder()
                        .setSpendingLimitsCurrency("USD")
                        .addSpendingLimit(CardholderCreateParams.SpendingControls.SpendingLimit.builder()
                                .setAmount(1000L)
                                .setInterval(CardholderCreateParams.SpendingControls.SpendingLimit.Interval.ALL_TIME)
                                .build())
                        .build())
                .putMetadata("workId", "some-random-uuid-via-apisdk")
                .putMetadata("ticketId", "some-random-uuid-via-apisdk")
                .build();


        RequestOptions options = RequestOptions.builder()
                .setIdempotencyKey(idempotencyKey)
                .build();


        try {
            Cardholder cardholder = Cardholder.create(params, options);
            System.out.println("The cardholder is created :::: ");
            System.out.println(cardholder.toJson());
            return cardholder.getId();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public void activateCardHolder(String cardHolderId) {

        try {
            Cardholder cardholder = Cardholder.retrieve(cardHolderId);

            CardholderUpdateParams params = CardholderUpdateParams.builder()
                    .setIndividual(CardholderUpdateParams.Individual.builder()
                            .setFirstName("Piyush via apisdk")
                            .setLastName("Kumar")
                            .setCardIssuing(CardholderUpdateParams.Individual.CardIssuing.builder()
                                    .setUserTermsAcceptance(CardholderUpdateParams.Individual.CardIssuing.UserTermsAcceptance.builder()
                                            .setIp("127.0.0.1")
                                            .setDate(Instant.now().getEpochSecond())
                                            .build())
                                    .build())
                            .build())
                    .build();

            Cardholder updatedCardHolder = cardholder.update(params);

            System.out.println("The updated cardholder is :::: ");
            System.out.println(updatedCardHolder.toJson());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setSpendingLimit(String cardHolderId) {
        try {
            Cardholder cardholder = Cardholder.retrieve(cardHolderId);

            CardholderUpdateParams params = CardholderUpdateParams.builder()

                    .setSpendingControls(CardholderUpdateParams.SpendingControls.builder()
                            .setSpendingLimitsCurrency("USD")
                            .addSpendingLimit(CardholderUpdateParams.SpendingControls.SpendingLimit.builder()
                                    .setAmount(1000L)
                                    .setInterval(CardholderUpdateParams.SpendingControls.SpendingLimit.Interval.ALL_TIME)
                                    .build())
                            .build())
                    .build();

            Cardholder updatedCardHolder = cardholder.update(params);

            System.out.println("The updated cardholder is :::: ");
            System.out.println(updatedCardHolder.toJson());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setMetadata(String cardHolderId) {
        try {
            Cardholder cardholder = Cardholder.retrieve(cardHolderId);

            CardholderUpdateParams params = CardholderUpdateParams.builder()
                    .putMetadata("workId", "some-random-uuid-via-apisdk")
                    .putMetadata("ticketId", "some-random-uuid-via-apisdk")
                    .build();

            Cardholder updatedCardHolder = cardholder.update(params);

            System.out.println("The updated cardholder is :::: ");
            System.out.println(updatedCardHolder.toJson());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
