package com.example.quizproject.controller;

import com.example.quizproject.dao.CategoryDao;
import com.example.quizproject.dao.QuizDao;
import com.example.quizproject.domain.*;
import com.example.quizproject.service.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserPageController {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private QuizService quizService;

    @Autowired
    private QuizQuestionService quizQuestionService;

    @Autowired
    private ChoiceService choiceService;

    @Autowired
    private QuestionService questionService;

    @GetMapping("/homepage")
    public String userHomePage(HttpServletRequest request, Model model) {
        List<Category> allCategory = categoryService.getAllCategory();

        model.addAttribute("categories", allCategory);

        HttpSession session = request.getSession(false);
        User user = (User) session.getAttribute("user");
        System.out.println(user);

        List<Quiz> allQuizResultByUser = this.quizService.getAllQuizResultByUser(user.getUser_id());


        model.addAttribute("pastQuiz", allQuizResultByUser);

        return "userHomepage";

    }

    @GetMapping("/quizDetail")
    public String QuizResult(@RequestParam int quiz_id, HttpServletRequest request, Model model) {
        Quiz quiz = this.quizService.getQuizById(quiz_id);
        List<QuizQuestion> quizQuestions = this.quizQuestionService.getQuizQuestionsByQuizId(quiz_id);
        int correct_counter = 0;
        for (QuizQuestion quizQuestion : quizQuestions) {
            if (choiceService.isCorrect(quizQuestion.getUser_choice_id())) {
                correct_counter++;
            }
        }
        boolean pass = correct_counter>=3 ? true: false;
        model.addAttribute("pass", pass);
        model.addAttribute("quiz", quiz);
        HttpSession session = request.getSession(false);
        User user = (User) session.getAttribute("user");
        model.addAttribute("user", user);



        HashMap<String, List<String>> QAMap = new HashMap<>();

        for (QuizQuestion quizQuestion : quizQuestions) {
            int questionId = quizQuestion.getQuestion_id();
            String content = this.questionService.getDescriptionByQuestionId(questionId);
            List<Choice> optionsC = this.choiceService.getChoiceByQuestionId(questionId);
            String options = "";
            for (int i = 0; i < optionsC.size(); i++) {
                options += i + ": " + optionsC.get(i).getDescription() + "\n";
            }
            String selected = this.choiceService.getChoiceById(quizQuestion.getUser_choice_id()).getDescription();
            String correct = this.choiceService.getRightChoiceByQuestionId(questionId).getDescription();

            List<String> list = new ArrayList<>();
            list.add(options);
            list.add(selected);
            list.add(correct);

            QAMap.put(content, list);
        }
        model.addAttribute("QAMap", QAMap);

        return "quizDetail";




    }
}
