create database truyum
use truyum

create table menu_item(
id int primary key,
Name varchar(100),
Price varchar(50),
active varchar(3),
date_of_launch date,
category varchar(50),
free_delivery varchar(3)
)

/*View Menu Item List Admin (TYUC001)*/
 
 /*insert scripts to add data into menu_item*/

insert into menu_item values
	 (1,'Sandwich',99,'Yes','2017-03-15','Main Course','Yes'),
	 (2,'Burger',129,'Yes','2017-12-23','Main Course','No'),
	 (3,'Pizza',149,'Yes','2017-08-21','Main Course','NO'),
	 (4,'French Fries',57,'No','2017-07-02','Starters','Yes'),
	 (5,'Chocolate Brownie',32,'Yes','2022-11-02','Dessert','Yes');
     
     /*Frame SQL query to get all menu items*/
     
select * from menu_item;

/*View Menu Item List Customer (TYUC002)*/

select * from menu_item
where date_of_launch >= '2017-03-15' and active = 'yes';

/*Edit Menu Item (TYUC003)*/

/*SQL query to get a menu items based on Menu Item Id*/
select *from menu_item
where id = 3;

/*update SQL menu_items table to update all the columns values based on Menu Item Id*/

update menu_item set name='Briyani',price=130,active='yes', date_of_launch = '2018-07-18',category = 'Main Course',
	    free_delivery = 'No' where id=3;
        
select *from menu_item	
        
 /*Add to Cart (TYUC004)*/
 
 
create table user(
	user_id int not null primary key,
	name varchar (20),
	email varchar(20),
	phone_no varchar(10),
	address varchar (20));
	    
insert into user values
                   (1,'praveen','prav@gmail.com','9874563210','ooty'),
                   (3,'prasanth','prasa@gmail.com','9996663330','coimbatore'),
                   (2,'pranesh','prana@gmail.com','9123456780','erode');
 select * from user  
 
/*View Cart (TYUC005)*/
 
 
 create table cart(
 user_id int ,
 item_id int,
 item_Name varchar(30),
 Free_delivery varchar(30),
 price int,
 category varchar(30),
  constraint fk1 foreign key (user_id) references user (user_id),
  constraint fk2 foreign key (item_id) references menu_item(id) );
  
  select * from cart
  
 insert into cart values(1,2,'Burger','No',129,'Main Course');
 insert into cart values(1,3,'Briyani','yes',130,'Main Course');
 
 insert into cart (item_id,user_id) values(3,3); 
 
 /*SQL query to get all menu items in a particular user’s cart*/
  
select * from menu_item m 
right join cart c on m.id=c.item_id 
where c.user_id=1; 

 /*SQL query to get the total price of all menu items in a particular user’s cart*/ 
 
select sum(Price) as total_price from menu_item
where id in (select item_id from cart where user_id = 1);


/*Remove Item from Cart (TYUC006)*/

/* SQL query to remove a menu items from Cart based on User Id and Menu Item Id*/
delete  from cart
where user_id=1 and item_id=2; 
     