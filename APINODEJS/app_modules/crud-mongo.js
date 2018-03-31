var MongoClient = require('mongodb').MongoClient;
var ObjectId = require('mongodb').ObjectID;

var assert = require('assert');
var url = 'mongodb://localhost:27017/test';

exports.connexionMongo = function(callback) {
    MongoClient.connect(url, function(err, db) {
        assert.equal(null, err);
        callback(err, db);
    });
};

exports.putSiege = function(body, callback) {
    MongoClient.connect(url, function(err, db) {
        if(!err) {
            var myquery = { "_id": ObjectId(body._id)};
            var newvalues = {
                numSerie : body.numSerie, 
                transport : body.transport,
                compagnie:body.compagnie,
                etat:body.etat,
                utilisateurs:body.utilisateurs?body.utilisateurs:[]
            };
            db.collection("sieges")
                .updateOne(myquery, newvalues, function(err, result) {
                    if(!err){
                        reponse = {
                            succes : true,
                            result: result,
                            msg: "Modification réussie " + result
                        };
                    } else {
                        reponse = {
                            succes : false,
                            error : err,
                            msg: "Problème à la modification"
                        };
                    }
                    callback(reponse);
                });
        } else{
            var reponse = reponse = {
                succes: false,
                error : err,
                msg:"Problème lors de la modification, erreur de connexion."
            };
            callback(reponse);
        }
    });
};

exports.postSiege = function(formData, callback) {
	MongoClient.connect(url, function(err, db) {
	    if(!err) {
	 
			let toInsert = {
				numSerie : formData.numSerie, 
                transport : formData.transport,
                compagnie:formData.compagnie,
                etat:formData.etat,
                utilisateurs:[]
			};
			console.dir(JSON.stringify(toInsert));
		    db.collection("sieges")
		    .insertOne(toInsert, function(err, result) {
		    	let reponse;

		        if(!err){
		            reponse = {
		                succes : true,
		                result: result,
		                error : null,
		                msg: "Ajout réussi " + result
		            };
		        } else {
		            reponse = {
		                succes : false,
		                error : err,
		                msg: "Problème à l'insertion"
		            };
		        }
		        callback(reponse);
		    });
		} else{
			let reponse = reponse = {
                    	succes: false,
                        error : err,
                        msg:"Problème lors de l'insertion, erreur de connexion."
                    };
            callback(reponse);
		}
	});
};

exports.postUtilisateur = function(formData, callback) {
	MongoClient.connect(url, function(err, db) {
	    if(!err) {
	 
			let toInsert = {
			    nom:formData.nom,
                prenom:formData.prenom,
				email : formData.email, 
                mdp : formData.mdp,
                dateNaissance:formData.dateNaissance,
                taille:formData.taille,
                poids:formData.poids,
				sexe:formData.sexe,
				adresse:{ville:formData.ville,
						 codePostal:formData.codePostal,
						 numVoie:formData.numVoie,
						 typeVoie:formData.typeVoie},
				preference:{
					inclinaison:120,
					temperature:24,
					luminosite:50
				},
				historique:[]
			};
			console.dir(JSON.stringify(toInsert));
		    db.collection("utilisateurs")
		    .insertOne(toInsert, function(err, result) {
		    	let reponse;

		        if(!err){
		            reponse = {
		                succes : true,
		                result: result,
		                error : null,
		                msg: "Ajout réussi " + result
		            };
		        } else {
		            reponse = {
		                succes : false,
		                error : err,
		                msg: "Problème à l'insertion"
		            };
		        }
		        callback(reponse);
		    });
		} else{
			let reponse = reponse = {
                    	succes: false,
                        error : err,
                        msg:"Problème lors de l'insertion, erreur de connexion."
                    };
            callback(reponse);
		}
	});
};

exports.getUtilisateur = function(body, callback) {
    MongoClient.connect(url, function(err, db) {
        var selection ={email: body.email, mdp :body.mdp};
        if(!err){
            db.collection('utilisateurs')
                .find(selection)
                .toArray()
                .then(arr => callback(arr[0]));
        }
        else{
            callback(-1);
        }
    });
};

exports.putUtilisateur = function(body, callback) {
    MongoClient.connect(url, function(err, db) {
        if(!err) {
            var myquery = { "_id": ObjectId(body._id)};
            var newvalues = {
                nom:body.nom,
                prenom:body.prenom,
                email : body.email, 
                mdp : body.mdp,
                dateNaissance:body.dateNaissance,
                taille:body.taille,
                poids:body.poids,
				sexe:body.sexe,
				adresse:{ville:body.ville,
						 codePostal:body.codePostal,
						 numVoie:body.numVoie,
						 typeVoie:body.typeVoie},
				preference:{
					inclinaison:body.inclinaison,
					temperature:body.temperature,
					luminosite:body.luminosite,
					cMusique:body.cMusique,
					cVideo:body.cVideo
				},
				historique:body.historique?body.historique:[]
            };
            db.collection("utilisateurs")
                .updateOne(myquery, newvalues, function(err, result) {
                    if(!err){
                        reponse = {
                            succes : true,
                            result: result,
                            msg: "Modification réussie " + result
                        };
                    } else {
                        reponse = {
                            succes : false,
                            error : err,
                            msg: "Problème à la modification"
                        };
                    }
                    callback(reponse);
                });
        } else{
            var reponse = reponse = {
                succes: false,
                error : err,
                msg:"Problème lors de la modification, erreur de connexion."
            };
            callback(reponse);
        }
    });
};

exports.deleteUtilisateur = function(params, callback) {
    MongoClient.connect(url, function(err, db) {
        if(!err) {
            let myquery = { "_id": ObjectId(params.id)};
            let reponse;
            db.collection("utilisateurs")
                .deleteOne(myquery, function(err, result) {
                    if(!err){
                        reponse = {
                            succes : true,
                            result: result,
                            error : null,
                            msg: "Suppression réussie " + result
                        };
                    } else {
                        console.log("supression resussi");
                        reponse = {
                            succes : false,
                            error : err,
                            msg: "Problème à  la suppression"
                        };
                    }
                    callback(reponse);
                });
        } else{
            let reponse = reponse = {
                succes: false,
                error : err,
                msg:"Problème lors de la suppression, erreur de connexion."
            };
            callback(reponse);
        }
    });
};
