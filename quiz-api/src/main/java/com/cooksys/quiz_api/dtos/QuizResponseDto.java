package com.cooksys.quiz_api.dtos;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@AllArgsConstructor
public class QuizResponseDto {

  private Long id;

  private String name;

  public List<QuestionResponseDto> questions;

}
