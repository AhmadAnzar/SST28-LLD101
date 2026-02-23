public abstract class NotificationSender {

    protected final AuditLog audit;

    protected NotificationSender(AuditLog audit) {
        this.audit = audit;
    }

    // Template method
    public final void send(Notification n) {

        if (n == null) {
            throw new IllegalArgumentException("Notification cannot be null");
        }

        Notification normalized = normalize(n);

        doSend(normalized);

        audit.add(getName() + " sent");
    }

    // Default: no change
    protected Notification normalize(Notification n) {
        return n;
    }

    protected abstract void doSend(Notification n);

    protected abstract String getName();
}