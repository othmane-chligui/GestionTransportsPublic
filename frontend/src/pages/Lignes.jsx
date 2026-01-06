import React, { useState, useEffect } from 'react';
import { ligneService } from '../services/api';

const Lignes = () => {
    const [lignes, setLignes] = useState([]);
    const [formData, setFormData] = useState({ nom: '', type: '' });
    const [editingId, setEditingId] = useState(null);

    useEffect(() => {
        fetchLignes();
    }, []);

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
                await ligneService.update(editingId, formData);
            } else {
                await ligneService.create(formData);
            }
            setFormData({ nom: '', type: '' });
            setEditingId(null);
            fetchLignes();
        } catch (error) {
            console.error("Error saving ligne", error);
        }
    };

    const handleEdit = (ligne) => {
        setFormData({ nom: ligne.nom, type: ligne.type });
        setEditingId(ligne.id);
    };

    const handleDelete = async (id) => {
        if (window.confirm("Supprimer cette ligne ?")) {
            try {
                await ligneService.delete(id);
                fetchLignes();
            } catch (error) {
                console.error("Error deleting ligne", error);
            }
        }
    };

    return (
        <div className="container">
            <h1>Gestion des Lignes</h1>
            
            <form onSubmit={handleSubmit} className="form-card">
                <input 
                    type="text" 
                    placeholder="Nom de la ligne" 
                    value={formData.nom} 
                    onChange={(e) => setFormData({...formData, nom: e.target.value})}
                    required
                />
                <input 
                    type="text" 
                    placeholder="Type (Bus, Tram, etc.)" 
                    value={formData.type} 
                    onChange={(e) => setFormData({...formData, type: e.target.value})}
                    required
                />
                <button type="submit">{editingId ? "Modifier" : "Ajouter"}</button>
                {editingId && <button onClick={() => {setEditingId(null); setFormData({nom:'', type:''})}}>Annuler</button>}
            </form>

            <table>
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Nom</th>
                        <th>Type</th>
                        <th>Actions</th>
                    </tr>
                </thead>
                <tbody>
                    {lignes.map(l => (
                        <tr key={l.id}>
                            <td>{l.id}</td>
                            <td>{l.nom}</td>
                            <td>{l.type}</td>
                            <td>
                                <button onClick={() => handleEdit(l)}>Modifier</button>
                                <button onClick={() => handleDelete(l.id)}>Supprimer</button>
                            </td>
                        </tr>
                    ))}
                </tbody>
            </table>
        </div>
    );
};

export default Lignes;
