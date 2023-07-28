import random

total_sellers = 17
products_per_seller = 2

with open("../db/products/mock_data_products.sqlite", "w") as file :
    file.truncate(0);
    for seller_id in range(1, total_sellers + 1):
        for product_num in range(1, products_per_seller + 1):
            title = f'Product {product_num} by Seller {seller_id}'
            description = f'Description of Product {product_num} by Seller {seller_id}'
            price = '{:.0f}'.format((10 + seller_id + product_num) * 100 * random.randint(20,40))
            stock = 50 - seller_id - product_num

            sql_insert = f"INSERT INTO products (seller_id, title, description, price, stock) VALUES ({seller_id}, '{title}', '{description}', {price}, {stock});"
            file.write(sql_insert + "\n")

print("done")