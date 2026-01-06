import React from 'react';
import { BrowserRouter as Router, Routes, Route, Link } from 'react-router-dom';
import Lignes from './pages/Lignes';
import Stations from './pages/Stations';
import Horaires from './pages/Horaires';
import './App.css';

function App() {
  return (
    <Router>
      <div className="app-container">
        <nav className="navbar">
          <div className="logo">Transport Public</div>
          <ul className="nav-links">
            <li><Link to="/">Lignes</Link></li>
            <li><Link to="/stations">Stations</Link></li>
            <li><Link to="/horaires">Horaires</Link></li>
          </ul>
        </nav>

        <main className="content">
          <Routes>
            <Route path="/" element={<Lignes />} />
            <Route path="/stations" element={<Stations />} />
            <Route path="/horaires" element={<Horaires />} />
          </Routes>
        </main>
      </div>
    </Router>
  );
}

export default App;
