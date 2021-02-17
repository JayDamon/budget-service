INSERT INTO account_type (account_type_id, full_account_type, short_account_type)
VALUES (1, 'Basic Checking', 'Checking'),
       (2, 'Savings', 'Savings'),
       (3, 'Interest Bearing Checking', 'Interest'),
       (4, 'Money Market', 'MM'),
       (5, 'Certification of Deposit', 'CD'),
       (6, 'Investment Retirement', 'Retirement'),
       (7, 'Brokerage', 'Brokerage'),
       (8, 'Credit Card', 'Credit Card');

INSERT INTO frequency_type(frequency_type_id, frequency_type, month_factor)
VALUES (1, 'Weekly', 4),
       (2, 'Monthly', 1),
       (3, 'Bi-Weekly', 2),
       (4, 'Hourly', 672),
       (5, 'Annual', .08333333);

INSERT INTO goal_type (goal_type_id, goal_type)
VALUES (1, 'save'),
       (2, 'pay');

INSERT INTO budget_category_type (budget_category_type_id, budget_category_type)
VALUES (1, 'fixed'),
       (2, 'flexible'),
       (3, 'discretionary');
-- INSERT INTO budget_category (budget_category_id, category_name) VALUES (1, 'Income');
-- INSERT INTO budget_category (budget_category_id, category_name) VALUES (2, 'Discressionary');
-- INSERT INTO budget_category (budget_category_id, category_name) VALUES (3, 'Non-Discressionary');
-- INSERT INTO budget_category (budget_category_id, category_name) VALUES (4, 'Bills');
-- INSERT INTO budget_category (budget_category_id, category_name) VALUES (5, 'Savings');
-- INSERT INTO budget_category (budget_category_id, category_name) VALUES (6, 'Investment');
-- INSERT INTO budget_category (budget_category_id, category_name) VALUES (7, 'Other');

INSERT INTO budget_category_name (budget_category_name_id, category_name)
VALUES (1, 'income'),
       (2, 'bill'),
       (3, 'spending'),
       (4, 'subscription'),
       (5, 'savings'),
       (6, 'investment');

INSERT INTO budget_category (budget_category_id, budget_category_type_id, budget_category_name_id)
VALUES (1, 1, 1), -- fixed income
       (2, 1, 2), -- fixed bill
       (3, 1, 3), -- fixed spending
       (4, 2, 1), -- flexible income
       (5, 2, 2), -- flexible bill
       (6, 2, 3), -- flexible spending
       (7, 3, 4), -- discretionary subscription
       (8, 3, 5), -- discretionary savings
       (9, 3, 6), -- discretionary investment
       (10, 3, 3);
-- discretionary spending

-- TODO change to transaction sub category
INSERT INTO budget_sub_category (budget_sub_category_id, sub_category_name)
VALUES (1, 'Income'),
       (2, 'Housing'),
       (3, 'Utilities'),
       (4, 'Credit Card'),
       (5, 'Health'),
       (6, 'Banking'),
       (7, 'Food/Beverage'),
       (8, 'Gas/Automotive'),
       (9, 'Household/Supplies'),
       (10, 'Entertainment'),
       (11, 'Education'),
       (12, 'Gifts and Contributions'),
       (13, 'Personal'),
       (14, 'Business'),
       (15, 'Taxes'),
       (16, 'Miscellaneous'),
       (17, 'Savings'),
       (18, 'Generic');

-- TODO this is just a list of generics and is being turned into a budget, not for user storage
INSERT INTO budget_item (budget_item_id, budget_category_id, name)
VALUES (1, 2, 'Rent / Mortgage'),
       (2, 2, 'Cable / Internet'),
       (3, 2, 'Cable'),
       (4, 2, 'Internet'),
       (5, 2, 'Satellite'),
       (6, 2, 'Telephone'),
       (7, 2, 'Cell Phone'),
       (8, 2, 'Health Insurance'),
       (9, 5, 'Electricity'),
       (10, 5, 'Oil'),
       (11, 5, 'Telephone'),
       (12, 5, 'Cell Phone'),
       (13, 6, 'Groceries'),
       (14, 6, 'Gas'),
       (15, 6, 'Household Items'),
       (16, 7, 'Netflix'),
       (17, 7, 'Hulu'),
       (18, 7, 'Amazon Prime'),
       (19, 8, 'Rainy Day Fund'),
       (20, 8, 'Home Downpayment'),
       (21, 8, 'Vacation'),
       (22, 9, 'Retirement / 401k'),
       (23, 9, 'Stocks'),
       (24, 9, 'Mutual Funds'),
       (25, 9, 'Index Funds'),
       (26, 9, 'Exchange-traded funds'),
       (27, 9, 'Options'),
       (28, 1, 'General'),
       (29, 2, 'General'),
       (30, 3, 'General'),
       (31, 4, 'General'),
       (32, 5, 'General'),
       (33, 6, 'General'),
       (34, 7, 'General'),
       (35, 8, 'General'),
       (36, 9, 'General'),
       (37, 10, 'General');

