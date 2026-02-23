public abstract class Exporter {

    public final ExportResult export(ExportRequest req) {

        if (req == null) {
            throw new IllegalArgumentException("Request cannot be null");
        }

        String encoded = encode(req);

        if (encoded == null) {
            throw new IllegalStateException("Encoding failed");
        }

      return new ExportResult(contentType(), encoded.getBytes(java.nio.charset.StandardCharsets.UTF_8));
    }

    protected abstract String encode(ExportRequest req);

    protected abstract String contentType();
}