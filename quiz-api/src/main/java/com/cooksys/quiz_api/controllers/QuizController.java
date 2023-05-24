package com.cooksys.quiz_api.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cooksys.quiz_api.dtos.QuestionResponseDto;
import com.cooksys.quiz_api.dtos.QuizRequestDto;
import com.cooksys.quiz_api.dtos.QuizResponseDto;
import com.cooksys.quiz_api.services.QuizService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/quiz")
public class QuizController {

  private final QuizService quizService;

  @GetMapping
  public List<QuizResponseDto> getAllQuizzes() {
    return quizService.getAllQuizzes();
  }
  
  // TODO: Implement the remaining 6 endpoints from the documentation.
  @PostMapping
  public QuizResponseDto createQuiz(@RequestBody QuizRequestDto quizRequestDto) {
    return quizService.createQuiz(quizRequestDto);
  }

  @DeleteMapping("/{id}")
  public QuizResponseDto deleteQuiz(@PathVariable("id") Long id) {
      return quizService.deleteQuiz(id);
  }

  @PatchMapping("/{id}/rename/{newName}")
  public QuizResponseDto renameQuiz(@PathVariable("id") Long id, @PathVariable("newName") String newName) {
      return quizService.renameQuiz(id, newName);
  }

  @GetMapping("/{id}/random")
  public QuestionResponseDto getRandomQuestion(@PathVariable("id") Long id) {
      return quizService.getRandomQuestion(id);
  }

  @PatchMapping("/{id}/add")
  public QuizResponseDto addQuestionToQuiz(@PathVariable("id") Long id, @RequestBody QuestionResponseDto questionResponseDto) {
      return quizService.addQuestionToQuiz(id, questionResponseDto);
  }

  @DeleteMapping("/{id}/delete/{questionId}")
  public QuestionResponseDto deleteQuestionFromQuiz(@PathVariable("id") Long id, @PathVariable("questionId") Long questionId) {
      return quizService.deleteQuestionFromQuiz(id, questionId);
  }

}
