// services/cartService.js

const API_BASE_URL = "http://localhost:8080";

/**
 * 提交購物車結帳
 * @param {Array} cartItems 購物車商品清單
 * @returns {Promise<Object>} API 回應
 */
export const checkoutCart = async (cartItems) => {
  const response = await fetch(`${API_BASE_URL}/orders/checkout`, {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
    },
    credentials: "include", // 傳遞 cookies/session
    body: JSON.stringify(cartItems),
  });

  if (!response.ok) {
    throw new Error("結帳失敗");
  }

  return response.json();
};

/**
 * 獲取歷史訂單紀錄
 * @returns {Promise<Object>} API 回應
 */
export const fetchOrderHistory = async () => {
  const response = await fetch(`${API_BASE_URL}/orders`, {
    method: "GET",
    credentials: "include", // 傳遞 cookies/session
  });

  if (!response.ok) {
    throw new Error("無法取得結帳紀錄");
  }

  return response.json();
};
