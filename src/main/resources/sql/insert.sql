INSERT INTO cc_code (code_id, code_type, code_name, display_order, created_at, updated_at, spare)
VALUES
    (1, 'ROLE', 'ROLE_ADMIN', 1, NOW(), NOW(), NULL),
    (2, 'ROLE', 'ROLE_MANAGER', 2, NOW(), NOW(), NULL),
    (3, 'ROLE', 'ROLE_USER', 3, NOW(), NOW(), NULL);

INSERT INTO cc_rank (
    rank_id, parent_rank_id, rank_name, display_order, is_active, created_at, updated_at, spare
)
VALUES
    (1, NULL, '대표이사', 1, 'Y', NOW(), NOW(), NULL),
    (2, 1, '전무', 2, 'Y', NOW(), NOW(), NULL),
    (3, 2, '상무', 3, 'Y', NOW(), NOW(), NULL),
    (4, 3, '이사', 4, 'Y', NOW(), NOW(), NULL),
    (5, 4, '부장', 5, 'Y', NOW(), NOW(), NULL),
    (6, 5, '차장', 6, 'Y', NOW(), NOW(), NULL),
    (7, 6, '과장', 7, 'Y', NOW(), NOW(), NULL),
    (8, 7, '대리', 8, 'Y', NOW(), NOW(), NULL),
    (9, 8, '주임', 9, 'Y', NOW(), NOW(), NULL),
    (10, 9, '사원', 10, 'Y', NOW(), NOW(), NULL);
