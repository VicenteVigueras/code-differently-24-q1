/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package com.codedifferently.lesson4;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import org.assertj.core.api.SoftAssertions;
import org.assertj.core.api.junit.jupiter.SoftAssertionsExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

@SpringBootTest
@ContextConfiguration(classes = TestConfiguration.class)
@ExtendWith(SoftAssertionsExtension.class)
class Lesson4Test {

  @Value("${quiz.answers}")
  List<AnswerOption> quizAnswers;

  QuizQuestion[] quizQuestions = Lesson4.makeQuizQuestions();

  @Test
  void checkQuizQuestions_areAssembledCorrectly() {
    assertEquals(6, quizQuestions.length, "Expected 6 questions but got " + quizQuestions.length);
    for (int i = 0; i < quizQuestions.length; i++) {
      assertEquals(
          i,
          quizQuestions[i].getQuestionNumber(),
          "Expected question number " + i + " but got " + quizQuestions[i].getQuestionNumber());
    }
  }

  @Test
  void checkQuizAnswers_areAssembledCorrectly() {
    assertEquals(quizQuestions.length, quizAnswers.size());
  }

  @Test
  void checkQuestions_answeredCorrectly() {
    var softly = new SoftAssertions();
    for (QuizQuestion question : quizQuestions) {
      AnswerOption correctAnswer = quizAnswers.get(question.getQuestionNumber());
      AnswerOption actualAnswer = question.getAnswer();
      softly
          .assertThat(actualAnswer)
          .as("Check question " + question.getQuestionNumber() + " is answered correctly")
          .isEqualTo(correctAnswer);
    }
    softly.assertAll();
  }
}