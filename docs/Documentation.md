## API Documentation

### List Entities

1. [Users](#Users)
2. [Products](#Products)
3. [Addresses](#Addresses)
4. [Orders](#Orders)

### Users

1. ##### Get All Users `GET METHOD`
   endpoint : `/api/users`  
   json response :
   ```
   {
   "data": [
       {
           "id": 1,
           "first_name": "Muhammad Hannan",
           "last_name": "Abrar",
           "email": "muhamm@gmail.com",
           "phone_number": "225150600111023",
           "type": "SELLER"
       },
       {
           "id": 2,
           "first_name": "Zakiyyah Asma",
           "last_name": "Amanyna Wardani",
           "email": "zakiyy@gmail.com",
           "phone_number": "225150600111024",
           "type": "SELLER"
       },
       {
           "id": 3,
           "first_name": "Auralia Laksa",
           "last_name": "Aji",
           "email": "aurali@gmail.com",
           "phone_number": "225150600111025",
           "type": "SELLER"
       }
   ],
   "message": "successfully fetch data",
   "status": 200
   }
   ```
2. ##### Get Users By Type `GET METHOD`
   endpoint : `api/users?type=buyer`  
   json response :
   ```
   {
   "data": [
       {
           "id": 18,
           "first_name": "Theresia Surya",
           "last_name": "Sabatini",
           "email": "theres@gmail.com",
           "phone_number": "225150601111025",
           "type": "BUYER"
       },
       {
           "id": 19,
           "first_name": "Thariq Azam",
           "last_name": "Firdaus",
           "email": "thariq@gmail.com",
           "phone_number": "225150601111026",
           "type": "BUYER"
       },
       {
           "id": 20,
           "first_name": "Annida Rahma",
           "last_name": "Nadhifa",
           "email": "annida@gmail.com",
           "phone_number": "225150601111027",
           "type": "BUYER"
       }
   ],
   "message": "successfully fetch data",
   "status": 200
   }
   ```
3. ##### Get Users By Id `GET METHOD`
   endpoint : `api/users/{id}`  
   json response if found :
   ```
   {
   "data": {
       "id": 9,
       "first_name": "Devan",
       "last_name": "Ferrel",
       "email": "devanf@gmail.com",
       "phone_number": "225150600111031",
       "type": "SELLER"
   },
   "message": "successfully fetch data",
   "status": 200
   }
   ```
   json response if not found :
   ```
   {
   "data": null,
   "message": "data not found",
   "status": 404
   }
   ```
4. ##### Add User `POST METHOD`

   endpoint : `api/users`  
    json request body :

   ```
   {
   "first_name": "first",
   "last_name": "last",
   "email": "firstlast@gmail.com",
   "phone_number": "6269",
   "type": "BUYER"
   }
   ```

   json response success :

   ```
   {
   "data": {
       "id": 1,
       "first_name": "first",
       "last_name": "last",
       "email": "firstlast@gmail.com",
       "phone_number": "6269",
       "type": "BUYER"
   },
   "message": "successfully add data",
   "status": 201
   }
   ```

   json response conflict :

   ```
   {
   "data": null,
   "message": "email already exist",
   "status": 409
   }
   ```

   json response request body null :

   ```
   {
   "data": null,
   "message": "request values still null",
   "status": 400
   }
   ```

5. ##### Update User `PATCH METHOD`
   endpoint : `api/users/{id}`
   json request body :
   ```
   {
   "first_name": "Ananta",
   "last_name": "Risky"
   }
   ```
   json response :
   ```
   {
   "data": null,
   "message": "successfully update data",
   "status": 200
   }
   ```
6. #### Delete User `DELETE METHOD`
   endpoint : `api/users/{id}`  
    json response :
   ```
   {
   "data": null,
   "message": "successfully delete data",
   "status": 200
   }
   ```
   json response if doesnt exist :
   ```
   {
   "data": null,
   "message": "failed to delete data",
   "status": 404
   }
   ```

### Products

1. ##### Get All Products `GET METHOD`
   endpoint : `api/products`  
   json response :
   ```
   {
   "data": [
       {
           "id": 1,
           "seller": 1,
           "title": "Product 1 by Seller 1",
           "description": "Description of Product 1 by Seller 1",
           "price": 12.00,
           "stock": 12
       },
       {
           "id": 2,
           "seller": 1,
           "title": "Product 2 by Seller 1",
           "description": "Description of Product 2 by Seller 1",
           "price": 13000,
           "stock": 13
       },
   ],
   "message": "successfully fetch data",
   "status": 200
   }
   ```
2. ##### Get Product By Id `GET METHOD`
   endpoint : `api/products/{id}`  
    json response :
   ```
   {
   "data": {
       "id": 1,
       "seller": 1,
       "title": "Product 1 by Seller 1",
       "description": "Description of Product 1 by Seller 1",
       "price": 31200,
       "stock": 31200
   },
   "message": "successfully fetch data",
   "status": 200
   }
   ```
   json response if not found :
   ```
   {
   "data": null,
   "message": "data not found",
   "status": 404
   }
   ```
3. ##### Get Product By Seller Id `GET METHOD`
   endpoint : `api/products?sellerId=?`  
   json response :
   ```
   {
    "data": [
        {
            "id": 1,
            "seller": 1,
            "title": "Product 1 by Seller 1",
            "description": "Description of Product 1 by Seller 1",
            "price": 31200,
            "stock": 31200
        },
        {
            "id": 2,
            "seller": 1,
            "title": "Product 2 by Seller 1",
            "description": "Description of Product 2 by Seller 1",
            "price": 39000,
            "stock": 39000
        }
    ],
    "message": "successfully fetch data",
    "status": 200
   }
   ```
4. ##### Get Product By Price Range `GET METHOD`
   endpoint : `api/products?min=40000&&max=50000`
   json response :
   ```
   {
   "data": [
       {
           "id": 7,
           "seller": 4,
           "title": "Product 1 by Seller 4",
           "description": "Description of Product 1 by Seller 4",
           "price": 45000,
           "stock": 45000
       },
       {
           "id": 9,
           "seller": 5,
           "title": "Product 1 by Seller 5",
           "description": "Description of Product 1 by Seller 5",
           "price": 43200,
           "stock": 43200
       },
       {
           "id": 10,
           "seller": 5,
           "title": "Product 2 by Seller 5",
           "description": "Description of Product 2 by Seller 5",
           "price": 45900,
           "stock": 45900
       },
       {
           "id": 11,
           "seller": 6,
           "title": "Product 1 by Seller 6",
           "description": "Description of Product 1 by Seller 6",
           "price": 44200,
           "stock": 44200
       }
   ],
   "message": "successfully fetch data",
   "status": 200
   }
   ```
   endpoint : `api/products?min=80000`  
    json response :
   ```
   {
   "data": [
       {
           "id": 21,
           "seller": 11,
           "title": "Product 1 by Seller 11",
           "description": "Description of Product 1 by Seller 11",
           "price": 81400,
           "stock": 81400
       },
       {
           "id": 34,
           "seller": 17,
           "title": "Product 2 by Seller 17",
           "description": "Description of Product 2 by Seller 17",
           "price": 81200,
           "stock": 81200
       }
   ],
   "message": "successfully fetch data",
   "status": 200
   }
   ```
   endpoint : `api/products?max=35000`
   json response :
   ```
   {
   "data": [
       {
           "id": 1,
           "seller": 1,
           "title": "Product 1 by Seller 1",
           "description": "Description of Product 1 by Seller 1",
           "price": 31200,
           "stock": 31200
       },
       {
           "id": 4,
           "seller": 2,
           "title": "Product 2 by Seller 2",
           "description": "Description of Product 2 by Seller 2",
           "price": 33600,
           "stock": 33600
       }
   ],
   "message": "successfully fetch data",
   "status": 200
   }
   ```
5. ##### Add Product `POST METHOD`
   endpoint : `api/products/{userId}`
   json request body :
   ```
   {
   "seller": 9,
   "title": "Backend for Beginner",
   "description": "Backend using js and mysql database",
   "price": 20000,
   "stock": 10
   }
   ```
   json response :
   ```
   {
   "data": {
       "id": 1,
       "seller": 1,
       "title": "Backend for Beginner",
       "description": "Backend using js and mysql database",
       "price": 20000,
       "stock": 10
   },
   "message": "successfully add new data",
   "status": 201
   }
   ```
   json response if seller_id not found :
   ```
   {
   "data": null,
   "message": "seller not found",
   "status": 404
   }
   ```
   json response if userId passed is not seller :
   ```
   {
   "data": null,
   "message": "user is not a seller",
   "status": 409
   }
   ```
6. ##### Update Product `PATCH METHOD`
   endpoint : `api/products/{id}`  
   json request body :
   ```
   {
    "title": "Frontnend for Beginner",
    "description": "Frontend using vite and react",
    "price": 10000,
    "stock": 5
   }
   ```
   json response :
   ```
   {
    "data": null,
    "message": "successfully update data",
    "status": 200
   }
   ```
   json response if not found :
   ```
   {
    "data": null,
    "message": "data not found",
    "status": 404
   }
   ```
7. ##### Delete Product `DELETE METHOD`
   endpoint : `api/products/{id}`
   json response :
   ```
   {
    "data": null,
    "message": "successfully delete data",
    "status": 200
   }
   ```

### Addresses

1. ###### Get Address By User Id `GET METHOD`
   endpoint : `api/addresses/{userId}`  
   json response :
   ```
   {
    "data": {
        "user": 18,
        "street": "jln ini malang",
        "city": "Kota Malang",
        "province": "Jawa Timur",
        "postcode": "189213",
        "country": "Indonesia"
    },
    "message": "successfully fetch data",
    "status": 200
   }
   ```
2. ##### Add Address With User Id `POST METHOD`
   endpoint : `api/addresses/{userId}`  
   json request body :
   ```
   {
    "street":"jln ini malang",
    "city": "Kota Malang",
    "province": "Jawa Timur",
    "postcode": "189213",
    "country": "Indonesia"
   }
   ```
   json response :
   ```
   {
    "data": {
        "user": 18,
        "street": "jln ini malang",
        "city": "Kota Malang",
        "province": "Jawa Timur",
        "postcode": "189213",
        "country": "Indonesia"
    },
    "message": "successfully add data",
    "status": 201
   }
   ```
   json response if user address already exist :
   ```
   {
    "data": null,
    "message": "user address already exist",
    "status": 409
   }
   ```
3. ##### Update Address `PATCH METHOD`
   endpoint : `api/addresses/{userId}`  
   json request body :
   ```
   {
    "street":"jln veteran",
    "city": "Kota Malang",
    "province": "Jawa Timur",
    "postcode": "189213"
   }
   ```
   json response :
   ```
   {
    "data": null
    "message": "successfully update data",
    "status": 200
   }
   ```
4. ###### Delete Address `DELETE METHOD`
   endpoint : `api/addresses/{userId}`  
   json response :
   ```
   {
    "data": null,
    "message": "successfully delete data",
    "status": 200
   }
   ```

### Orders

1. ##### Get Order By User Id
   endpoint : ```api/orders?userId={}``` ```GET METHOD```    
   json response : 
   ```
   {
    "data": [
        {
            "id": 19,
            "buyer": 18,
            "total": 440147,
            "discount": 49.0,
            "notes": "notes 13",
            "orderedAt": "2023-02-20 14:49:27",
            "paid": true
        },
        {
            "id": 23,
            "buyer": 18,
            "total": 246212,
            "discount": 38.0,
            "notes": "notes 163",
            "orderedAt": "2023-01-03 02:42:56",
            "paid": true
        }
    ],
    "message": "successfully fetch data",
    "status": 200
   }
   ```
2. ##### Get Order By Id
   endpoint : ```api/orders/{id}``` ```GET METHOD```    
   json response : 
   ```
   {
    "data": {
        "id": 1,
        "buyer": 33,
        "total": 269960,
        "discount": 24.0,
        "notes": "notes 16",
        "orderedAt": "2023-11-02 17:34:44",
        "paid": true
    },
    "message": "successfully fetch data",
    "status": 200
   } 
   ```
   json response if not found : 
   ```
   {
    "data": null,
    "message": "data not found",
    "status": 404
   }
   ```
   
3. ##### Get Order By Id With Detail
   endpoint : ```api/orders/{id}?detail={boolean}``` ```GET METHOD```    
   json response :
   ```
   {
    "data": {
        "id": 186,
        "buyer": 1,
        "total": 42069,
        "discount": 0.0,
        "notes": "notes 181",
        "orderedAt": "2023-08-01 13:19:37",
        "details": {
            "order": 186,
            "product": 1,
            "quantity": 10,
            "price": 10000.0
        },
        "paid": true
    },
    "message": "successfully fetch data",
    "status": 200
   }
   ```
4. ##### Add Order By User Id
   endpoint : ```api/orders/{userId}``` ```POST METHOD```        
   json request body : 
   ```
   {
    "total": 42069,
    "discount": 0.0,
    "notes": "notes 181",
    "paid": true,
    "details": {
        "product": 1,
        "quantity": 10,
        "price": 10000
    }
   }
   ```
   json response : 
   ```
   {
    "data": {
        "id": 186,
        "buyer": 1,
        "total": 42069,
        "discount": 0.0,
        "notes": "notes 181",
        "orderedAt": "2023-08-01 13:19:37",
        "details": {
            "order": 186,
            "product": 1,
            "quantity": 10,
            "price": 10000.0
        },
        "paid": true
    },
    "message": "successfully add data",
    "status": 201
   }
   ```
5. ##### Update Order
   endpoint : ```api/orders/{id}``` ```PATCH METHOD```     
   json request body : 
   ```
   {
    "total": 42069,
    "discount": 0.0,
    "notes": "notes 181",
    "paid": false
   }
   ```
   json response : 
   ```
   {
    "data": null,
    "message": "successfully update data",
    "status": 200
   }
   ```
6. ##### Delete Order
   endpoint : ```api/orders/{id}``` ```DELETE METHOD```   
   json response : 
   ```
   {
    "data": null,
    "message": "successfully delete data",
    "status": 200
   }
   ```
   
7. ##### Get Only Details Of Order By Order Id ```GET METHOD```
   endpoint : ```api/orders/details/{orderId}```    
   json response : 
   ```
   {
    "data": {
        "order": 186,
        "product": 1,
        "quantity": 10,
        "price": 10000.0
    },
    "message": "successfully fetch data",
    "status": 200
   }
   ```
