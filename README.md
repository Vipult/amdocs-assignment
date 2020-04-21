Pre-requisites:
Docker desktop 2.0.1.4 should be installed to run this project.

Use case and Functional flow:
PUT or Update User Profile Scenario:
1.Executing Update User Profile operation as described in step No.5 below will trigger an event and
publish it to userProfileChangeTopic Kafka Topic.Authorization Service will act as a source for
this UPDATE event.
2.User Profile Service will act as a consumer for this event and will consume the UPDATE event
received from userProfileChangeTopic Kafka Topic and Update either the address and phonenumber or
both in User_Profile table in H2 DB.



Steps:
1.Git Clone the project from the repository using:

git clone https://github.com/Vipult/amdocs-assignment.git

this will create local directory amdocs-assigment\assignment

2.Go to the root folder of the project or cd to root folder of the project i.e.assignment. 

3.To build the project use this command:

mvn clean package docker:build

4.To run the project use this command:

docker-compose –f docker-compose.yml up
Note: Both Authorization Service and User Profile service and kafka server should start as a result of executing this command.
Authorization Service should start on port 8085
User Profile service should start on port 8080
kafka server  should start on port 9092
schema.sql and data.sql in User Profile Service and Authorization service will be loaded and USER_PROFILE and USER Tables will be created in H2 database. 

5.To Test the application using POSTMAN :
Scenarios to be executed:

1.For POST :

Authorization->Basic Auth-> Username: admin Password: password or Username: user Password: password
Headers: Accept: application/json and Content-Type: application/json
URL: http://localhost:8085/authorization/userprofiles
Action : POST
Request Body: 
{
    "address": "Bibwewadi,Pune",
    "phoneNumber": "9987767564"
}

Expected result:
POSTMAN status: 200 OK
A record should be created in Database with id: 1, address: Bibwewadi,Pune,phoneNumber: 9987767564
expected JSON response:
{
    "id": 1,
    "address": "Bibwewadi,Pune",
    "phoneNumber": "9987736354"
}

Error JSON response:
for phonenumber value not numeric or less than or greater than 10 digits
{
    "errorMessage": [
        "phoneNumber : PhoneNumber should be 10 digit",
        "phoneNumber : PhoneNumber should be numeric"
    ],
    "statusCode": 400
}

Error JSON Response:
for wrong credentials i.e.Authorization->Basic Auth-> Username: admin222 Password: password222 or Username: user222 Password: password222
POSTMAN response: 401 NOT AUTHORIZED



2.For PUT:
To update the same record:
Authorization->Basic Auth-> Username: admin Password: password or Username: user Password: password
Headers: Accept: application/json and Content-Type: application/json
Hit the URL: http://localhost:8085/authorization/userprofiles/1
Action : PUT
Request Body: 
{
    "id":"1"
    "address": "Viman Nagar,Pune",
    "phoneNumber": "9876789609"
}

Expected result:
POSTMAN status: 200 OK
A record should be updated in Database with id: 1, address: Viman Nagar,Pune,phoneNumber: 9876789609
NOTE: URL @Pathvariable value i.e. in this case should match request Body id variable i.e. in this case 1 value.
Expected JSON response:
{
    "id":"1"
    "address": "Viman Nagar,Pune",
    "phoneNumber": "9876789609"
}

Error JSON response:
for phonenumber value not numeric or less than or greater than 10 digits
{
    "errorMessage": [
        "phoneNumber : PhoneNumber should be 10 digit",
        "phoneNumber : PhoneNumber should be numeric"
    ],
    "statusCode": 400
}

Error JSON Response:
for wrong credentials i.e.Authorization->Basic Auth-> Username: admin222 Password: password222 or Username: user222 Password: password222
POSTMAN response: 401 NOT AUTHORIZED


3.For DELETE:
Authorization->Basic Auth-> Username: admin Password: password or Username: user Password: password
Headers: Accept: application/json and Content-Type: application/json
To delete the same record:
Hit the URL: http://localhost:8085/authorization/userprofiles/1
Action : DELETE

Expected result:
POSTMAN status: 204 NO CONTENT
A record should be deleted in Database.

Error JSON response:
If you delete same user with same id i.e. id=1 again then
{
    "timestamp": "2020-04-21T06:01:09.608+0000",
    "message": "User Profile with id '1' does no exist",
    "details": "uri=/authorization/userprofiles/1"
}
This is applicable for any user not existing in the DB.
 

NOTE: A REST endpoint http://localhost:8080/userprofilemgmt/userprofiles can be used to check the output after execution of each scenario above.
i.e.
Hit the URL: http://localhost:8080/authorization/userprofiles/1
Action : GET

For All the REST EndPoints for Amdocs assignment,  "Amdocs assignment collection.postman_collection.json" has been created in assignment root folder.Please import the same in POSTMAN.










