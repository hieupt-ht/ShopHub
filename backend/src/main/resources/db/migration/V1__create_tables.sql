-- ShopHub
-- Flyway migration V1: Create database schema
-- SQL Server

CREATE TABLE users (
    id BIGINT IDENTITY(1,1) NOT NULL,
    username VARCHAR(50) NOT NULL,
    email VARCHAR(100) NOT NULL,
    password VARCHAR(255) NOT NULL,
    full_name NVARCHAR(100) NULL,
    phone VARCHAR(20) NULL,
    role VARCHAR(20) NOT NULL CONSTRAINT DF_users_role DEFAULT 'CUSTOMER',
    created_at DATETIME2 NOT NULL CONSTRAINT DF_users_created_at DEFAULT SYSDATETIME(),
    updated_at DATETIME2 NULL,

    CONSTRAINT PK_users PRIMARY KEY (id),
    CONSTRAINT UQ_users_username UNIQUE (username),
    CONSTRAINT UQ_users_email UNIQUE (email),
    CONSTRAINT CK_users_role CHECK (role IN ('CUSTOMER', 'ADMIN'))
);

CREATE TABLE refresh_tokens (
    id BIGINT IDENTITY(1,1) NOT NULL,
    user_id BIGINT NOT NULL,

    token VARCHAR(500) NOT NULL,
    expires_at DATETIME2 NOT NULL,

    revoked BIT NOT NULL DEFAULT 0,

    created_at DATETIME2 NOT NULL DEFAULT SYSDATETIME(),

    CONSTRAINT PK_refresh_tokens PRIMARY KEY (id),

    CONSTRAINT UQ_refresh_tokens_token UNIQUE (token),

    CONSTRAINT FK_refresh_tokens_users
        FOREIGN KEY (user_id)
        REFERENCES users(id)
        ON DELETE CASCADE
);

CREATE INDEX IX_refresh_tokens_user_id
    ON refresh_tokens(user_id);

CREATE INDEX IX_refresh_tokens_token
    ON refresh_tokens(token);

CREATE TABLE categories (
    id BIGINT IDENTITY(1,1) NOT NULL,
    name NVARCHAR(100) NOT NULL,
    slug VARCHAR(120) NOT NULL,
    description NVARCHAR(500) NULL,
    created_at DATETIME2 NOT NULL CONSTRAINT DF_categories_created_at DEFAULT SYSDATETIME(),
    updated_at DATETIME2 NULL,

    CONSTRAINT PK_categories PRIMARY KEY (id),
    CONSTRAINT UQ_categories_slug UNIQUE (slug)
);

CREATE TABLE products (
    id BIGINT IDENTITY(1,1) NOT NULL,
    category_id BIGINT NOT NULL,
    name NVARCHAR(200) NOT NULL,
    slug VARCHAR(220) NOT NULL,
    description NVARCHAR(MAX) NULL,
    price DECIMAL(18,2) NOT NULL,
    stock INT NOT NULL CONSTRAINT DF_products_stock DEFAULT 0,
    status VARCHAR(20) NOT NULL CONSTRAINT DF_products_status DEFAULT 'ACTIVE',
    created_at DATETIME2 NOT NULL CONSTRAINT DF_products_created_at DEFAULT SYSDATETIME(),
    updated_at DATETIME2 NULL,

    CONSTRAINT PK_products PRIMARY KEY (id),
    CONSTRAINT UQ_products_slug UNIQUE (slug),
    CONSTRAINT FK_products_categories
        FOREIGN KEY (category_id) REFERENCES categories(id),
    CONSTRAINT CK_products_price CHECK (price >= 0),
    CONSTRAINT CK_products_stock CHECK (stock >= 0),
    CONSTRAINT CK_products_status CHECK (status IN ('ACTIVE', 'INACTIVE'))
);

CREATE TABLE product_images (
    id BIGINT IDENTITY(1,1) NOT NULL,
    product_id BIGINT NOT NULL,
    image_url VARCHAR(500) NOT NULL,
    is_thumbnail BIT NOT NULL CONSTRAINT DF_product_images_thumbnail DEFAULT 0,

    CONSTRAINT PK_product_images PRIMARY KEY (id),
    CONSTRAINT FK_product_images_products
        FOREIGN KEY (product_id)
        REFERENCES products(id)
        ON DELETE CASCADE
);

CREATE TABLE cart (
    id BIGINT IDENTITY(1,1) NOT NULL,
    user_id BIGINT NOT NULL,
    created_at DATETIME2 NOT NULL CONSTRAINT DF_cart_created_at DEFAULT SYSDATETIME(),
    updated_at DATETIME2 NULL,

    CONSTRAINT PK_cart PRIMARY KEY (id),
    CONSTRAINT UQ_cart_user UNIQUE (user_id),
    CONSTRAINT FK_cart_users
        FOREIGN KEY (user_id)
        REFERENCES users(id)
        ON DELETE CASCADE
);

CREATE TABLE cart_items (
    id BIGINT IDENTITY(1,1) NOT NULL,
    cart_id BIGINT NOT NULL,
    product_id BIGINT NOT NULL,
    quantity INT NOT NULL CONSTRAINT DF_cart_items_quantity DEFAULT 1,

    CONSTRAINT PK_cart_items PRIMARY KEY (id),
    CONSTRAINT UQ_cart_product UNIQUE (cart_id, product_id),
    CONSTRAINT FK_cart_items_cart
        FOREIGN KEY (cart_id)
        REFERENCES cart(id)
        ON DELETE CASCADE,
    CONSTRAINT FK_cart_items_product
        FOREIGN KEY (product_id)
        REFERENCES products(id),
    CONSTRAINT CK_cart_items_quantity CHECK (quantity > 0)
);

CREATE TABLE orders (
    id BIGINT IDENTITY(1,1) NOT NULL,
    user_id BIGINT NOT NULL,
    total_amount DECIMAL(18,2) NOT NULL,
    status VARCHAR(30) NOT NULL CONSTRAINT DF_orders_status DEFAULT 'PENDING',
    shipping_name NVARCHAR(100) NOT NULL,
    shipping_phone VARCHAR(20) NOT NULL,
    shipping_address NVARCHAR(500) NOT NULL,
    created_at DATETIME2 NOT NULL CONSTRAINT DF_orders_created_at DEFAULT SYSDATETIME(),
    updated_at DATETIME2 NULL,

    CONSTRAINT PK_orders PRIMARY KEY (id),
    CONSTRAINT FK_orders_users
        FOREIGN KEY (user_id) REFERENCES users(id),
    CONSTRAINT CK_orders_total CHECK (total_amount >= 0),
    CONSTRAINT CK_orders_status CHECK (
        status IN (
            'PENDING',
            'CONFIRMED',
            'SHIPPING',
            'COMPLETED',
            'CANCELLED'
        )
    )
);

CREATE TABLE order_items (
    id BIGINT IDENTITY(1,1) NOT NULL,
    order_id BIGINT NOT NULL,
    product_id BIGINT NOT NULL,
    product_name NVARCHAR(200) NOT NULL,
    price DECIMAL(18,2) NOT NULL,
    quantity INT NOT NULL,

    CONSTRAINT PK_order_items PRIMARY KEY (id),
    CONSTRAINT FK_order_items_order
        FOREIGN KEY (order_id)
        REFERENCES orders(id)
        ON DELETE CASCADE,
    CONSTRAINT FK_order_items_product
        FOREIGN KEY (product_id)
        REFERENCES products(id),
    CONSTRAINT CK_order_items_price CHECK (price >= 0),
    CONSTRAINT CK_order_items_quantity CHECK (quantity > 0)
);

-- Indexes for common lookup paths.
CREATE INDEX IX_products_category_id ON products(category_id);
CREATE INDEX IX_products_status ON products(status);
CREATE INDEX IX_product_images_product_id ON product_images(product_id);
CREATE INDEX IX_cart_items_product_id ON cart_items(product_id);
CREATE INDEX IX_orders_user_id ON orders(user_id);
CREATE INDEX IX_orders_status ON orders(status);
CREATE INDEX IX_order_items_order_id ON order_items(order_id);
CREATE INDEX IX_order_items_product_id ON order_items(product_id);
