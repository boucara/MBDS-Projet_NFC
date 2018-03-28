const express  = require('express');

var path = require('path');
const app      = express();
const port     = process.env.PORT || 3000;
const server   = require('http').Server(app);
const mongoDBModule = require('./app_modules/crud-mongo');
// Pour les formulaires standards
const bodyParser = require('body-parser');
// pour les formulaires multiparts
var multer = require('multer');
var multerData = multer();

// Cette ligne indique le répertoire qui contient
// les fichiers statiques: html, css, js, images etc.
app.use(express.static(path.join(__dirname, '../', 'angular_client/client-video/dist/')));
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

// Rend la liste des vidéos
app.get('/api/videos', function(req, res) {
    // Si prÃ©sent on prend la valeur du param, sinon 1
    var page = parseInt(req.query.page || 0);
    // idem si present on prend la valeur, sinon 10
    var pagesize = parseInt(req.query.pagesize || 10);
     mongoDBModule.findVideos(page, pagesize,  function(data) {
         //console.log(data.JSON);
        var objdData = {
            msg:"la liste des videos avec succes",
            data: data
        }
        res.send(JSON.stringify(objdData));
    });
});

// Met à jour la vidéo
// rend un message de réussite si la mise à jour à réussie
// un message d'erreur sinon
app.put('/api/video', function (req, res) {
    mongoDBModule.updateVideo(req.body, function (response) {
        res.send(JSON.stringify(response));
    });
});

// Check si l'url est déjà utilisé
// rend true si l'url est pas déjà stoké
app.get('/api/checkurl', function (req, res) {
    mongoDBModule.checkURL(req.query, function (response) {
        res.send(JSON.stringify(response));
    });
});

// ajoute une nouvelle vidéo en base de données
/*app.post('/api/videos', function(req, res) {
    mongoDBModule.createVideo(req.body, function(data) {
        res.send(JSON.stringify(data));
    });
});*/app.post('/api/videos', multerData.fields([]), function(req, res) {
    console.log("tedi");
 	mongoDBModule.createVideo(req.body, function(data) {
        
        res.send(JSON.stringify(data)); 
    });
});

//Retourne la video dont l'id en param
app.get('/api/:id/video',function(req, res) {
    mongoDBModule.findVideo(req.params, function(data) {
        res.send(JSON.stringify(data));
    });
});

//Retourne la video dont l'id en param
app.delete('/api/video/:id',function(req, res) {
    mongoDBModule.deleteVideo(req.params, function(data) {
        res.send(JSON.stringify(data));
    });
});

// Retourne l'index de l'application cliente
app.get('*', function(req, res) {
    res.sendFile(path.join(__dirname, '../', 'angular_client/client-video/dist/', 'index.html'));
});
// ajoute vidéo en base de données


// Lance le serveur avec express
server.listen(port);

console.log("Serveur lancé sur le port : " + port);
