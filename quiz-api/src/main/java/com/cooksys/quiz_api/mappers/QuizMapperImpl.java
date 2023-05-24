package com.cooksys.quiz_api.mappers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.cooksys.quiz_api.dtos.QuizRequestDto;
import com.cooksys.quiz_api.dtos.QuizResponseDto;
import com.cooksys.quiz_api.entities.Quiz;

@Component
public class QuizMapperImpl implements QuizMapper {

    @Override
    public Quiz dtoToEntity(QuizRequestDto dto) {
        Quiz quiz = new Quiz();
        quiz.setName(dto.getName());
        return quiz;
    }

    @Override
    public QuizResponseDto entityToDto(Quiz entity) {
        QuizResponseDto dto = new QuizResponseDto();
        dto.setName(entity.getName());
        return dto;
    }
    
    @Override
    public List<QuizResponseDto> entitiesToDtos(List<Quiz> entities) {
        return entities.stream()
                .map(this::entityToDto)
                .collect(Collectors.toList());
    }
}

