/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  johnson3yo
 * Created: May 8, 2018
 */

-- creating dummy account 
insert into account(id,player_name,sex,date_created,version) values (1000,'foobar1','M',now(),0);
insert into account(id,player_name,sex,date_created,version) values (2000,'foobar2','M',now(),0);
insert into account(id,player_name,sex,date_created,version) values (3000,'foobar3','F',now(),0);

-- performing wallet update for account with id 1000
insert into wallet(id,account_id,amount,purpose,transaction_date,transaction_reference,version) values (500,1000,4000,'opening balance',now(),1001,0);
insert into wallet(id,account_id,amount,purpose,transaction_date,transaction_reference,version) values (501,1000,-300,'debit',now(),1002,0);
insert into wallet(id,account_id,amount,purpose,transaction_date,transaction_reference,version) values (502,1000,6500,'credit',now(),1003,0);


