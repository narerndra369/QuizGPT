package com.narendra.SpringAIDemo;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.narendra.models.Quiz;
import com.narendra.models.QuizService;
import com.narendra.models.UserEntity;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.ollama.OllamaChatModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

@CrossOrigin(origins = "http://localhost:3000",allowCredentials = "true")
@RestController
public class OllamaController {

    private final ChatClient chatClient;

    
	@Autowired
    private  com.narendra.SpringAIDemo.Authentication.MyUserDetailsService userDetailsService;
	@Autowired
    private AuthenticationManager authenticationManager;
	@Autowired
	com.narendra.SpringAIDemo.Authentication.JWTService jwtService;
	
	
	
	@PostMapping("/register")
	public String register(@RequestBody UserEntity user) {
		return userDetailsService.register(user);
	}
	
	@PostMapping("/login")
	public String login(@RequestBody UserEntity user) {
		 try {
	            Authentication auth = authenticationManager.authenticate(
	                new UsernamePasswordAuthenticationToken(user.getName(), user.getPass())
	            );
	            if(auth.isAuthenticated()) {
	            	return jwtService.generateToken(user.getName());
	            }
	            return auth.isAuthenticated() ? "success" : "fail";
	        } catch (Exception e) {
	        	e.printStackTrace();
	            return "fail";
	        }
	}
    

    @Autowired
    private QuizService db;

    public OllamaController(OllamaChatModel chatModel) {
        this.chatClient = ChatClient.create(chatModel);
    }

    @CrossOrigin(origins = "http://localhost:3000",allowCredentials = "true")
    @GetMapping("/")
    public ResponseEntity<String> home() {
    	
        return ResponseEntity.ok("Quiz Generator Running");
    }
    
    @CrossOrigin(origins = "http://localhost:3000",allowCredentials = "true")
    @GetMapping("/giveQuestions")
    public ResponseEntity<String> getAnswer(@RequestParam String prompt, @RequestParam String email, HttpSession session) {
    	System.out.println("Got Request /GiveQuestions");
        String finalPrompt = """
            You are an AI quiz generator. Generate if the number does not mention in the prompt then give 5 other wise give no of questions in prompt multiple-choice questions on the following topic: `prompt starts here` """ + prompt + """
            `prompt ends here`
            Return the questions in the exact JSON format shown below  but dont give this question this is an example to generrate the question format:

            [
              {
                "question": "What is JVM?",
                "options": [
                  "1.Java Virtual Machine",
                  "2.Java Very Much",
                  "3.Just Virtual Mode",
                  "4.None of the above"
                ],
                "correctAnswer": "1"
              }
            ]

            Instructions:
            - the response generated is passing to the mapper.readTree(response) it will error while it is not in the  Json form
            - Only return a JSON array of questions.
            - Do not include explanations, notes, or markdown.
            - Each question must include 4 options labeled from "1." to "4."
            - The correctAnswer should be one of "1", "2", "3", or "4".
            - Do not include ``` or `json` or any headings.
            - The output should start with [ and end with ].
        """;

        // Get Ollama response
        
        String response = chatClient.prompt(finalPrompt).call().content();
        
       // String response = "[{\"question\":\"What is JVM?\",\"options\":[\"1.Java Virtual Machine\",\"2.Java Very Much\",\"3.Just Virtual Mode\",\"4.None of the above\"],\"correctAnswer\":\"1\"},{\"question\":\"What is the primary role of the JVM?\",\"options\":[\"1. To execute Java code.\",\"2. To create Java projects.\",\"3. To write documentation for Java applications.\",\"4. To manage Java libraries.\"],\"correctAnswer\":\"1\"},{\"question\":\"What does the JVM stand for?\",\"options\":[\"1. Java Virtual Machine\",\"2. Java Very Machine\",\"3. Just Virtual Mode\",\"4. None of the above\"],\"correctAnswer\":\"1\"}]";

        
        System.out.println("Raw Ollama Response:\n" + response);

        // Clean JSON
        String cleanJson = extractJsonArray(response);
       // System.out.println("Extracted JSON:\n" + cleanJson);

        // Parse JSON
        JsonNode jsonNode = parseJson(cleanJson);
        session.setAttribute("Questions", jsonNode);
        session.setAttribute("email", email);
        System.out.println("Session ID (store): " + session.getId());

        return ResponseEntity.ok("Questions generated and stored in session.");
    }

    @CrossOrigin(origins = "http://localhost:3000",allowCredentials = "true")
    @PostMapping("/showQuestions")
    public ResponseEntity<JsonNode> showQuestions(HttpSession session) {
    	System.out.println("Got Request .showQuestions");
        System.out.println("Session ID (retrieve): " + session.getId());
        JsonNode questions = (JsonNode) session.getAttribute("Questions");

        if (questions == null) {
            System.out.println("No Questions Found in Session");
            return ResponseEntity.badRequest().body(null);
        }

        return ResponseEntity.ok(questions);
    }

    @CrossOrigin(origins = "http://localhost:3000",allowCredentials = "true")
    @PostMapping("/submit")
    public ResponseEntity<JsonNode> submitQuiz(@RequestBody JsonNode requestBody, HttpSession session) {
        JsonNode questions = (JsonNode) session.getAttribute("Questions");
        JsonNode answers = requestBody.get("answers");
    	System.out.println("Got Request /submit");
        int correct = 0;
        int i = 0;

        ObjectMapper mapper = new ObjectMapper();
        ArrayNode quizArray = mapper.createArrayNode();

        if (questions != null && answers != null && answers.isArray()) {
            for (JsonNode question : questions) {
                String correctAnswer = question.get("correctAnswer").asText();
                String userAnswer = answers.get(i++).asText();
                ObjectNode quiz = mapper.createObjectNode();
                quiz.put("question", question.get("question").asText());
                quiz.set("options", question.get("options"));
                quiz.put("correctAnswer", correctAnswer);
                quiz.put("userAnswer", userAnswer);
                quiz.put("status", correctAnswer.equals(userAnswer) ? "CorrectAnswer" : "WrongAnswer");

                if (correctAnswer.equals(userAnswer)) {
                    correct++;
                }

                quizArray.add(quiz);
            }
        }
        
        LocalDate localDate = LocalDate.now();

	     // Convert to java.util.Date
	     Date date = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
	     System.out.println(date);
        
        // Save quiz result
        Quiz q = new Quiz("nare6301@gmail.com", quizArray, correct,date);
        
        db.addQuiz(q);

        // Prepare response
        ObjectNode result = mapper.createObjectNode();
        result.put("score", correct);
        result.put("attempted", i);
        result.put("total", questions.size()) ;
        return ResponseEntity.ok(result);
    }

    // Helper: Extract JSON array from mixed response
    private static String extractJsonArray(String text) {
        int start = text.indexOf('[');
        int end = text.lastIndexOf(']') + 1;

        if (start >= 0 && end > start) {
            return text.substring(start, end);
        } else {
            throw new RuntimeException("JSON array not found in response.");
        }
    }

    // Helper: Parse string to JsonNode
    private static JsonNode parseJson(String response) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.readTree(response);
        } catch (IOException e) {
            throw new RuntimeException("Invalid JSON format", e);
        }
    }
}
