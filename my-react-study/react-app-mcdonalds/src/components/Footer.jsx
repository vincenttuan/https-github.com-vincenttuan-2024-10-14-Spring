import React from "react";
import "./Footer.css";

function Footer() {
  return (
    <footer className="footer">
      <p>&copy; {new Date().getFullYear()} 購物車範例. All rights reserved.</p>
      <div className="footer-links">
        <a href="/about">關於我們</a>
        <a href="/contact">聯絡我們</a>
        <a href="/privacy">隱私政策</a>
      </div>
    </footer>
  );
}

export default Footer;
