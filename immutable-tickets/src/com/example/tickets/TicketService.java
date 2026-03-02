package com.example.tickets;

import java.util.ArrayList;
import java.util.List;

/**
 * Service layer that creates tickets.
 *
 * CURRENT STATE (BROKEN ON PURPOSE):
 * - creates partially valid objects
 * - mutates after creation (bad for auditability)
 * - validation is scattered & incomplete
 *
 * TODO (student):
 * - After introducing immutable IncidentTicket + Builder, refactor this to stop mutating.
 */
public class TicketService {

    public IncidentTicket createTicket(String id, String reporterEmail, String title) {

      IncidentTicket.Builder builder = IncidentTicket.builder();

    builder.id(id);
    builder.reporterEmail(reporterEmail);
    builder.title(title);

    builder.priority("MEDIUM");
    builder.source("CLI");
    builder.customerVisible(false);
    builder.addTag("NEW");

    return builder.build();
    }

    public IncidentTicket escalateToCritical(IncidentTicket t) {
       IncidentTicket.Builder builder = t.toBuilder();

    builder.priority("CRITICAL");
    builder.addTag("ESCALATED");

    return builder.build();
    }

  public IncidentTicket assign(IncidentTicket t, String assigneeEmail) {
     
    IncidentTicket.Builder builder = t.toBuilder();
    builder.assigneeEmail(assigneeEmail);

    return builder.build();
    }
}
