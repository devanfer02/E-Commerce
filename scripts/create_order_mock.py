from random import randint

def generate_random_notes() :
    return "notes " + str(randint(1,180));

def generate_random_ordered_at() :
    year = 2023;
    month = randint(1,12);
    day = randint(1,30);
    hour = randint(0,23);
    minute = randint(0,59);
    second = randint(0,59);
    return f"{year:04d}-{month:02d}-{day:02d} {hour:02d}:{minute:02d}:{second:02d}";

with open("../db/orders/mock_data_orders.sqlite", "w") as file :
    file.truncate(0);
    for i in range(1,181) :
        buyer_id = randint(18,34);
        notes = generate_random_notes();
        total = randint(10000,1000000);
        discount = randint(5,50);
        ordered_at = generate_random_ordered_at();
        is_paid = randint(0,1);

        file.write(f"INSERT INTO orders (id, buyer_id, notes, total, discount, ordered_at, is_paid) VALUES ({i}, {buyer_id}, '{notes}', {total}, {discount}, '{ordered_at}', {is_paid});\n")

print("done");