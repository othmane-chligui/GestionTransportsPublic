# Docker Configuration pour Gestion de Transports Publics

## 📋 Prérequis

- Docker Desktop installé sur votre machine
- Docker Compose (inclus avec Docker Desktop)
- Au moins 4 GB de RAM disponible

## 🏗️ Architecture

Le projet est composé de :
- **3 bases de données MySQL** (une par microservice)
- **Eureka Server** (8761) - Service Discovery
- **API Gateway** (8080) - Point d'entrée unique
- **ServiceLignes** (8081) - Gestion des lignes
- **ServiceStations** (8082) - Gestion des stations
- **ServiceHoraires** (8083) - Gestion des horaires
- **Frontend React** (80) - Interface utilisateur

## 🚀 Démarrage

### Lancer tous les services

```bash
docker-compose up --build
```

Cette commande va :
1. Construire les images Docker pour tous les services
2. Démarrer les 3 bases de données MySQL
3. Démarrer Eureka Server
4. Démarrer les 3 microservices métier
5. Démarrer l'API Gateway
6. Démarrer le frontend React

### Lancer en arrière-plan

```bash
docker-compose up -d --build
```

### Voir les logs

```bash
# Tous les services
docker-compose logs -f

# Un service spécifique
docker-compose logs -f service-lignes
docker-compose logs -f eureka-server
docker-compose logs -f frontend
```

## 🌐 URLs d'accès

- **Frontend** : http://localhost
- **API Gateway** : http://localhost:8080
- **Eureka Dashboard** : http://localhost:8761
- **ServiceLignes** : http://localhost:8081
- **ServiceStations** : http://localhost:8082
- **ServiceHoraires** : http://localhost:8083
- **MySQL Lignes** : localhost:3307
- **MySQL Stations** : localhost:3308
- **MySQL Horaires** : localhost:3309

## 🛠️ Commandes utiles

### Arrêter tous les services

```bash
docker-compose down
```

### Arrêter et supprimer les volumes (⚠️ supprime les données)

```bash
docker-compose down -v
```

### Redémarrer un service spécifique

```bash
docker-compose restart service-lignes
```

### Reconstruire un service spécifique

```bash
docker-compose up -d --build service-lignes
```

### Voir les conteneurs en cours d'exécution

```bash
docker-compose ps
```

### Accéder au shell d'un conteneur

```bash
docker exec -it service-lignes sh
docker exec -it mysql-lignes bash
```

## 🔍 Vérification de l'état

1. Attendez environ 1-2 minutes que tous les services démarrent
2. Vérifiez Eureka Dashboard : http://localhost:8761
   - Vous devriez voir : service-lignes, service-stations, service-horaires, api-gateway
3. Testez le frontend : http://localhost

## 🐛 Dépannage

### Les services ne démarrent pas

```bash
# Vérifier les logs
docker-compose logs -f

# Redémarrer complètement
docker-compose down
docker-compose up --build
```

### Base de données non accessible

```bash
# Vérifier l'état des conteneurs MySQL
docker-compose ps mysql-lignes mysql-stations mysql-horaires

# Redémarrer les bases de données
docker-compose restart mysql-lignes mysql-stations mysql-horaires
```

### Problèmes de mémoire

Assurez-vous d'avoir au moins 4 GB de RAM alloués à Docker Desktop dans les paramètres.

## 🔧 Configuration

### Variables d'environnement

Toutes les variables sont configurées dans le `docker-compose.yml`. Pour modifier :

1. Ouvrez `docker-compose.yml`
2. Modifiez les variables sous `environment` pour chaque service
3. Redémarrez : `docker-compose up -d`

### Ports personnalisés

Pour changer les ports exposés, modifiez la section `ports` dans `docker-compose.yml`.

## 📦 Volumes

Les données MySQL sont persistées dans des volumes Docker nommés :
- `lignes-data`
- `stations-data`
- `horaires-data`

Pour voir les volumes :
```bash
docker volume ls
```

## 🧹 Nettoyage complet

Pour supprimer tout (conteneurs, images, volumes, réseaux) :

```bash
docker-compose down -v --rmi all
```

## 📝 Notes importantes

- Les bases de données utilisent le mot de passe root : `root`
- Le mode DDL est en `update` pour préserver les données au redémarrage
- Les services ont des health checks configurés pour démarrer dans le bon ordre
- Le frontend utilise nginx en production avec proxy vers l'API Gateway
