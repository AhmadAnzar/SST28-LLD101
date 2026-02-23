public class PdfExporter extends Exporter {

    @Override
    protected String encode(ExportRequest req) {

        if (req.body != null && req.body.length() > 20) {
            throw new IllegalArgumentException(
                "PDF cannot handle content > 20 chars"
            );
        }

        return "PDF(" + req.title + "):" + req.body;
    }

    @Override
    protected String contentType() {
        return "application/pdf";
    }
}