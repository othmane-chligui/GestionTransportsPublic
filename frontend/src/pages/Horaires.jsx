import React, { useState, useEffect } from 'react';
import { horaireService, ligneService } from '../services/api';

const Horaires = () => {
    const [horaires, setHoraires] = useState([]);
    const [lignes, setLignes] = useState([]);
    const [formData, setFormData] = useState({ ligneId: '', heureDepart: '', heureArrivee: '' });
    const [editingId, setEditingId] = useState(null);

    useEffect(() => {
        fetchHoraires();
        fetchLignes();
    }, []);

    const fetchHoraires = async () => {
        try {
            const response = await horaireService.getAll();
            setHoraires(response.data);
        } catch (error) {
            console.error("Error fetching horaires", error);
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
                await horaireService.update(editingId, formData);
            } else {
                await horaireService.create(formData);
            }
            setFormData({ ligneId: '', heureDepart: '', heureArrivee: '' });
            setEditingId(null);
            fetchHoraires();
        } catch (error) {
            console.error("Error saving horaire", error);
        }
    };

    const handleEdit = (horaire) => {
        const foundLigne = lignes.find(l => l.nom === horaire.nomLigne);
        setFormData({ 
            ligneId: foundLigne ? foundLigne.id : '', 
            heureDepart: horaire.heureDepart, 
            heureArrivee: horaire.heureArrivee 
        });
        setEditingId(horaire.id);
    };

    const handleDelete = async (id) => {
        if (window.confirm("Supprimer cet horaire ?")) {
            try {
                await horaireService.delete(id);
                fetchHoraires();
            } catch (error) {
                console.error("Error deleting horaire", error);
            }
        }
    };

    return (
        <div className="container">
            <h1>Gestion des Horaires</h1>
            
            <form onSubmit={handleSubmit} className="form-card">
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
                <div>
                    <label>Heure Départ:</label>
                    <input 
                        type="time" 
                        value={formData.heureDepart} 
                        onChange={(e) => setFormData({...formData, heureDepart: e.target.value + ":00"})}
                        required
                    />
                </div>
                <div>
                    <label>Heure Arrivée:</label>
                    <input 
                        type="time" 
                        value={formData.heureArrivee} 
                        onChange={(e) => setFormData({...formData, heureArrivee: e.target.value + ":00"})}
                        required
                    />
                </div>
                <button type="submit">{editingId ? "Modifier" : "Ajouter"}</button>
            </form>

            <table>
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Ligne</th>
                        <th>Départ</th>
                        <th>Arrivée</th>
                        <th>Actions</th>
                    </tr>
                </thead>
                <tbody>
                    {horaires.map(h => (
                        <tr key={h.id}>
                            <td>{h.id}</td>
                            <td>{h.nomLigne}</td>
                            <td>{h.heureDepart}</td>
                            <td>{h.heureArrivee}</td>
                            <td>
                                <button onClick={() => handleEdit(h)}>Modifier</button>
                                <button onClick={() => handleDelete(h.id)}>Supprimer</button>
                            </td>
                        </tr>
                    ))}
                </tbody>
            </table>
        </div>
    );
};

export default Horaires;
