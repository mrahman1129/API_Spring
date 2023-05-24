package com.cooksys.quiz_api.mappers;

import com.cooksys.quiz_api.dtos.AnswerResponseDto;
import com.cooksys.quiz_api.entities.Answer;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class AnswerMapperImpl implements AnswerMapper {

    @Override
    public AnswerResponseDto entityToDto(Answer entity) {
        if (entity == null) {
            return null;
        }

        AnswerResponseDto answerResponseDto = new AnswerResponseDto();

        answerResponseDto.setId(entity.getId());
        answerResponseDto.setText(entity.getText());

        return answerResponseDto;
    }

    @Override
    public List<AnswerResponseDto> entitiesToDtos(List<Answer> entities) {
        if (entities == null) {
            return null;
        }

        List<AnswerResponseDto> list = new ArrayList<AnswerResponseDto>(entities.size());
        for (Answer answer : entities) {
            list.add(entityToDto(answer));
        }

        return list;
    }

    @Override
    public Answer dtoToEntity(AnswerResponseDto dto) {
        if (dto == null) {
            return null;
        }

        Answer answer = new Answer();

        answer.setId(dto.getId());
        answer.setText(dto.getText());

        return answer;
    }

    @Override
    public List<Answer> dtosToEntities(List<AnswerResponseDto> dtos) {
        if (dtos == null) {
            return null;
        }

        List<Answer> list = new ArrayList<>(dtos.size());
        for (AnswerResponseDto dto : dtos) {
            list.add(dtoToEntity(dto));
        }

        return list;
    }
}
