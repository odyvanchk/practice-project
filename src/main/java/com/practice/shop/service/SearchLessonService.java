package com.practice.shop.service;

import com.practice.shop.model.TeachersDescriptionCriteria;
import com.practice.shop.model.TeachersDescription;

import java.util.List;

public interface SearchLessonService {

    List<TeachersDescription> searchByParams(TeachersDescriptionCriteria description, Long page);

}
