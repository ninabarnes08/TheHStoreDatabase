-- categories definition
CREATE TABLE categories (
	id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
	category TEXT NOT NULL
);

-- pricing definition
CREATE TABLE pricing (
	products_id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
	price INTEGER NOT NULL,
	date_info INTEGER NOT NULL,
	CONSTRAINT pricing_products_FK FOREIGN KEY (products_id) REFERENCES products(id)
);

-- products definition
CREATE TABLE products (
	id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
	name TEXT NOT NULL,
	category_id INTEGER NOT NULL,
	season_id INTEGER NOT NULL,
	CONSTRAINT products_categories_FK FOREIGN KEY (category_id) REFERENCES categories(id),
	CONSTRAINT products_seasonal_info_FK FOREIGN KEY (season_id) REFERENCES seasonal_info(id)
);

-- quantities definition
CREATE TABLE quantities (
	product_id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
	inventory_available INTEGER NOT NULL,
	CONSTRAINT quantities_products_FK FOREIGN KEY (product_id) REFERENCES products(id)
);

-- seasonal_info definition
CREATE TABLE seasonal_info (
	id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
	name TEXT NOT NULL,
	start_date INTEGER NOT NULL,
	end_date INTEGER NOT NULL
);