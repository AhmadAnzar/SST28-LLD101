public class WhatsAppSender extends NotificationSender {

    public WhatsAppSender(AuditLog audit) {
        super(audit);
    }

    @Override
    protected Notification normalize(Notification n) {

        if (n.phone == null || !n.phone.startsWith("+")) {
            throw new IllegalArgumentException(
                    "phone must start with + and country code");
        }

        return n;
    }

    @Override
    protected void doSend(Notification n) {

        System.out.println("WA -> to=" + n.phone +" body=" + n.body);
    }

    @Override
    protected String getName() {
        return "wa";
    }
}