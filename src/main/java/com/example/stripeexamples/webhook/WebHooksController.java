//package com.example.stripeexamples.webhook;
//
//import com.stripe.exception.SignatureVerificationException;
//import com.stripe.model.Event;
//import com.stripe.net.ApiResource;
//import com.stripe.net.Webhook;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.MediaType;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestHeader;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RestController;
//
///**
// * Steps to run it :
// *
// * Download Stripe cli : https://stripe.com/docs/stripe-cli
// * stripe login
// * stripe listen --forward-to localhost:8080/webhookwithoutsign OR stripe listen --forward-to localhost:8080/webhookwithsign
// * stripe trigger customer.created
// *
// * @author Piyush Kumar.
// * @since 26/03/23.
// */
//@RestController
//public class WebHooksController {
//
//    @Value("${stripe.wehbhookSecretKey}")
//    private String webhookSecretKey;
//
//    @RequestMapping(value = "/webhookwithoutsign", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<String> webhookWithoutSign(@RequestBody String eventBody) {
//
//        System.out.println("Event received is :::: " + eventBody);
//
//        Event event = ApiResource.GSON.fromJson(eventBody, Event.class);
//
//        System.out.println("The event constructed is is :::: ");
//        System.out.println(event);
//
//        System.out.println(event.getId());
//        System.out.println(event.getType());
//        System.out.println(event.getDataObjectDeserializer().getObject().getClass());
//
//        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//    }
//
//    @RequestMapping(value = "/webhookwithsign", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<String> webhookWithtSign(@RequestBody String eventBody, @RequestHeader("Stripe-Signature") String stripeSignatureHeader) {
//
//        System.out.println("Event with signing received is :::: " + eventBody);
//        System.out.println("Stripe signature header is :::: " + stripeSignatureHeader);
//
//        try {
//            Event event = Webhook.constructEvent(eventBody, stripeSignatureHeader, webhookSecretKey);
//            System.out.println("The event constructed is is :::: ");
//            System.out.println(event);
//
//            System.out.println(event.getId());
//            System.out.println(event.getType());
//            System.out.println(event.getDataObjectDeserializer().getObject().getClass());
//
//
//        } catch (SignatureVerificationException e) {
//            e.printStackTrace();
//            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//
//        }
//
//        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//    }
//}
