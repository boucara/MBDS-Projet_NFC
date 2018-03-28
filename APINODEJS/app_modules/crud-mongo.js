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

exports.checkURL = function(params, callback) {
    MongoClient.connect(url, function(err, db) {
        if(!err) {
            // La requete mongoDB
            db.collection("videos")
                .find(params).toArray(function(err, data) {
                    var reponse;

                    if(!err){
                        var result = data.length>0?true:false;
                        reponse = {
                            succes: true,
                            result : result,
                            error : null,
                            msg:"Details de la vidéo envoyés"
                        };
                    } else{
                        reponse = {
                            succes: false,
                            error : err,
                            msg: "erreur lors du find"

                        };
                    }
                    callback(reponse);
                    db.close();
                });
        } else {
            var reponse = reponse = {
                succes: false,
                error : err,
                msg: "erreur de connexion à la base"
            };
            callback(reponse);
        }
    });
};

exports.updateVideo = function(body, callback) {
    MongoClient.connect(url, function(err, db) {
        if(!err) {
            var myquery = { "_id": ObjectId(body.id)};
            var newvalues = {
                description : body.description,
                titre : body.titre,
                urlimg : body.urlimg,
                url : body.url,
                vote:body.vote
            };
            db.collection("videos")
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

exports.findVideos = function(page, pagesize, callback) {
    MongoClient.connect(url, function(err, db) {
        console.log("pagesize = " + pagesize);
        console.log("page = " + page);
        var selection ={};
        if(!err){
            db.collection('videos')
                .find(selection)
                .skip(page*pagesize)
                .limit(pagesize)
                .toArray()
                .then(arr => callback(arr));
        }
        else{
            callback(-1);
        }
    });
};
exports.createVideo = function(formData, callback) {
	MongoClient.connect(url, function(err, db) {
	    if(!err) {
	 
			let toInsert = {
				url : formData.url, 
                description : formData.description,
                titre:formData.titre,
                urlimg:formData.urlimg,
                vote:formData.vote
			};
			console.dir(JSON.stringify(toInsert));
		    db.collection("videos")
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

exports.findVideo = function(params, callback) {
    MongoClient.connect(url, function(err, db) {
        var selection ={_id: ObjectId(params.id)};
        if(!err){
            db.collection('videos')
                .find(selection)
                .toArray()
                .then(arr => callback(arr[0]));
        }
        else{
            callback(-1);
        }
    });
};

exports.deleteVideo = function(params, callback) {
    MongoClient.connect(url, function(err, db) {
        if(!err) {
            let myquery = { "_id": ObjectId(params.id)};
            let reponse;
            db.collection("videos")
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
