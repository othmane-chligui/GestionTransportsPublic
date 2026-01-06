import React, { useState, useEffect } from 'react';
import { stationService, ligneService } from '../services/api';

const Stations = () => {
    const [stations, setStations] = useState([]);
    const [lignes, setLignes] = useState([]);
    const [formData, setFormData] = useState({ nom: '', adresse: '', ligneId: '' });
    const [editingId, setEditingId] = useState(null);

    useEffect(() => {
        fetchStations();
        fetchLignes();
    }, []);

    const fetchStations = async () => {
        try {
            const response = await stationService.getAll();
            setStations(response.data);
        } catch (error) {
            console.error("Error fetching stations", error);
        }
    };

    const fetchLignes = async () => {
        try {
            const response = await ligneService.getAll();
            setLignes(response.data);
        } catch (error) {
            console.error("Error fetching lignes", error);
        }
    };

    const handleSubmit = async (e) => {
        e.preventDefault();
        try {
            if (editingId) {
                await stationService.update(editingId, formData);
            } else {
                await stationService.create(formData);
            }
            setFormData({ nom: '', adresse: '', ligneId: '' });
            setEditingId(null);
            fetchStations();
        } catch (error) {
            console.error("Error saving station", error);
        }
    };

    const handleEdit = (station) => {
        const foundLigne = lignes.find(l => l.nom === station.nomLigne);
        setFormData({ 
            nom: station.nom, 
            adresse: station.adresse, 
            ligneId: foundLigne ? foundLigne.id : '' 
        });
        setEditingId(station.id);
    };

    const handleDelete = async (id) => {
        if (window.confirm("Supprimer cette station ?")) {
            try {
                await stationService.delete(id);
                fetchStations();
            } catch (error) {
                console.error("Error deleting station", error);
            }
        }
    };

    return (
        <div className="container">
            <h1>Gestion des Stations</h1>
            
            <form onSubmit={handleSubmit} className="form-card">
                <input 
                    type="text" 
                    placeholder="Nom de la station" 
                    value={formData.nom} 
                    onChange={(e) => setFormData({...formData, nom: e.target.value})}
                    required
                />
                <input 
                    type="text" 
                    placeholder="Adresse" 
                    value={formData.adresse} 
                    onChange={(e) => setFormData({...formData, adresse: e.target.value})}
                    required
                />
                <select 
                    value={formData.ligneId} 
                    onChange={(e) => setFormData({...formData, ligneId: e.target.value})}
                    required
                >
                    <option value="">Sélectionner une ligne</option>
                    {lignes.map(l => (
                        <option key={l.id} value={l.id}>{l.nom} ({l.type})</option>
                    ))}
                </select>
                <button type="submit">{editingId ? "Modifier" : "Ajouter"}</button>
            </form>

            <table>
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Nom</th>
                        <th>Adresse</th>
                        <th>Ligne</th>
                        <th>Actions</th>
                    </tr>
                </thead>
                <tbody>
                    {stations.map(s => (
                        <tr key={s.id}>
                            <td>{s.id}</td>
                            <td>{s.nom}</td>
                            <td>{s.adresse}</td>
                            <td>{s.nomLigne}</td>
                            <td>
                                <button onClick={() => handleEdit(s)}>Modifier</button>
                                <button onClick={() => handleDelete(s.id)}>Supprimer</button>
                            </td>
                        </tr>
                    ))}
                </tbody>
            </table>
        </div>
    );
};

export default Stations;
