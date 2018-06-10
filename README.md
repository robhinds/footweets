[![Build Status](https://travis-ci.org/robhinds/footweets.png)](https://travis-ci.org/robhinds/footweets)

# Russia 2018 World Cup Tweets
API collecting stats about the FIFA World Cup Russia 2018

## Sentiment Analysis
The running service connects to the Twitter streaming API and filters for relevant tweets for the upcoming World Cup. After that, it attempts to check the sentiment of the tweet using AWS Comprehend (requires an AWS account and access key). It translates the sentiment score returned from AWS to a score ranging from -100 to 100. It then updates the in memory data store and in memory stats for the relevant countries mentioned.


## The API
There are two primary endpoints: /latest and /stats.

### Latest Endpoint
The latest endpoint returns the 20 latest tweets collected, it contains the author, the tweet content, a list of countries mentioned and a sentiment score (if sentiment is being analysed). 
```
{
  "status" : "200 OK",
  "data" : [
    {
      "content" : "I am so proud of our national team ♥️ #Russia2018 #Egypt 🇪🇬🏆 #ThePharaohs #WorldCup",
      "author" : "Mai⚜️",
      "sentimentScore" : 96,
      "mentionedCountries" : [
        {
          "Egypt" : {
            "name" : "Egypt"
          }
        }
      ]
    },
    {
      "content" : "Almost game time .VAI 🇧🇷🤙🏾 #FriendlyMatch #BrazilvsAustria #Rusia2018 #WorldCup #BRAZIL #FUTBOL https://t.co/yibRi5htUO",
      "author" : "BellachourinNJR",
      "sentimentScore" : 0,
      "mentionedCountries" : [
        {
          "Brazil" : {
            "name" : "Brazil"
          }
        }
      ]
    },
    {
      "content" : "🇦🇷 🏆 ⚽ How to watch all #Argentina matches from @FIFAWorldCup with FREE Live Streaming on @BBCSport or @ITV (Englis… https://t.co/E7iPYDfzXv",
      "author" : "Liberty Shield",
      "sentimentScore" : 0,
      "mentionedCountries" : [
        {
          "Argentina" : {
            "name" : "Argentina"
          }
        }
      ]
    }
  ]
}
```

### Stats Endpoint
The stats endpoint returns the stats, arranged by country, for all tweets collected - it records the total number of tweets for each country collected so far as well as the average sentiment.
```
{
  "status" : "200 OK",
  "data" : {
    "Brazil" : {
      "count" : 1,
      "sentimentCount" : 1,
      "averageSentiment" : 0
    },
    "Argentina" : {
      "count" : 1,
      "sentimentCount" : 1,
      "averageSentiment" : 0
    },
    "Egypt" : {
      "count" : 1,
      "sentimentCount" : 1,
      "averageSentiment" : 96
    }
  }
}
```
