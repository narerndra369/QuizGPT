import React from 'react';
import './App.css';
import { Routes, Route} from 'react-router-dom';
import HomePage from './pages/HomePage';
import Quiz from './pages/QuizPage';
import Result from './pages/ResultPage';
import Login from './pages/Login';
import Register from './pages/RegisterPage';
import LogoutPage from './pages/LogoutPage';
function App() {
  return (
    <div className="App">
      <Routes>
        <Route path="/" element={<Login />} />
        <Route path="/Register" element={<Register />} />
        <Route path="/Logout" element={<LogoutPage />} />
        <Route path="/quizgpt" element={<HomePage />} />
        <Route path="/quizPage" element={<Quiz />} />
        <Route path="/resultPage" element={<Result />} />
      </Routes>
    </div>
  );
}

export default App;
