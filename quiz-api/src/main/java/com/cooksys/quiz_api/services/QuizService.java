package com.cooksys.quiz_api.services;

import java.util.List;

import com.cooksys.quiz_api.dtos.QuizResponseDto;
import com.cooksys.quiz_api.dtos.QuestionResponseDto;
import com.cooksys.quiz_api.dtos.QuizRequestDto;


public interface QuizService {

  List<QuizResponseDto> getAllQuizzes();
  QuizResponseDto createQuiz(QuizRequestDto requestDto);

  QuizResponseDto deleteQuiz(Long id);

  QuizResponseDto renameQuiz(Long id, String newName);

  QuestionResponseDto getRandomQuestion(Long id);

  QuizResponseDto addQuestionToQuiz(Long id, QuestionResponseDto questionDto);

  QuestionResponseDto deleteQuestionFromQuiz(Long quizId, Long questionId);

}
