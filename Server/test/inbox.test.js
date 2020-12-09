const assert =require('assert');
const ganache =require('ganache-cli');
const Web3 =require('web3');
const web3 =new Web3(ganache.provider());


//let express = require('express');
const express =require('express');
//const app = express();
const app=express();


const bodyParser = require('body-parser');
const port = process.env.PORT || 1223;

const server = app.listen(port, () => {

    console.log('Connected to port ' + port)

})

//var urlencodedParser = bodyParser.urlencoded({ extended: false });

//const apiRouter = express.Router();
//app.use(express.bodyParser());
app.use(bodyParser.json());
app.use(bodyParser.urlencoded({ extended: true }));
// const server = app.listen(port, () => {

//     console.log('Connected to port ' + port)

// })

const Nexmo = require('nexmo');

const nexmo = new Nexmo({
  apiKey: '135607bc',
  apiSecret: 'AWF0GLk1QXLkLgjj',
});


const from = 'Nexmo';
const to = '918870677602';
const text = 'Hello from Nexmo';

nexmo.message.sendSms(from, to, text);


//web3.eth.getAccounts().then(fetchedAccounts=>{
//      console.log(fetchedAccounts[9]);
//});
//const ac=web3.eth.accounts.privateKeyToAccount("0x41ff651b4cc0c988a271b8c28960c5092502063eb165290f04d6a1dfb108c6ee");
//console.log(ac.address);
//const sign =web3.eth.accounts.sign('Some data', '0x41ff651b4cc0c988a271b8c28960c5092502063eb165290f04d6a1dfb108c6ee');
//console.log(sign);
const sin =web3.eth.accounts.sign("message", '0x41ff651b4cc0c988a271b8c28960c5092502063eb165290f04d6a1dfb108c6ee');
console.log(sin);
const as=web3.eth.accounts.recover('Some data', '0x14947b79db807f53e0ed15da3c8694bf94ca6d3334607f110aa52e7422695e013c38afd5f24f8212bdd9ad2ab4523fbd939a68463f121ccab45c4af1fd7073951b');
var block= web3.eth.getBlock(4,true);
console.log(block);
app.get('/hello',async (req, res) => {
 // const acc =web3.eth.accounts.create();
  res.send("hello  world");
  console.log("sent");
})
app.post('/verify',(req, res) => {
  var post=req.body.name;

	console.log("Received variable : "+post);
  	res.send("Sending some message");
})


