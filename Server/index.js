let express = require('express');
var fs = require('fs');
const app = express();

const bodyParser = require('body-parser');
const assert =require('assert');
const ganache =require('ganache-cli');
const Web3 =require('web3');
const web3 =new Web3(ganache.provider());
//const {verif,verify1}=require("./mongo/mdb");
var MongoClient = require('mongodb').MongoClient;
var url = "mongodb://127.0.0.1:27017/";
var data = fs.readFileSync('./config.json'),acc;
myObj = JSON.parse(data);

const Nexmo = require('nexmo');

const nexmo = new Nexmo({
  apiKey: '135607bc',
  apiSecret: 'AWF0GLk1QXLkLgjj',
});


/*web3.eth.getAccounts().then(fetchedAccounts=>{
    const root=fetchedAccounts[0];
    console.log(root);

    });*/



//const router = require('../server/routes/routes')
const port = process.env.PORT || 1223;
const server = app.listen(port, () => {

    console.log('Connected to port ' + port)

})
 
app.use(bodyParser.json());
app.use(bodyParser.urlencoded({ extended: true }));

app.get('/hello',async (req, res) => {
    // const acc =web3.eth.accounts.create();
     res.send("hello  world");
     console.log("sent");
   })

     
     
     
app.post('/verify', (req, res) => {
   
    const data=req.body.aadhar+" "+req.body.mobile; // your dataip

    console.log(data);
    // do something with that data (write to a DB, for instance)
    a=0;
    console.log(a);
    const datah =web3.eth.accounts.hashMessage(data);
    //console.log(datah);
    
    MongoClient.connect(url, function(err, db) {
        if (err) throw err;
        var dbo = db.db("IDdb");
        var myquery = {mobileh:datah};
        //console.log("mh="+mh);
        dbo.collection("customers").find(myquery).toArray(function(err, result) {
          if (err) throw err;
          //db.close();
          else{
    
            var digits = '0123456789'; 
            let OTP = ''; 
            for (let i = 0; i < 4; i++ ) { 
                  OTP += digits[Math.floor(Math.random() * 10)]; 
               }

             const from = 'Nexmo';
             const to   =  "91"+req.body.mobile ;
             const text = OTP;
             nexmo.message.sendSms(from, to, text);
              res.status(200).json({
                  otp:OTP
                  });
              console.log(OTP);
                a=1
           }
         });
      });
      if(a=0){
        res.status(404);
        console.log(a);
    }
    
    
});




app.get('/createacc',async (req, res) => {
    const acc =web3.eth.accounts.create();
     res.json({
         address:acc.address,
         privatekey:acc.privateKey
     });
     console.log(acc);
   });


app.post('/getsign',async (req, res) => {
    const data1 =req.body.data;
    const datah =web3.eth.accounts.hashMessage(data1);
    console.log(data1+datah);
    MongoClient.connect(url, function(err, db) {
        if (err) throw err;
        var dbo = db.db("IDdb");
        var myquery = {hdata:datah};
        dbo.collection("customers").find(myquery).toArray(function(err, result) {
          if (err){
            res.status(404);
              throw err;
             
          }
          else {
            
            const sign= web3.eth.accounts.sign(data1,myObj.privateKey);
            console.log(sign);
            res.json({sign:sign
            });
          }
     });
     
   })
   
   });
   
   
   
   
   
   app.post('/getverify',async (req, res) => {
    const sig =JSON.parse(req.body.Sign);
    const addr=req.body.toadr;
    //const pkey=req.body.pkey;
    console.log(typeof sig);
     const signer = web3.eth.accounts.recover(sig);
     
     if(myObj.address==signer)
     {
         res.json({
       Result:"Identity Verified",
       transhash:"Signed By:"+signer
     })
     }
     else{
       res.status(404);
     }
     
    
    });