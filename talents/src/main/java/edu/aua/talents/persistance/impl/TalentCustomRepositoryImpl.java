package edu.aua.talents.persistance.impl;

import edu.aua.talents.persistance.TalentCustomRepository;
import edu.aua.talents.persistance.entity.Talent;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

import java.util.List;

public class TalentCustomRepositoryImpl implements TalentCustomRepository {
    private final EntityManager entityManager;
    private final CriteriaBuilder cb;

    public TalentCustomRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
        this.cb = entityManager.getCriteriaBuilder();
    }

    @Override
    public List<Talent> findBySpecializationId(Long specializationId) {

        CriteriaQuery<Talent> cq = cb.createQuery(Talent.class);

        Root<Talent> talent = cq.from(Talent.class);

        Predicate specializationPredicate = cb.equal(talent.get("specialization"), specializationId);

        cq.where(specializationPredicate);

        TypedQuery<Talent> query = entityManager.createQuery(cq);

        return query.getResultList();
    }
}
