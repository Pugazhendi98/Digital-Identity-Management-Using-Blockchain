var MongoClient = require('mongodb').MongoClient;
var url = "mongodb://127.0.0.1:27017/";
const ganache =require('ganache-cli');
const Web3 =require('web3');
const web3 =new Web3(ganache.provider());





const message="MUTHU S,3-6-1999,PANDI PK,MALE"
mdata="664349111325 9789589619"
const data =web3.eth.accounts.hashMessage(message);
const mh=web3.eth.accounts.hashMessage(mdata);



/*MongoClient.connect(url, function(err, db) {
  if (err) throw err;
  var dbo = db.db("IDdb");
  var myobj = { _id:664349111325,mobileh:mh,hdata:data};
  dbo.collection("customers").insertOne(myobj, function(err, res) {
    if (err) throw err;
    console.log("1 document inserted");
    db.close();
  });
});
/*MongoClient.connect(url, function(err, db) {
  if (err) throw err;
  var dbo = db.db("IDdb");
  var myquery = {_id:462457726130};
  dbo.collection("customers").find(myquery).toArray(function(err, result) {
    if (err) throw err;
    console.log(result);
    db.close();
  });
});*/

/*MongoClient.connect(url, function(err, db) {
  if (err) throw err;
  var dbo = db.db("IDdb");
  var myquery = { _id:363457726222};
  dbo.collection("customers").deleteMany(myquery, function(err, obj) {
    if (err) throw err;
    console.log(obj.result.n + " document(s) deleted");
    db.close();
  });
});*/
/*exports.verif=(mh)=>{
  MongoClient.connect(url, function(err, db) {
  if (err) throw err;
  var dbo = db.db("IDdb");
  var myquery = {mobileh:mh};
  //console.log("mh="+mh);
  dbo.collection("customers").find(myquery).toArray(function(err, result) {
    if (err) throw err;
    //db.close();
    return 1;
  
  });
});
}
exports.verify1= (dh)=>{
  MongoClient.connect(url, function(err, db) {
    if (err) throw err;
    var dbo = db.db("IDdb");
    var myquery = {hdata:dh};
    dbo.collection("customers").find(myquery).toArray(function(err, result) {
      if (err) return 0;
      else return 1;
      console.log(result);
      db.cloe();
    });
  });
  }*/
 