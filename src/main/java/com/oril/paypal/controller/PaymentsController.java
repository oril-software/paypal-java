package com.oril.paypal.controller;

import com.oril.paypal.model.PayPalResponseDTO;
import com.oril.paypal.service.PayPalService;
import com.paypal.api.payments.Payment;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/payments")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class PaymentsController {

    private final PayPalService payPalService;

    @GetMapping(value = "/paypal")
    public PayPalResponseDTO getPayPalUrl(@RequestParam Double price) {
        return payPalService.createPayment(price);
    }

    @GetMapping(value = "/paypal/success")
    public void handlePayPalSuccess(@RequestParam String token,
                                    @RequestParam String paymentId,
                                    @RequestParam String PayerID) {
        Payment payment = payPalService.executePayment(paymentId, PayerID);
        // TODO here you get payment response and can implement other logic by yourself (e.g. saving transaction)
    }

    @GetMapping(value = "/paypal/cancel")
    public void handlePalPalCancel(@RequestParam String token) {
        // TODO here you can handle when user press cancel button on PayPal form
    }

}
