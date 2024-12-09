import React, { useState } from "react";
import "./LoginPage.css";

function LoginPage({ onLogin }) {
  const [username, setUsername] = useState("admin");
  const [password, setPassword] = useState("1234");
  
  const handleSubmit = (e) => {
    e.preventDefault();
    onLogin(username, password); // 呼叫 onLogin 進行登入驗證
  };

  return (
    <div className="login-page">
      <h2>登入</h2>
      <form onSubmit={handleSubmit}>
        <div className="form-group">
          <label htmlFor="username">帳號：</label>
          <input
            type="text"
            id="username"
            value={username}
            onChange={(e) => setUsername(e.target.value)}
            required
          />
        </div>
        <div className="form-group">
          <label htmlFor="password">密碼：</label>
          <input
            type="password"
            id="password"
            value={password}
            onChange={(e) => setPassword(e.target.value)}
            required
          />
        </div>
        <button type="submit" className="login-button">
          登入
        </button>
      </form>
    </div>
  );
}

export default LoginPage;
