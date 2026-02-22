    import java.util.*;

    public class OnboardingService {
        private final PersistenceDb db;
        private final StudentInputParser parser;
        private final ValidationService validator;
        private final PrintingService printer;

        public OnboardingService(PersistenceDb db) {
            this.db = db;
            this.parser = new StudentInputParser();
            this.validator = new ValidationService();
            this.printer = new PrintingService();
        }

        // Intentionally violates SRP: parses + validates + creates ID + saves + prints.
        public void registerFromRawInput(String raw) {
            printer.printInput(raw);

            Map<String, String> data = parser.parseRaw(raw);
            List<String> errors = validator.validate(data);

            if (!errors.isEmpty()) {
               printer.printErrors(errors);
               return;
            }

            String name = data.get("name");
            String email = data.get("email");
            String phone = data.get("phone");
            String program = data.get("program");

            String id = IdUtil.nextStudentId(db.count());
            StudentRecord rec = new StudentRecord(id, name, email, phone, program);

            db.save(rec);

            printer.printSuccess(rec, db.count());
        }
    }
