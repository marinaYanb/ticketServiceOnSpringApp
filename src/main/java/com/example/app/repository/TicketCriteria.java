package com.example.app.repository;

import com.example.app.model.Ticket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Root;
import java.util.List;

@Component
public class TicketCriteria {

    EntityManager entityManager;

    List<Ticket> findAllTickets() {
        try {
            CriteriaBuilder builder = entityManager.getCriteriaBuilder();
            CriteriaQuery<Ticket> criteriaQuery = builder.createQuery(Ticket.class);
            Root<Ticket> ticketRoot = criteriaQuery.from(Ticket.class);

            Path<Boolean> deleted = ticketRoot.get("deleted");
            criteriaQuery.select(ticketRoot).where(builder.equal(deleted, false));
            TypedQuery<Ticket> query = entityManager.createQuery(criteriaQuery);

            return query.getResultList();
        } finally {
            entityManager.close();
        }
    }
}
