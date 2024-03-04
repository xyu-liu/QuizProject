package com.example.quizproject.controller;

import com.example.quizproject.domain.*;
import com.example.quizproject.service.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private UserService userService;

    @Autowired
    private ContactService contactService;

    @Autowired
    private QuizService quizService;

    @Autowired
    private QuizQuestionService quizQuestionService;

    @Autowired
    private QuestionService questionService;

    @Autowired
    private ChoiceService choiceService;

    @GetMapping("/homepage")
    public String adminHomePage(HttpServletRequest request, Model model) {
        return "adminHomepage";
    }

    @GetMapping("/userManagement")
    public String userManage(HttpServletRequest request, Model model) {
        List<User> allUsers = this.userService.getAllUsers();
        model.addAttribute("users", allUsers);
        return "userManage";
    }

    @GetMapping("/userManagement/changeStatus")
    public String changeStatus(@RequestParam int user_id, HttpServletRequest request, Model model) {
        this.userService.updateStatusById(user_id);
        return "redirect:/admin/userManagement";
    }

    @GetMapping("/quizResult")
    public String quizResult(@RequestParam(name = "user_id", required = false, defaultValue = "-1") int user_id, @RequestParam(name = "category_id", required = false, defaultValue = "-1") int category_id, HttpServletRequest request, Model model) {
        List<Quiz> allQuiz = this.quizService.getAllQuiz();

        if (user_id != -1) {
            allQuiz = allQuiz.stream().filter(x -> x.getUser_id() == user_id).collect(Collectors.toList());
        }

        if (category_id != -1) {
            allQuiz = allQuiz.stream().filter(x -> x.getCategory_id() == category_id).collect(Collectors.toList());
        }

        model.addAttribute("quizs", allQuiz);
        return "adminQuizResult";
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
        boolean pass = correct_counter >= 3 ? true : false;
        model.addAttribute("pass", pass);
        model.addAttribute("quiz", quiz);
        User user = this.userService.getUserById(quiz.getUser_id());
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

        return "/adminQuizDetail";
    }


    @GetMapping("/questionManagement")
    public String questionManage(HttpServletRequest request, Model model) {
        List<Question> allQuestions = this.questionService.getAllQuestions();
        model.addAttribute("questions", allQuestions);
        return "questionManage";
    }

    @GetMapping("/questionManagement/changeStatus")
    public String changeQuestionStatus(@RequestParam int question_id, HttpServletRequest request, Model model) {
        this.questionService.changeStatusById(question_id);
        return "redirect:/admin/questionManagement";
    }

    @GetMapping("/contactManagement")
    public String contactManage(HttpServletRequest request, Model model) {
        List<Contact> allContacts = this.contactService.getAllContacts();
        model.addAttribute("contacts", allContacts);
        return "contactManage";
    }

    @GetMapping("/userManagement/add")
    public String addNewQuestion(HttpServletRequest request, Model model) {
        List<Category> allCategory = this.categoryService.getAllCategory();
        model.addAttribute("categories", allCategory);
        return "addNewQuestion";
    }

    @PostMapping("/userManagement/add")
    public String submitNewQuestion(@RequestParam int category_id, @RequestParam String description,  HttpServletRequest request, Model model) {
        this.questionService.addNewQuestion(category_id, description);
        return "redirect:/admin/questionManagement";
    }
}
