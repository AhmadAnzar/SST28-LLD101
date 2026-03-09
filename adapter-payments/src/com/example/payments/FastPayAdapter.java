package com.example.payments;

public class FastPayAdapter implements PaymentGateway {
    private final FastPayClient client;

    public FastPayAdapter(FastPayClient client) {
        this.client = client;
    }

    @Override
    public String charge(String cId, int amount) {
        //returns a string and uses paynow fn
        return client.payNow(cId, amount);
    }
}