import React, { useState, useEffect } from "react";
import axios from "axios";
import { Navigate } from 'react-router-dom';
import { useNavigate } from 'react-router-dom';
// useNavigate is a hook

function QuizPage() {
    const navigate = useNavigate();
    const [quizData, setQuizData] = useState(null);

    useEffect(() => {
        axios.post("http://localhost:8000/showQuestions", {}, { withCredentials: true })
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
        }, { withCredentials: true })
            .then((response) => {
                console.log("Quiz submitted successfully");
                alert("Quiz submitted Successfully");
                navigate("/ResultPage", {
                    state: {
                        score: response.data.score,
                        total: response.data.total,
                        attempted: response.data.attempted
                    }
                })
            })
            .catch((error) => {
                console.error("Error submitting quiz:", error);
                alert("Error submitting quiz. Please try again.");
            });
    };

    return (
        <div className="home-container">
    <div className="form-container">
        <h1>Quiz Questions</h1>
        {Array.isArray(quizData) && quizData.length > 0 ? (
            quizData.map((question, index) => (
                <div key={index} className="question-card">
                    <h4 className="question-text">{index + 1}. {question.question}</h4>
                    <ul className="options-list">
                        {question.options.map((option, optionIndex) => (
                            <li key={optionIndex} className="option-item">
                                <label> {/* The label itself doesn't need a class */}
                                    <input
                                        type="radio"
                                        name={`question-${index}`}
                                        value={optionIndex + 1}
                                        className="option-radio" // Class to hide the radio button
                                    />
                                    {/* This label is styled when the input is checked */}
                                    <span className="option-label">{option}</span>
                                </label>
                            </li>
                        ))}
                    </ul>
                </div>
            ))
        ) : (
            <p className="loading-text">Loading questions...</p>
        )}
        <button type="submit" className="generate-btn" onClick={submitHandel}>Submit</button>
    </div>
</div>
    );
}

export default QuizPage;
