package com.practice.shop.service.impl;

import com.practice.shop.model.TeachersDescriptionCriteria;
import com.practice.shop.model.user.TeachersDescription;
import com.practice.shop.model.user.TeachersDescription_;
import com.practice.shop.model.user.TeachersPermanentPreference;
import com.practice.shop.model.user.TeachersPermanentPreference_;
import com.practice.shop.service.SearchLessonService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.*;
import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SearchLessonServiceImpl implements SearchLessonService {

    @PersistenceContext
    private final EntityManager entityManager;
    private static final Integer PAGE_SIZE = 10;


    public List<TeachersDescription> searchByParams(TeachersDescriptionCriteria criteria, Long page) {
        Session session = (Session) entityManager.getDelegate();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<TeachersDescription> cq = cb.createQuery(TeachersDescription.class);

        Root<TeachersDescription> root = cq.from(TeachersDescription.class);
        Join<TeachersDescription, TeachersPermanentPreference> preferenceJoin = root.join(TeachersDescription_.teachersPermanentPreferences, JoinType.LEFT);
        //Join<TeachersDescription, Schedule> scheduleJoin = root.join(TeachersDescription_.schedules, JoinType.LEFT);

        Predicate[] predicates = buildPredicates(criteria, cb, root, preferenceJoin);
        var allQuery = session.createQuery(cq
                .select(root)
                .where(predicates)
                .groupBy(root.get(TeachersDescription_.id)))
                .setFirstResult((int) (page * PAGE_SIZE))//offset
                .setMaxResults(PAGE_SIZE);//limit
        return allQuery.getResultList();
    }

    private Predicate[] buildPredicates(TeachersDescriptionCriteria criteria, CriteriaBuilder cb,
                                        Root<TeachersDescription> root, Join<TeachersDescription,
                                        TeachersPermanentPreference> preferenceJoin) {
        List<Predicate> predicates = new ArrayList<>();
        if (criteria.getWeekDay() != null) {
            predicates.add(cb.equal(preferenceJoin.get(TeachersPermanentPreference_.weekDay), criteria.getWeekDay()));
        }
        if (criteria.getStart() != null) {
            predicates.add(cb.equal(preferenceJoin.get(TeachersPermanentPreference_.timeStart), criteria.getStart()));
        }
        if (criteria.getIsNative() != null) {
            predicates.add(cb.equal(root.get(TeachersDescription_.isNative), criteria.getIsNative()));
        }
        if (criteria.getCountryIdSet() != null) {
            predicates.add(cb.in(root.get(TeachersDescription_.country)));
        }
        if (criteria.getDefaultPriceFrom() != null) {
            predicates.add(cb.greaterThanOrEqualTo(root.get(TeachersDescription_.defaultPrice), criteria.getDefaultPriceFrom()));
        }
        if (criteria.getDefaultPriceTo() != null) {
            predicates.add(cb.lessThanOrEqualTo(root.get(TeachersDescription_.defaultPrice), criteria.getDefaultPriceTo()));
        }
        if (criteria.getExperienceFrom() != null) {
            predicates.add(cb.greaterThanOrEqualTo(root.get(TeachersDescription_.experience), criteria.getExperienceFrom()));
        }
        if (criteria.getExperienceTo() != null) {
            predicates.add(cb.lessThanOrEqualTo(root.get(TeachersDescription_.experience), criteria.getExperienceTo()));
        }
        if (criteria.getStart() != null) {
            predicates.add(cb.equal(preferenceJoin.get(TeachersPermanentPreference_.timeStart), criteria.getStart()));
        }
        if (criteria.getMarkFrom() != null) {
            predicates.add(cb.greaterThanOrEqualTo(root.get(TeachersDescription_.mark), criteria.getMarkFrom()));
        }
        if (criteria.getMarkTo() != null) {
            predicates.add(cb.lessThanOrEqualTo(root.get(TeachersDescription_.mark), criteria.getMarkTo()));
        }
        return predicates.toArray(Predicate[]::new);
    }

}