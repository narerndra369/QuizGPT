import axios from "axios";
import React,{useState} from "react";
import { useNavigate } from "react-router-dom";

export default function Login() {
    const [username, setUsername] = useState("");
    const [password, setPassword] = useState("");
    const navigate = useNavigate();
    async function handleLogin() {
        const response = await axios.post("http://localhost:8000/login", {
            name: username,
            pass: password
        });
        if(response){
            localStorage.setItem("token", response.data);
            axios.defaults.headers.common['Authorization'] = `Bearer ${response.data}`;
            navigate("/quizgpt");
        }
        console.log(response.data);
    }

    return (
        <div className="home-container">
        <div className="form-container">
            <h1>Login Page</h1>
            <input
                type="text"
                placeholder="Username"
                onChange={(e) => setUsername(e.target.value)}
            />
            <input
                type="password"
                placeholder="Password"
                onChange={(e) => setPassword(e.target.value)}
            />
            <button className="generate-btn" onClick={handleLogin}>
                Login
            </button>
            <a href="/register" className="register-link">Don't have an account? Register</a>
        </div>
    </div>
    );
}