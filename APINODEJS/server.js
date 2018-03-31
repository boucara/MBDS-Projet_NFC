const express  = require('express');

var path = require('path');
const app      = express();
const port     = process.env.PORT || 3000;
const server   = require('http').Server(app);
const mongoDBModule = require('./app_modules/crud-mongo');
// Pour les formulaires standards
const bodyParser = require('body-parser');

// Paramètres standards du module bodyParser
// qui sert à  récupérer des paramètres reçus
// par ex, par l'envoi d'un formulaire "standard"
app.use(bodyParser.urlencoded({ extended: false }));
app.use(bodyParser.json());

app.use(function (req, res, next) {
     res.header("Access-Control-Allow-Origin", "*");
     res.header("Access-Control-Allow-Methods","POST,DELETE,PUT,GET")
    res.header("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept");
     next();
 });

//------------------
// ROUTES
//------------------

// Ici des routes en :
// http GET (pour récupérer des données)
// http POST : pour insérer des données
// http PUT pour modifier des donnÃ©es
// http DELETE pour supprimer des données

//----------------------------------------------
// Ces routes forment l'API de l'application !!
//----------------------------------------------

// Test de la connexion à la base
app.get('/api/connection', function(req, res) {
    mongoDBModule.connexionMongo(function(err, db) {
        var reponse;
        if(err) {
            console.log("erreur connexion");
            reponse = {
                msg: "erreur de connexion err=" + err
            }
        } else {
            reponse = {
                msg: "connexion établie"
            }
        }
        res.send(JSON.stringify(reponse));
    });
});

// Connexion utilisateur
app.post('/api/connexion', function(req, res) {
	
     mongoDBModule.getUtilisateur(req.body,  function(data) {
         //console.log(data.JSON);
        var objdData = {
            msg:"succes",
            data: data
        }
        res.send(JSON.stringify(objdData));
    });
});

// Inscription utilisateur
app.post('/api/utilisateur', function(req, res) {
	
     mongoDBModule.postUtilisateur(req.body,  function(data) {
         //console.log(data.JSON);
        var objdData = {
            msg:"succes",
            data: data
        };
        res.send(JSON.stringify(objdData));
    });
});

// Update Utilisateur
app.put('/api/utilisateur', function (req, res) {
    mongoDBModule.putUtilisateur(req.body, function (response) {
        res.send(JSON.stringify(response));
    });
});

// delete Utilisateur
app.delete('/api/utilisateur/:id', function (req, res) {
    mongoDBModule.deleteUtilisateur(req.params, function (response) {
        res.send(JSON.stringify(response));
    });
});

// activation siège
app.post('/api/siege', function(req, res) {
	
     mongoDBModule.postSiege(req.body,  function(data) {
         //console.log(data.JSON);
        var objdData = {
            msg:"succes",
            data: data
        }
        res.send(JSON.stringify(objdData));
    });
});

// Update siège
app.put('/api/siege', function (req, res) {
    mongoDBModule.putSiege(req.body, function (response) {
        res.send(JSON.stringify(response));
    });
});

// Lance le serveur avec express
server.listen(port);

console.log("Serveur lancé sur le port : " + port);
