
package com.example.stripeexamples.card;

import static java.util.Objects.nonNull;

import com.stripe.exception.StripeException;
import com.stripe.model.issuing.Card;
import com.stripe.param.issuing.CardCreateParams;
import com.stripe.param.issuing.CardListParams;
import com.stripe.param.issuing.CardUpdateParams;
import org.springframework.stereotype.Component;

/**
 * @author Piyush Kumar.
 * @since 26/03/23.
 */
@Component
public class CardOperations {


    /**
     * By default, card created with inactive status.
     * Basically, card is inactive when created.
     */
    public String createVirtualCardWithoutActivation(String cardHolderId) {

        CardCreateParams params = CardCreateParams.builder()
                .setCardholder(cardHolderId)
                .setCurrency("USD")
                .setType(CardCreateParams.Type.VIRTUAL)
                .build();

        Card card = null;
        try {

            card = Card.create(params);

            System.out.println("The card id created for cardholder " + cardHolderId + " is " + card.getId());
            System.out.println("The card id created for cardholder " + cardHolderId + " is ");
            System.out.println(card.toJson());

        } catch (StripeException e) {
            e.printStackTrace();
        }

        return nonNull(card) ? card.getId() : null;
    }

    public String createVirtualCardWithActivation(String cardHolderId) {

        CardCreateParams params = CardCreateParams.builder()
                .setCardholder(cardHolderId)
                .setCurrency("USD")
                .setType(CardCreateParams.Type.VIRTUAL)
                .setStatus(CardCreateParams.Status.ACTIVE)
                .build();

        Card card = null;
        try {

            card = Card.create(params);

            System.out.println("The card id created for cardholder " + cardHolderId + " is " + card.getId());
            System.out.println("The card id created for cardholder " + cardHolderId + " is ");
            System.out.println(card.toJson());

        } catch (StripeException e) {
            e.printStackTrace();
        }

        return nonNull(card) ? card.getId() : null;
    }

    public String createVirtualCardWithActivationSpendingLimitMetadata(String cardHolderId) {

        CardCreateParams params = CardCreateParams.builder()
                .setCardholder(cardHolderId)
                .setCurrency("USD")
                .setType(CardCreateParams.Type.VIRTUAL)
                .setStatus(CardCreateParams.Status.ACTIVE)
                .setSpendingControls(CardCreateParams.SpendingControls.builder()
                        .addSpendingLimit(CardCreateParams.SpendingControls.SpendingLimit.builder()
                                .setAmount(1000L)
                                .setInterval(CardCreateParams.SpendingControls.SpendingLimit.Interval.ALL_TIME)
                                .build())
                        .build())
                .putMetadata("workId", "some-random-uuid-via-apisdk")
                .putMetadata("ticketId", "some-random-uuid-via-apisdk")
                .build();

        Card card = null;
        try {

            card = Card.create(params);

            System.out.println("The card id created for cardholder " + cardHolderId + " is " + card.getId());
            System.out.println("The card id created for cardholder " + cardHolderId + " is ");
            System.out.println(card.toJson());

        } catch (StripeException e) {
            e.printStackTrace();
        }

        return nonNull(card) ? card.getId() : null;
    }

    public void setCardActiveWithSpendingLimitAndMetadata(String cardId){

        try {

            Card card = Card.retrieve(cardId);

            CardUpdateParams params = CardUpdateParams.builder()
                    .setSpendingControls(CardUpdateParams.SpendingControls.builder()
                            .addSpendingLimit(CardUpdateParams.SpendingControls.SpendingLimit.builder()
                                    .setAmount(1000L)
                                    .setInterval(CardUpdateParams.SpendingControls.SpendingLimit.Interval.ALL_TIME)
                                    .build())
                            .build())
                    .setStatus(CardUpdateParams.Status.ACTIVE)
                    .putMetadata("workId", "some-random-uuid-via-apisdk")
                    .putMetadata("ticketId", "some-random-uuid-via-apisdk")
                    .build();

            Card updateCard = card.update(params);

            System.out.println("The updated card is :::: ");
            System.out.println(updateCard.toJson());

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void listCardWithAutoPagination(){

        try {


            CardListParams params = CardListParams.builder().setLimit(10L).build();

            Iterable<Card> cards = Card.list(params).autoPagingIterable();

            System.out.println("List of cards in autopagination mode ::: ");

            /* We can use enhanced for loop, forEach loop or iterator to iterate*/
            for (Card card : cards) {
                System.out.println(card.getId());
            }


//        Iterator<Card> iterator = cards.iterator();
//
//        while (iterator.hasNext()){
//            System.out.println(iterator.next().getId());
//        }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
