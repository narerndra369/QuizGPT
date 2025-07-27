import React, { useEffect } from "react";
import { useLocation,useNavigate } from "react-router-dom";

function ResultPage() {
    const location = useLocation();
    const { score, total, attempted } = location.state || { score: 0, total: 0 };
    const navigate = useNavigate();
    useEffect(() => {
        sessionStorage.clear();
    }, []);
    return (
        <div className="home-container">
        <div className="form-container results-container">
            <h1>Quiz Results</h1>
            <div className="score-display">
                <p>Your Score</p>
                <span>{score}</span>
            </div>
            <div className="stats-container">
                <p>Attempted Questions: <strong>{attempted}</strong></p>
                <p>Total Questions: <strong>{total}</strong></p>
            </div>
            <div className="button-group">
                <button
                    className="generate-btn"
                    onClick={() => window.location.href = "/quizgpt"}
                >
                    Go to Home
                </button>
                <button
                    className="logout-btn"
                    onClick={() => navigate("/logout")}
                >
                    Logout
                </button>
            </div>
        </div>
    </div>
    )
}

export default ResultPage;
