package com.practice.shop.service.impl;

import com.practice.shop.model.BlackList;
import com.practice.shop.model.BlackListPK_;
import com.practice.shop.model.BlackList_;
import com.practice.shop.model.TchDescriptionResultList;
import com.practice.shop.model.TeachersDescription;
import com.practice.shop.model.TeachersDescriptionCriteria;
import com.practice.shop.model.TeachersDescription_;
import com.practice.shop.model.user.User_;
import com.practice.shop.model.user.UsersCountry;
import com.practice.shop.service.SearchLessonService;
import com.practice.shop.web.controller.security.jwt.userdetails.CustomUserDetails;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SearchLessonServiceImpl implements SearchLessonService {

    @PersistenceContext
    private final EntityManager entityManager;
    private static final Integer PAGE_SIZE = 10;


    public TchDescriptionResultList searchByParams(TeachersDescriptionCriteria criteria, Long page) {
        Session session = (Session) entityManager.getDelegate();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<TeachersDescription> cq = cb.createQuery(TeachersDescription.class);

        Root<TeachersDescription> root = cq.from(TeachersDescription.class);

                Predicate[] predicates = buildPredicates(criteria, cb, root);
        var allQuery = session.createQuery(cq
                .select(root)
                .where(predicates)
                .groupBy(root.get(TeachersDescription_.id)))
                .setFirstResult((int) (page * PAGE_SIZE))//offset
                .setMaxResults(PAGE_SIZE);//limit
        var resultList = new TchDescriptionResultList();
        resultList.setCurrentPage(page);
        resultList.setTeachersDescriptions(allQuery.getResultList());
        resultList.setTotalCount(getTotalPages(criteria));
        resultList.setPageSize(PAGE_SIZE);
        return resultList;
    }

    public Long getTotalPages(TeachersDescriptionCriteria criteria) {
        Session session = (Session) entityManager.getDelegate();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<TeachersDescription> root = cq.from(TeachersDescription.class);
        Predicate[] predicates = buildPredicates(criteria,cb,root);

        return session.createQuery(cq
                        .select(cb.count(root))
                        .where(predicates))
                .getSingleResult();
    }


    private Predicate[] buildPredicates(TeachersDescriptionCriteria criteria, CriteriaBuilder cb,
                                        Root<TeachersDescription> root) {
        List<Predicate> predicates = new ArrayList<>();
        if (criteria.getIsNative() != null) {
            predicates.add(cb.equal(root.get(TeachersDescription_.isNative), criteria.getIsNative()));
        }
        if (criteria.getCountries() != null) {
            CriteriaBuilder.In<UsersCountry> integerIn = cb.in(root.get(TeachersDescription_.country));
            criteria.getCountries().forEach(
                    integerIn::value
            );
            predicates.add(integerIn);
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
        if (criteria.getMarkFrom() != null) {
            predicates.add(cb.greaterThanOrEqualTo(root.get(TeachersDescription_.mark), criteria.getMarkFrom()));
        }
        if (criteria.getMarkTo() != null) {
            predicates.add(cb.lessThanOrEqualTo(root.get(TeachersDescription_.mark), criteria.getMarkTo()));
        }
        if (criteria.getLanguage() != null) {
            predicates.add(cb.equal(root.get(TeachersDescription_.language), criteria.getLanguage()));
        }
        if (criteria.getLevelFrom() != null) {
            predicates.add(cb.greaterThanOrEqualTo(root.get(TeachersDescription_.level), criteria.getLevelFrom()));
        }

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();


        Join<TeachersDescription, BlackList> join = root.join(TeachersDescription_.BLACK_LISTS, JoinType.LEFT);
        join = join.on(cb.equal(join.get(BlackList_.PK).get(BlackListPK_.USER_ID), userDetails.getId()))
                .on(cb.equal(join.get(BlackList_.PK).get(BlackListPK_.BLOCKED_USER_ID), root.get(TeachersDescription_.ID)));


        predicates.add(cb.isNull(join.get(BlackList_.USER).get(User_.ID)));
        return predicates.toArray(Predicate[]::new);
    }

}