# stripe-examples
This repository contains examples for integrating stripe using stripe java sdk with spring boot

## How to run ?
To run the different examples, provide necessary keys in application.yaml file and uncomment line of codes in StripeExamplesApplication class and run it.

## How to run webhooks ?
* Uncomment `spring-boot-starter-web` dependency in `pom.xml`
* Uncomment `WebHooksController.java`
* Download Stripe cli : https://stripe.com/docs/stripe-cli
* stripe login
* stripe listen --forward-to localhost:8080/webhookwithoutsign OR stripe listen --forward-to localhost:8080/webhookwithsign
* stripe trigger `customer.created`