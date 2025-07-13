import React from 'react';
import './App.css';
import { Routes, Route} from 'react-router-dom';
import HomePage from './pages/HomePage';
import Quiz from './pages/QuizPage';
import Result from './pages/ResultPage';

function App() {
  return (
    <div className="App">
      <Routes>
        <Route path="/" element={<HomePage />} />
        <Route path="/quizPage" element={<Quiz />} />
        <Route path="/resultPage" element={<Result />} />
      </Routes>
    </div>
  );
}

export default App;
