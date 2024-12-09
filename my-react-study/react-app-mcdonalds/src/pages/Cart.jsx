import React, { useState } from "react";
import "./Cart.css";
import { checkoutCart } from "../services/cartService";

function Cart({ cartItems, removeFromCart, clearCart, isLoggedIn }) {
  const [checkoutMessage, setCheckoutMessage] = useState("");

  // 計算總價
  const totalAmount = cartItems.reduce((total, item) => total + item.product.price, 0);

  // 結帳處理邏輯
  const handleCheckout = async () => {
    if (!isLoggedIn) {
      setCheckoutMessage("請先登入以提交結帳");
      return;
    }

    try {
      const apiResponse = await checkoutCart(cartItems); // 結帳處理服務;
      setCheckoutMessage(apiResponse.message);
      clearCart(); // 清空購物車
    } catch (error) {
      console.error("Error during checkout:", error);
      setCheckoutMessage("結帳失敗，請重試");
    }
  };

  return (
    <div className="cart-container">
      <h1>購物車</h1>
      {cartItems.length === 0 ? (
        <p>購物車是空的</p>
      ) : (
        <div className="cart-items">
          <ul>
            {cartItems.map((item, index) => (
              <li key={index} className="cart-item">
                <span>
                  {item.product.name} - ${item.product.price} - 數量: {item.quantity}
                </span>
                <button
                  className="remove-button"
                  onClick={() => removeFromCart(index)}
                >
                  移除
                </button>
              </li>
            ))}
          </ul>
          <h3 className="cart-total">總價: ${totalAmount}</h3>
          <button
            className="checkout-button"
            onClick={handleCheckout}
            disabled={!isLoggedIn}
          >
            提交結帳
          </button>
          {!isLoggedIn && (
            <p className="login-warning">請先登入才能提交結帳。</p>
          )}
        </div>
      )}
      {checkoutMessage && <p className="checkout-message">{checkoutMessage}</p>}
    </div>
  );
}

export default Cart;
