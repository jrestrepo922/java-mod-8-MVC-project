## General ##
1. data base is seeded with genres. 
## How to test the APIs ##
1. ### POST /api/books ###
   1. {
      "title": "rich dad poor dad",
      "pages": 1,
      "published": "2015-04-23T18:25:43.511Z",
      "author": {
      "name": "Juan valdez"
      },
      "genresNames": [
      { "name":  "mystery"},
      {"name": "horror"}
      ]
      }
2. ### PUT /api/books/{id} ###
   1.     {
      "title": "a great book",
      "pages": 100,
      "published": "2015-04-23"
   }
   2. The only fields that can be updated are those listed on the JSON example.

3. ### POST api/users ###
   1. {
      "username": "Juan",
      "password": "1234"
      }
4. ### POST /api/users/{id}/reading_lists ###
   1. {
      "name": "self help list"
      }

The rest of the GET and DELETE APIs are easy to test as long as you use the right IDs. 