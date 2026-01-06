import axios from 'axios';

const API_BASE_URL = '/api';

const api = axios.create({
    baseURL: API_BASE_URL,
    headers: {
        'Content-Type': 'application/json',
    },
});

export const ligneService = {
    getAll: () => api.get('/lignes'),
    getById: (id) => api.get(`/lignes/${id}`),
    create: (data) => api.post('/lignes', data),
    update: (id, data) => api.put(`/lignes/${id}`, data),
    delete: (id) => api.delete(`/lignes/${id}`),
};

export const stationService = {
    getAll: () => api.get('/stations'),
    getById: (id) => api.get(`/stations/${id}`),
    getByLigne: (ligneId) => api.get(`/stations/ligne/${ligneId}`),
    create: (data) => api.post('/stations', data),
    update: (id, data) => api.put(`/stations/${id}`, data),
    delete: (id) => api.delete(`/stations/${id}`),
};

export const horaireService = {
    getAll: () => api.get('/horaires'),
    getById: (id) => api.get(`/horaires/${id}`),
    getByLigne: (ligneId) => api.get(`/horaires/ligne/${ligneId}`),
    create: (data) => api.post('/horaires', data),
    update: (id, data) => api.put(`/horaires/${id}`, data),
    delete: (id) => api.delete(`/horaires/${id}`),
};

export default api;
