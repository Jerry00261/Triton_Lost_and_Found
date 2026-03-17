CREATE TABLE posts (
    id BIGSERIAL PRIMARY KEY,
    title VARCHAR(120) NOT NULL,
    description TEXT NOT NULL,
    location VARCHAR(120),
    post_type VARCHAR(20) NOT NULL CHECK (post_type IN ('LOST', 'FOUND')),
    status VARCHAR(20) NOT NULL DEFAULT 'OPEN',
    private_identifying_details TEXT,
    created_at TIMESTAMP NOT NULL DEFAULT NOW()
);

CREATE TABLE claims (
    id BIGSERIAL PRIMARY KEY,
    post_id BIGINT NOT NULL,
    claimer_name VARCHAR(100) NOT NULL,
    claimer_email VARCHAR(255) NOT NULL,
    claim_message TEXT,
    identifying_details TEXT NOT NULL,
    claim_status VARCHAR(20) NOT NULL DEFAULT 'PENDING',
    created_at TIMESTAMP NOT NULL DEFAULT NOW(),

    CONSTRAINT fk_claims_post
        FOREIGN KEY (post_id)
        REFERENCES posts(id)
        ON DELETE CASCADE
);