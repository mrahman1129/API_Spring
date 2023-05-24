package com.cooksys.quiz_api.dtos;

import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class AnswerResponseDto {

  private Long id;

  private String text;
  
  private List<QuestionResponseDto> Question;

}
