CREATE TABLE field (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    field_name VARCHAR(255) NOT NULL,
    count_ball INT NOT NULL,
    size_field_long INT NOT NULL,
    size_field_wide INT NOT NULL,
    price_field_on_weekend DOUBLE PRECISION NOT NULL,
    price_field_on_weekday DOUBLE PRECISION NOT NULL,
    image_url VARCHAR(255) NOT NULL,
);