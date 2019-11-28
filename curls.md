#Add new Meal
curl -d "{\"dateTime\":\"2018-11-22T22:00:00\",\"description\":\"curlTest\",\"calories\":285}" -H "Content-Type: application/json" http://localhost:8080/topjava/rest/meals

#GetMealById
curl -v http://localhost:8080/topjava/rest/meals/100004

#GetAll 
curl -v http://localhost:8080/topjava/rest/meals/

#DeleteById
curl -X DELETE http://localhost:8080/topjava/rest/meals/100004

#Update
curl -d "{\"dateTime\":\"2018-11-22T22:00:00\",\"description\":\"curlUpdateTest\",\"calories\":285}" -H "Content-Type: application/json" -X PUT http://localhost:8080/topjava/rest/meals/100006