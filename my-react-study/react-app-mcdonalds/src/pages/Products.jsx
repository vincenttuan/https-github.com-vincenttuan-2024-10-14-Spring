import React, { useState, useEffect } from "react";
import "./Products.css";
import { fetchProducts, addProduct } from "../services/productService";
import { addFavorite, removeFavorite, fetchFavorites } from "../services/favoriteService";

function Products({ addToCart, isLoggedIn }) {
  const [products, setProducts] = useState([]);
  const [newProductName, setNewProductName] = useState("");
  const [newProductPrice, setNewProductPrice] = useState("");
  const [newProductImageBase64, setNewProductImageBase64] = useState("");
  const [favorites, setFavorites] = useState(new Set()); // 用 Set 儲存關注的商品 ID

  // 使用 useEffect 從 REST API 獲取商品資料
  useEffect(() => {
    const loadProducts = async () => {
      try {
        const apiResponse = await fetchProducts(); // 使用查詢所有商品服務方法
        setProducts(apiResponse.data);
        // 使用者的關注清單
        if (isLoggedIn) {
          const favoritesResponse = await fetchFavorites(); // 查詢使用者的關注清單
          const favoriteIds = new Set(favoritesResponse.data.map((fav) => fav.id));
          setFavorites(favoriteIds);
        }
      } catch (error) {
        console.error("Error fetching products:", error);
      }
    };

    loadProducts();
  }, []);

  const handleAddProduct = async () => {
    // 建立新商品物件
    const newProduct = {
      name: newProductName,
      price: parseFloat(newProductPrice),
      imageBase64: newProductImageBase64,
    };

    try {
      const savedProduct = await addProduct(newProduct); // 使用新增商品服務方法
      // 將新商品加入到商品列表
      setProducts([...products, savedProduct.data]);

      // 清空表單輸入框
      setNewProductName("");
      setNewProductPrice("");
      setNewProductImageBase64("");
    } catch (error) {
      console.error("Error adding product:", error);
    }
  };

  const handleImageUpload = (e) => {
    const file = e.target.files[0];
    if (file) {
      const reader = new FileReader();
      reader.onloadend = () => {
        console.log(reader.result);
        setNewProductImageBase64(reader.result);
      };
      // readAsDataURL 方法會將指定的檔案讀取為Data URL 格式，也就是 Base64 編碼形式的字串
      // 執行 reader.readAsDataURL(file) 後，當檔案讀取操作完成時，FileReader 物件的 onloadend 事件會被觸發。 
      reader.readAsDataURL(file); 
    }
  };

  // 處理關注
  const handleFavoriteToggle = async (productId) => {
    try {
      if (favorites.has(productId)) {
        await removeFavorite(productId); // 執行移除關注
        const updatedFavorites = new Set(favorites);
        updatedFavorites.delete(productId);
        setFavorites(updatedFavorites);
      } else {
        await addFavorite(productId); // 執行關注
        const updatedFavorites = new Set(favorites);
        updatedFavorites.add(productId);
        setFavorites(updatedFavorites);
      }
    } catch (error) {
      console.error("Error toggling favorite:", error);
    }
  };

  return (
    <div className="products-container">
      <div className="add-product-form">
        <h2>新增商品</h2>
        <input
          type="text"
          placeholder="商品名稱"
          value={newProductName}
          onChange={(e) => setNewProductName(e.target.value)}
        />
        <input
          type="number"
          placeholder="價格"
          value={newProductPrice}
          onChange={(e) => setNewProductPrice(e.target.value)}
        />
        <input
          type="file"
          accept="image/*"
          onChange={handleImageUpload}
        />
        {isLoggedIn && (
          <button onClick={handleAddProduct} >新增商品</button>
        )}
        
      </div>
      <div className="product-list">
        <h1>商品列表</h1>
        {products.length === 0 ? (
          <p>無商品資料...</p>
        ) : (
          <ul>
            {products.map((product) => (
              <li key={product.id}>
                <span>
                <img src={product.imageBase64} alt={product.name} valign="middle"/> {product.name} - ${product.price}
                </span>
                {isLoggedIn && (
                  <>
                  <button onClick={() => addToCart(product)} >
                    加入購物車
                  </button>
                  &nbsp;&nbsp;
                  <button
                    className={`favorite-button ${favorites.has(product.id) ? "unfollow" : "follow"}`}
                    onClick={() => handleFavoriteToggle(product.id)}
                  >
                    {favorites.has(product.id) ? "移除關注" : "加入關注"}
                  </button>
                  </>
                )}
              </li>
            ))}
          </ul>
        )}
      </div>
    </div>
  );
}

export default Products;
