public class JsonExporter extends Exporter {

    @Override
    protected String encode(ExportRequest req) {
        return "{\"title\":\"" + escape(req.title) + "\",\"body\":\"" + escape(req.body) + "\"}";
    }

    @Override
    protected String contentType() {
        return "application/json";
    }

    private String escape(String s) {
        if (s == null) return "";
        return s.replace("\"", "\\\"");
    }
}