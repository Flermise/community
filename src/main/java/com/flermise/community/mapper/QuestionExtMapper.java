package com.flermise.community.mapper;

import com.flermise.community.dto.QuestionQueryDTO;
import com.flermise.community.model.Question;

import java.util.List;

public interface QuestionExtMapper {
    int incView(Question record);

    int incComment(Question record);

    List<Question> selectRelated(Question record);

    Integer countBySearch(QuestionQueryDTO questionQueryDTO);

    List<Question> selectBySearch(QuestionQueryDTO questionQueryDTO);
}