import React, { useEffect, useState } from "react";
import axios from "axios";
import { useNavigate } from "react-router-dom";




function HomePage() {

    const [email, setEmail] = useState("");
    const [prompt, setPrompt] = useState("");
    const [questionsGenerated, setQuestionsGenerated] = useState(false);
    const [isLoading, setIsLoading] = useState(false);

    const navigate = useNavigate();

    function emailChange(e) {
        setEmail(e.target.value);
    }

    function promptChange(e) {
        setPrompt(e.target.value);
    }

    function generateQuestions(){
        if (!email || !prompt) {
            alert("Please enter both email and prompt.");
            return;
        }
        alert("Gpt will generate questions please wait...");
        setIsLoading(true);
       axios.get("http://localhost:8000/giveQuestions",{
            params:{
                email:email,
                prompt:prompt
            },withCredentials: true
       }).then((response)=>{
        console.log("Questions generated successfully:");
        setQuestionsGenerated(true);
        setIsLoading(false);
       })
        .catch((error) => {
            console.error("Error generating questions:", error);
        });
    }

    function startQuiz(){
        if(questionsGenerated){
            navigate("/quizPage");
        }
    }

    return (
        <div>
            <h1>Welcome to the AI Quiz App</h1>
            <div className="home-content">
                <input type="text" placeholder="Enter your email" value={email} onChange={emailChange} />
                <br></br>
                <input type="text" placeholder="Enter your prompt" value={prompt} onChange={promptChange} />
                <br></br>
                <button onClick={generateQuestions}>Generate Questions</button>
                {isLoading && <h3>GPT Generating questions...</h3>}
                {questionsGenerated && <button onClick={startQuiz}>Start Quiz</button>}
            </div>
        </div>
    );
}

export default HomePage;
