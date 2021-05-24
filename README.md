# fetchReward
to Submit a coding test


This coding is for Fetch Reward coding test.

The ability for Fetch Rewards participating companies to reward points to consumers.
Customer can use there point in oldest earn points first.

Prerequisites

REST API

REWARD POINTS

POST /addTransaction

//For spend points (olest points will be used first)
curl -d  '{ "payer": "DANNON", "points": 1000, "timestamp": "2020-11-02T14:00:00Z" }' -H 'Content-Type: application/json' localhost:8080/addTransaction


POST /spendPoints

curl -d  '{ "points": 5000 }' -H 'Content-Type: application/json' localhost:8080/spendPoints


GET /pointsBalance

curl -d  localhost:8080/pointsBalance



