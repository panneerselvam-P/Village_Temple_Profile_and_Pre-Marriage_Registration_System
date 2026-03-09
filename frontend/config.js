const CONFIG = {
    API_BASE_URL: 'http://localhost:8080/api'
};

// Export if using modules, otherwise it remains global
if (typeof module !== 'undefined' && module.exports) {
    module.exports = CONFIG;
}
