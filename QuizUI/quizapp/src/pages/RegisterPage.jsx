import React from "react";
import axios from "axios";
import { useState } from "react";
import { useNavigate } from "react-router-dom";
export default function Register() {
    const [username, setUsername] = useState("");
    const [password, setPassword] = useState("");
    const navigate = useNavigate(); 
    async function handleRegister(event) {
        event.preventDefault(); 
        const response = await axios.post("http://localhost:8000/register", {
            name: username,
            pass: password
        });
        if(response){
            navigate("/");
        }
    }
    return (    
        <div className="home-container">
        <div className="form-container">
            <h1>Register Page</h1>
            <form onSubmit={handleRegister}>
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
                
                <button type="submit" className="generate-btn">
                    Register
                </button>
                 <a href="/login" className="login-link">Already have an account? Login</a>
            </form>
        </div>
    </div>
    );
}