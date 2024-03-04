package com.example.quizproject.controller;

import com.example.quizproject.domain.*;
import com.example.quizproject.service.ChoiceService;
import com.example.quizproject.service.QuestionService;
import com.example.quizproject.service.QuizQuestionService;
import com.example.quizproject.service.QuizService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping("/quiz")
public class QuizController {

    @Autowired
    private QuizService quizService;

    @Autowired
    private QuestionService questionService;

    @Autowired
    private QuizQuestionService quizQuestionService;

    @Autowired
    private ChoiceService choiceService;

    @GetMapping("/new")
    public String createNewQuizByCategory(@RequestParam int category_id,
                                          HttpServletRequest request, Model model) {

        HttpSession session = request.getSession(false);

        if (session.getAttribute("ongoingQuiz") != null) {
            int quiz_id = (int) session.getAttribute("ongoingQuiz");
            return "redirect:/quiz/ongoing?quiz_id="+quiz_id;
        }



        //Create New Quiz
        User user = (User) session.getAttribute("user");
        int quizID = this.quizService.createQuizByCategoryAndUser(user.getUser_id(), category_id);
        session.setAttribute("ongoingQuiz", quizID);


        //Generate Five Four this Quiz
        List<Question> fiveQuestionsByCategory = this.questionService.getFiveQuestionsByCategory(category_id);
        this.quizQuestionService.createNewQuizQuestions(quizID, fiveQuestionsByCategory);



        //Redirect to the page
        return "redirect:/quiz/ongoing?quiz_id="+quizID;
    }

    @GetMapping("/ongoing")
    public String onGoingQuizPage(@RequestParam int quiz_id,
                                          HttpServletRequest request, Model model) {


        List<QuizQuestion> quizQuestions = this.quizQuestionService.getQuizQuestionsByQuizId(quiz_id);
        List<Question> questions = this.questionService.getQuestionsByQQ(quizQuestions);
        HashMap<QuizQuestionInOnGoingQuiz, List<Choice>> QAMap = new HashMap<>();
        for (QuizQuestion quizQuestion : quizQuestions) {
            String description = Question.getDescription(questions, quizQuestion.getQuestion_id());
            QuizQuestionInOnGoingQuiz quizQuestionInOnGoingQuiz = new QuizQuestionInOnGoingQuiz(quizQuestion.getQq_id(), quizQuestion.getQuiz_id(), quizQuestion.getQuestion_id(), description);
            List<Choice> choices = this.choiceService.getChoiceByQuestionId(quizQuestion.getQuestion_id());
            QAMap.put(quizQuestionInOnGoingQuiz,choices);
        }

        model.addAttribute("QA", QAMap);
        model.addAttribute("ongoing", quiz_id);
        return "quiz";
    }

    @PostMapping ("/submitAnswers")public String processSubmittedQuiz(@RequestParam int quiz_id,
                                                                 @RequestParam int answer_0,
                                                                 @RequestParam int answer_1,
                                                                 @RequestParam int answer_2,
                                                                 @RequestParam int answer_3,
                                                                 @RequestParam int answer_4,
                                                                 HttpServletRequest request, Model model) {
        int[] choice_ids = {answer_0, answer_1, answer_2, answer_3, answer_4};
        int choice_id = 0;

        for (int i = 0; i < choice_ids.length; i++) {
            choice_id = choice_ids[i];
            int questionId = this.choiceService.getQuestionIdByChoiceID(choice_id);
            this.quizQuestionService.addUserChoice(choice_id, quiz_id, questionId);
        }

        this.quizService.updateQuizEndTime(quiz_id, new Timestamp(System.currentTimeMillis()));
        return "redirect:/user/homepage";

    }


}
