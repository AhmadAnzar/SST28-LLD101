package com.example.payments;

public class SafeCashAdapter implements PaymentGateway {
    
    private final SafeCashClient client;

    public SafeCashAdapter(SafeCashClient client) {
        this.client = client;
    }

    @Override
    public String charge(String cId, int amount) {
        SafeCashPayment payment = client.createPayment(amount, cId);
        return payment.confirm();
    }
}