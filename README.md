After you've cloned the repo locally:

1. Go to the root folder of the project.
2. In the terminal run the script by entering ./run_carworkshop.sh
3. After the script has completed, you have two options:
  1. Go to a web-browser and try the GET options, since I have added some dummy data:
  2. Go to a backend-test-service like postman, bruno etc. Where you can try all of the options.

If you just want to try the GET in the browser:
1. To get all customers:
   http://localhost:8080/customers
2. To get all vehicles:
   http://localhost:8080/vehicles
3. To get all vehicles of a certain brand, for example Volvo:
   http://localhost:8080/brand?brand=Volvo
4. To get the id of a customer by name, for example Lars:
   http://localhost:8080/get-customer-id?customerName=Lars
5. To get all vehicles for a specific customer by entering id, for example 1:
   http://localhost:8080/get-vehicles-for-customer?customerId=1
   
If you want to try the POST- and/or GET-options in a service like postman etc.
You can use the same url's as above for GET-operations and change the parameters if you like for the ones that take parameters.
The different relevant parameters are: brand, customerName and customerId

POST-options:
1. To create a customer:
   http://localhost:8080/customer

   Here is an example to add as body:
   {
     "name": "Bosse",
     "phoneNumber": "0783131313"
   }

2. To create a vehicle:
  http://localhost:8080/vehicle

  Here is an example to add as body:
  {
    "registrationNumber": "W08167",
    "brand": "Volvo",
    "model": "V50",
    "productionYear": "2001"
  }

If you added these you should be able to see them with the previous GET-operations, but they aren't linked yet.

3. To link a customer to a vehicle, for example if you added a new customer and vehicle they should have customerId 4 and vehicleId5 since we have dummy-data from before:
   http://localhost:8080/addvehicletocustomer?customerId=4&vehicleId=5

   or: http://localhost:8080/addvehicletocustomer
   and just add params manually in postman: customerId & vehicleId

If you look at the customers or the specific customer, the car should be added to their vehicle-list, and the vehicle should have the customerId attached to it.










   
