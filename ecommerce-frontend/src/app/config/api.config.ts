export const API_CONFIG = {
  PRODUCTS_BASE_URL: 'http://localhost:8081',
  ORDERS_BASE_URL: 'http://localhost:8080',
  ENDPOINTS: {
    PRODUCTS: '/products',
    ORDERS: '/api/orders'
  }
};

export const getProductsApiUrl = (endpoint: string): string => {
  return `${API_CONFIG.PRODUCTS_BASE_URL}${endpoint}`;
};

export const getOrdersApiUrl = (endpoint: string): string => {
  return `${API_CONFIG.ORDERS_BASE_URL}${endpoint}`;
};
