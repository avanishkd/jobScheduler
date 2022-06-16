# jobScheduler
sample requests
POST-->http://localhost:8080/schedule/taskdef
{
    "id": "2",
    "cronExpression": "*/1 * * * * ?",
    "actionType": "print data every minute",
    "data":"data to be printed evry minute"
}

GET-->http://localhost:8080/schedule/remove/2
