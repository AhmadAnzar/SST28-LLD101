public class CsvExporter extends Exporter {

    @Override
    protected String encode(ExportRequest req) {

        String body = req.body == null ? "" : req.body;

        return "title,body\n" + escape(req.title) + "," + escape(body) + "\n";
    }

    @Override
    protected String contentType() {
        return "text/csv";
    }

    private String escape(String s) {
        if (s == null)
            return "";
        return s.replace("\n", " ").replace(",", " ");
    }
}