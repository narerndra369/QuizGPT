import React, { useEffect } from "react";
import { useLocation } from "react-router-dom";

function ResultPage() {
    const location = useLocation();
    const { score, total, attempted } = location.state || { score: 0, total: 0 };
    useEffect(() => {
        sessionStorage.clear();
    }, []);
    return(
        <div>
            <h1>Quiz Results</h1>
            <p>Your Score: {score}</p>
            <p>Attempted Questions: {attempted}</p>
            <p>Total Questions: {total}</p>
            <input type="button" onClick={()=>window.location.href = "/"} value="Go to Home" /> 
        </div>
    )
}

export default ResultPage;
