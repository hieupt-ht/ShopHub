-- ShopHub
-- Flyway migration V2: Seed development data
-- SQL Server
--
-- IMPORTANT:
-- Passwords below are BCrypt hashes for development/demo users.
-- Change them before using any real environment.

INSERT INTO users (
    username,
    email,
    password,
    full_name,
    phone,
    role
)
VALUES
(
    'admin',
    'admin@shophub.local',
    '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy',
    N'ShopHub Admin',
    '0900000001',
    'ADMIN'
),
(
    'customer',
    'customer@shophub.local',
    '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy',
    N'Nguyễn Văn Customer',
    '0900000002',
    'CUSTOMER'
);

INSERT INTO categories (
    name,
    slug,
    description
)
VALUES
(
    N'Điện thoại',
    'dien-thoai',
    N'Điện thoại thông minh và thiết bị di động'
),
(
    N'Laptop',
    'laptop',
    N'Máy tính xách tay cho học tập và công việc'
),
(
    N'Phụ kiện',
    'phu-kien',
    N'Phụ kiện công nghệ'
);

INSERT INTO products (
    category_id,
    name,
    slug,
    description,
    price,
    stock,
    status
)
VALUES
(
    1,
    N'iPhone 17',
    'iphone-17',
    N'Smartphone Apple thế hệ mới.',
    24990000,
    20,
    'ACTIVE'
),
(
    1,
    N'Samsung Galaxy S26',
    'samsung-galaxy-s26',
    N'Smartphone Android cao cấp.',
    22990000,
    15,
    'ACTIVE'
),
(
    1,
    N'Google Pixel 10',
    'google-pixel-10',
    N'Smartphone Google với trải nghiệm Android thuần.',
    19990000,
    12,
    'ACTIVE'
),
(
    2,
    N'MacBook Air M4',
    'macbook-air-m4',
    N'Laptop mỏng nhẹ dành cho học tập và công việc.',
    29990000,
    10,
    'ACTIVE'
),
(
    2,
    N'Dell XPS 14',
    'dell-xps-14',
    N'Laptop cao cấp cho công việc và lập trình.',
    35990000,
    8,
    'ACTIVE'
),
(
    2,
    N'ASUS Vivobook 15',
    'asus-vivobook-15',
    N'Laptop phổ thông cho sinh viên.',
    15990000,
    25,
    'ACTIVE'
),
(
    3,
    N'Apple AirPods Pro',
    'apple-airpods-pro',
    N'Tai nghe không dây chống ồn.',
    5990000,
    30,
    'ACTIVE'
),
(
    3,
    N'Logitech MX Master 3S',
    'logitech-mx-master-3s',
    N'Chuột không dây cao cấp cho công việc.',
    2490000,
    18,
    'ACTIVE'
),
(
    3,
    N'Keychron K2',
    'keychron-k2',
    N'Bàn phím cơ không dây nhỏ gọn.',
    2190000,
    22,
    'ACTIVE'
);

INSERT INTO product_images (
    product_id,
    image_url,
    is_thumbnail
)
VALUES
(1, 'https://placehold.co/800x800?text=iPhone+17', 1),
(1, 'https://placehold.co/800x800?text=iPhone+17+Back', 0),
(2, 'https://placehold.co/800x800?text=Galaxy+S26', 1),
(3, 'https://placehold.co/800x800?text=Pixel+10', 1),
(4, 'https://placehold.co/800x800?text=MacBook+Air+M4', 1),
(5, 'https://placehold.co/800x800?text=Dell+XPS+14', 1),
(6, 'https://placehold.co/800x800?text=ASUS+Vivobook+15', 1),
(7, 'https://placehold.co/800x800?text=AirPods+Pro', 1),
(8, 'https://placehold.co/800x800?text=MX+Master+3S', 1),
(9, 'https://placehold.co/800x800?text=Keychron+K2', 1);

-- Create an empty cart for the demo customer.
INSERT INTO cart (user_id)
SELECT id
FROM users
WHERE username = 'customer';
