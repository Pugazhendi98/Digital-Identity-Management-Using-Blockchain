pragma solidity ^0.4.16;
contract Inbox{
     string public message;
   function Inbox(string Initialmessage)public{
       message =Initialmessage;
   } 
   function setmessage(string newmessage)public{
       message =newmessage;
   }
}
