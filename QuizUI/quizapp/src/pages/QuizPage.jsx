import React, { useState, useEffect } from "react";
import axios from "axios";
import { Navigate } from 'react-router-dom';
import { useNavigate } from 'react-router-dom';
  // useNavigate is a hook

function QuizPage() {
    const navigate = useNavigate();
    const [quizData, setQuizData] = useState(null);

    useEffect(() => {
        axios.post("http://localhost:8000/showQuestions",{},{withCredentials: true})
            .then((response) => {
                setQuizData(response.data);
                console.log("Quiz data fetched successfully:", response.data);
            })
            .catch((error) => {
                console.error("Error fetching quiz data:", error);
            });
    }, []);

    const submitHandel = () => {
        const selectedAnswers = [];

        quizData.forEach((question, index) => {
            const selectedOption = document.querySelector(
                `input[name="question-${index}"]:checked`
            );
            selectedAnswers[index] = selectedOption ? String(selectedOption.value) : null;
        });

        axios.post("http://localhost:8000/submit", {
            answers: selectedAnswers
        },{withCredentials: true})
            .then((response) => {
                console.log("Quiz submitted successfully");
                alert("Quiz submitted Successfully");
                navigate("/ResultPage",{
                    state:{
                        score:response.data.score,
                        total:response.data.total,
                        attempted:response.data.attempted
                    }
                })
            })
            .catch((error) => {
                console.error("Error submitting quiz:", error);
                alert("Error submitting quiz. Please try again.");
            });
    };

    return (
        <div>
            <h1>Quiz Questions</h1>
            {Array.isArray(quizData) && quizData.length > 0 ? (
                quizData.map((question, index) => (
                    <div key={index}>
                        <h4>{index + 1}. {question.question}</h4>
                        <ul style={{ listStyleType: "none", padding: 0 }}>
                            {question.options.map((option, optionIndex) => (
                                <li key={optionIndex}>
                                    <label>
                                        <input
                                            type="radio"
                                            name={`question-${index}`}
                                            value={optionIndex + 1}
                                        />
                                        {` ${option}`}
                                    </label>
                                </li>
                            ))}
                        </ul>
                    </div>
                ))
            ) : (
                <p>Loading questions...</p>
            )}
            <button type="submit" onClick={submitHandel}>Submit</button>
        </div>
    );
}

export default QuizPage;
