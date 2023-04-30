package com.practice.shop.service;

import com.practice.shop.model.TchDescriptionResultList;
import com.practice.shop.model.TeachersDescriptionCriteria;


public interface SearchLessonService {

    TchDescriptionResultList searchByParams(TeachersDescriptionCriteria description, Long page);

}
