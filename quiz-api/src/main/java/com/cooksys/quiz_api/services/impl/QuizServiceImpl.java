package com.cooksys.quiz_api.services.impl;

import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.springframework.stereotype.Service;

import com.cooksys.quiz_api.dtos.QuestionResponseDto;
import com.cooksys.quiz_api.dtos.QuizRequestDto;
import com.cooksys.quiz_api.dtos.QuizResponseDto;
import com.cooksys.quiz_api.entities.Question;
import com.cooksys.quiz_api.entities.Quiz;
import com.cooksys.quiz_api.mappers.QuestionMapper;
import com.cooksys.quiz_api.mappers.QuizMapper;
import com.cooksys.quiz_api.repositories.AnswerRepository;
import com.cooksys.quiz_api.repositories.QuestionRepository;
import com.cooksys.quiz_api.repositories.QuizRepository;
import com.cooksys.quiz_api.services.QuizService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class QuizServiceImpl implements QuizService {

  private final QuizRepository quizRepository;
  private final QuizMapper quizMapper;
  private final AnswerRepository answerRepository;
  private final QuestionRepository questionRepository;
  private final QuestionMapper questionMapper;

  @Override
  public List<QuizResponseDto> getAllQuizzes() {
    return quizMapper.entitiesToDtos(quizRepository.findAll());
  }
  
  public QuizResponseDto createQuiz(QuizRequestDto quizRequestDto) {
	    Quiz quizToSave = quizMapper.dtoToEntity(quizRequestDto);
	    System.out.println("quizToSave: " + quizToSave);
	    quizRepository.saveAndFlush(quizToSave);
	    return quizMapper.entityToDto(quizToSave);
	}

  
  @Override
  public QuizResponseDto deleteQuiz(Long id) {
    Optional<Quiz> optionalQuiz = quizRepository.findById(id);
    if (optionalQuiz.isPresent()) {
      Quiz quizToDelete = optionalQuiz.get();
      quizRepository.delete(quizToDelete);
      return quizMapper.entityToDto(quizToDelete);
    }
    return null;
  }
  
  @Override
  public QuizResponseDto renameQuiz(Long id, String newName) {
    Optional<Quiz> optionalQuiz = quizRepository.findById(id);
    if (optionalQuiz.isPresent()) {
      Quiz quizToRename = optionalQuiz.get();
      quizToRename.setName(newName);
      quizRepository.saveAndFlush(quizToRename);
      return quizMapper.entityToDto(quizToRename);
    }
    return null;
  }
  
  @Override
  public QuestionResponseDto getRandomQuestion(Long id) {
    Optional<Quiz> optionalQuiz = quizRepository.findById(id);
    if (optionalQuiz.isPresent()) {
      Quiz quiz = optionalQuiz.get();
      List<Question> questions = quiz.getQuestions();
      if (!questions.isEmpty()) {
        Random random = new Random();
        int randomIndex = random.nextInt(questions.size());
        Question randomQuestion = questions.get(randomIndex);
        return questionMapper.entityToDto(randomQuestion);
      }
    }
    return null;
  }
  
  @Override
  public QuizResponseDto addQuestionToQuiz(Long id, QuestionResponseDto questionDto) {
    Optional<Quiz> optionalQuiz = quizRepository.findById(id);
    if (optionalQuiz.isPresent()) {
      Quiz quiz = optionalQuiz.get();
      Question question = questionMapper.dtoToEntity(questionDto);
      quiz.getQuestions().add(question);
      quizRepository.saveAndFlush(quiz);
      return quizMapper.entityToDto(quiz);
    }
    return null;
  }
  
  
  @Override
  public QuestionResponseDto deleteQuestionFromQuiz(Long quizId, Long questionId) {
      Optional<Quiz> optionalQuiz = quizRepository.findById(quizId);
      if (optionalQuiz.isPresent()) {
          Quiz quiz = optionalQuiz.get();
          List<Question> questions = quiz.getQuestions();

          Question questionToDelete = null;

          for (Question question : questions) {
              if (question.getId().equals(questionId)) {
                  questionToDelete = question;
                  break;
              }
          }

          if (questionToDelete != null) {
              questions.remove(questionToDelete);
              questionRepository.deleteById(questionId);
              quizRepository.saveAndFlush(quiz);
              return questionMapper.entityToDto(questionToDelete);
          }
      }
      
      return null;
  }

	/*
	 * @Override public QuestionResponseDto deleteQuestionFromQuiz(Long quizId, Long
	 * questionId) { Optional<Quiz> optionalQuiz = quizRepository.findById(quizId);
	 * if (optionalQuiz.isPresent()) { Quiz quiz = optionalQuiz.get();
	 * List<Question> questions = quiz.getQuestions();
	 * 
	 * for (Question question : questions) { if
	 * (question.getId().equals(questionId)) { questions.remove(question);
	 * questionRepository.deleteById(questionId); break; } }
	 * 
	 * quizRepository.saveAndFlush(quiz); return
	 * questionMapper.entityToDto(question);
	 * 
	 * }
	 * 
	 * return null; }
	 */
  
}




