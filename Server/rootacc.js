var fs = require('fs');
const assert =require('assert');
const ganache =require('ganache-cli');
const Web3 =require('web3');
const web3 =new Web3(ganache.provider());
const Nexmo = require('nexmo');

const nexmo = new Nexmo({
  apiKey: '135607bc',
  apiSecret: 'AWF0GLk1QXLkLgjj',
});
const from = 'Nexmo';
const to = '918678966378';
const text = 'Hello from Nexmo';

const resp=nexmo.message.sendSms(from, to, text);

//let express = require('express');
const express =require('express');
//const app = express();
const app=express();


/*const bodyParser = require('body-parser');
const port = process.env.PORT || 1222;

const server = app.listen(port, () => {

    console.log('Connected to port ' + port)

})

console.log(resp);
app.get('/sms',async (req, res) => {
  
const from = 'Nexmo';
const to = '918678966378';
const text = 'Hello from Nexmo';

const resp=nexmo.message.sendSms(from, to, text);
res.send(resp);
console.log(resp);
 });*/
