public class EmailSender extends NotificationSender {

    public EmailSender(AuditLog audit) {
        super(audit);
    }

    @Override
    protected Notification normalize(Notification n) {

        String body = n.body;

        if (body.length() > 40) {
            body = body.substring(0, 40);
        }

        return new Notification( n.subject, body, n.email, n.phone);
    }

    @Override
    protected void doSend(Notification n) {

        System.out.println("EMAIL -> to=" + n.email + " subject=" + n.subject + " body=" + n.body);
    }

    @Override
    protected String getName() {
        return "email";
    }
}