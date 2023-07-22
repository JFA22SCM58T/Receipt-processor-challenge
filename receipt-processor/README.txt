prerequisite :-
    JAVA 17

Note:-
    For this project I have used H2 Database once you rerun your application data will be vanished.
    once your application is running you can check your database with this URL :- http://localhost:8080/h2

Configuration for Database :-
    JDBC URL: jdbc:h2:C:/data/sample
    User Name: admin
    Password : password

There are three API:-

    ->localhost:8080/receipts/process :-
        This API Will Save Data into Local Database Table name will be RECEIPT & ITEMS.

    ->localhost:8080/receipts/{id}/points
        This API will be used for to get the points for the id which is available in Endpoint.

    ->localhost:8080/getAllReceipts
        This API will be used for to List all the record which is available in Database.
