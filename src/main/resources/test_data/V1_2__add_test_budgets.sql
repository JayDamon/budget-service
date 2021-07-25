INSERT INTO budget (budget_id, budget_category_id, budget_item_name, start_date, end_date,
                    frequency_type_id, amount, in_use, transaction_type_id)
VALUES (1, 2, 'Rent / Mortgage', '2017-01-01', NULL, 2, 800, 1, 2),
       (2, 2, 'Auto Loan / Lease', '2017-01-01', NULL, 2, 350, 1, 2),
       (3, 2, 'Auto Insurance', '2017-01-01', NULL, 2, 350, 1, 2),
       (4, 2, 'Health Insurance', '2017-01-01', NULL, 2, 350, 1, 2),
       (5, 2, 'Cable / Internet', '2017-01-01', NULL, 2, 102.25, 1, 2),
       (6, 2, 'Cell Phone', '2017-01-01', NULL, 2, 75.32, 1, 2),
       (7, 6, 'Groceries', '2017-01-01', NULL, 2, 100, 1, 2),
       (8, 6, 'Gas', '2017-01-01', NULL, 2, 100, 1, 2),
       (9, 6, 'Household Items', '2017-01-01', NULL, 2, 50, 1, 2),
       (10, 7, 'Amazon Prime', '2017-01-01', NULL, 5, 99, 1, 2),
       (11, 7, 'Plex', '2017-01-01', NULL, 2, 3.99, 1, 2),
       (12, 7, 'Netflix', '2017-01-01', NULL, 2, 10.99, 1, 2),
       (13, 7, 'Spotify', '2017-01-01', NULL, 2, 10.99, 1, 2),
       (14, 7, 'Adobe', '2017-01-01', '2018-12-31', 2, 20.39, 1, 2),
       (15, 7, 'Audible', '2017-01-01', NULL, 2, 20.39, 1, 2),
       (16, 8, 'Rainy Day Fund', '2017-01-01', NULL, 2, 100, 1, 2),
       (17, 8, 'Home Downpayment', '2017-01-01', NULL, 2, 1000, 1, 2),
       (18, 8, 'Vacation', '2017-01-01', NULL, 2, 200, 1, 2),
       (19, 9, 'Retirement / 401k', '2017-01-01', NULL, 2, 50, 1, 2),
       (20, 1, 'General', '2017-01-01', NULL, 2, 3000, 1, 1), -- fixed income
       (21, 2, 'General', '2017-01-01', NULL, 2, 50, 1, 2),   -- fixed bill
       (22, 3, 'General', '2017-01-01', NULL, 2, 50, 1, 2),   -- fixed spending
       (23, 4, 'General', '2017-01-01', NULL, 2, 0, 1, 1),    -- flexible income
       (24, 5, 'General', '2017-01-01', NULL, 2, 50, 1, 2),   -- flexible bill
       (25, 6, 'General', '2017-01-01', NULL, 2, 50, 1, 2),   -- flexible spending
       (26, 7, 'General', '2017-01-01', NULL, 2, 50, 1, 2),   -- discretionary subscription
       (27, 8, 'General', '2017-01-01', NULL, 2, 50, 1, 2),   -- discretionary savings
       (28, 9, 'General', '2017-01-01', NULL, 2, 50, 1, 2),   -- discretionary investment
       (29, 10, 'General', '2017-01-01', NULL, 2, 50, 1, 2),  -- discretionary spending
       (30, 10, 'Dining', '2017-01-01', NULL, 2, 50, 1, 2);
