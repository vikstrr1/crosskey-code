-- schema.sql

CREATE TABLE IF NOT EXISTS prospects (
    id serial PRIMARY KEY,
    customer_name VARCHAR(255) not null,
    loan_amount DOUBLE PRECISION not null,
    yearly_interest_rate DOUBLE PRECISION not null,
    loan_term_in_years INTEGER not null
);
