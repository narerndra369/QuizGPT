import React from "react";
import axios from "axios";
import { useNavigate } from "react-router-dom";

export default function LogoutPage() {
    const navigate = useNavigate();
    const handleLogout = () => {
        localStorage.removeItem("token");
        axios.defaults.headers.common['Authorization'] = '';
        navigate("/");
    };

    return (
        <div className="home-container">
            <div className="form-container">
                <h1>Logout Page</h1>
                <p className="logout-message">You have been logged out successfully.</p>
                <button className="generate-btn" onClick={handleLogout}>
                    Go to Login
                </button>
            </div>
        </div>
    );
}