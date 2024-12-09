// services/authService.js

const API_BASE_URL = "http://localhost:8080";

/**
 * 檢查登入狀態
 * @returns {Promise<Object>} 包含登入狀態的 API 回應
 */
export const checkLoginStatus = async () => {
  const response = await fetch(`${API_BASE_URL}/auth/isLoggedIn`, {
    method: "GET",
    credentials: "include",
  });

  if (!response.ok) {
    throw new Error("無法取得登入狀態");
  }

  return response.json();
};

/**
 * 登入
 * @param {string} username 用戶名
 * @param {string} password 密碼
 * @returns {Promise<Object>} 包含登入結果的 API 回應
 */
export const login = async (username, password) => {
  const response = await fetch(`${API_BASE_URL}/auth/login`, {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
    },
    credentials: "include",
    body: JSON.stringify({ username, password }),
  });

  if (!response.ok) {
    throw new Error("登入失敗");
  }

  return response.json();
};

/**
 * 登出
 * @returns {Promise<Object>} 包含登出結果的 API 回應
 */
export const logout = async () => {
  const response = await fetch(`${API_BASE_URL}/auth/logout`, {
    method: "GET",
    credentials: "include",
  });

  if (!response.ok) {
    throw new Error("登出失敗");
  }

  return response.json();
};
