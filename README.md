DBMSProject
===========

Our DBMS Project

Project Narrative for CSC540: Database-Management Concepts and Systems
You are to design and build a management system for a chain of bookstores called Books-AThousand.
The database system will be used by the management of the chain, and should 
maintain information on at least the following:
Staff Information: staff ID, name, age, gender, salary, job title (store salesperson, billing
staff, merchandise stocker, etc), department (Sales Department, Accounting Department, etc),
contact information (phone number and address);
Usage Records: customer information, merchandise purchased, purchase date, etc.
Vendors: vendor ID, name, merchandise supplied, contact information.
Customer: customer ID, SSN (if available), name, date of birth, gender, age, contact
information
(phone number and address)
Vendor Purchases: merchandise purchased, cost, purchase date, etc.
Merchandise: price, quantity in stock, supplier, etc.
Orders: customer who placed the order (customer ID), the merchandise that is being ordered,
date, etc.
Roles and Behaviors
We have elicited for you the following information about the book store chain. Note that in
working on this project, you might discover that not every bit of the information has to be
explicitly captured in the database. Part of the modeling effort is to decide what to keep and
what to discard. In doing your project, you will need to make additional assumptions as well as
identify the potential inconsistencies and resolve them. Any reasonable assumptions are fine,
but they must be documented in your reports. You can consult with the TAs or instructor if you
have questions about the assumptions.
Books-A-Thousand only sells books.
Salespeople at the sales counter can open a new account for a non-registered customer, can
ring up a selection of books to sell to a customer, can place or cancel an order for merchandise
by request of the customer, and can bill each customer based on placed orders.
If the customer chooses to place an order, the customer needs to have an account with the
store. Customer is billed by the end of each billing cycle.
Books-A-Thousand has contract with several book vendors and has its own warehouse to
stock the books. If a customer’s desired book(s) is out of stock, Books-A-Thousand will request
replenishment service from the vendor. Once the required books are replenished, Books-AThousand
can ship the book(s) to customer.
Tasks and Operations
The following are the four major kinds of tasks that need to be performed using your database.
Each task potentially consists of a number of operations; an "operation" is something that
corresponds to a separate action. For example, information processing is considered
to be one task, which involves separate operations such as entering and updating basic
information about customers. Each student should write about the same number of application
programs, but it is up to each team to decide how to allocate the overall effort.
Information processing: Enter/update/delete basic information about staff, customers,
contracts, warehouse information and vendors. Check available books and sell what’s in stock
to each customer according to their request.
Maintaining purchase usage records for each vendor: Enter/update a new purchase
record for each vendor for each billing cycle.
Maintaining billing accounts. Generate/maintain billing accounts for every billing cycle of
every vendor and customer.
Reports. Generate reports: report the purchase history for a given customer and for a
certain time period (day/month/year). Return information on all the customers a given
salesperson assisted during a certain time period (day/month/year). Return information on all
the vendors a particular store has contract with. Return information on Books-A-Thousand’ staff
grouped by their role.