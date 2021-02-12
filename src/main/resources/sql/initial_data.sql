INSERT INTO transaction_type (transaction_type_id, transaction_type)
VALUES (1, 'Income'),
       (2, 'Expense');

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

INSERT INTO occurrence(occurrence_id, occurrence)
VALUES (1, 'Specific Date'),
       (2, 'End of Month'),
       (3, 'First of Month'),
       (4, 'Monday'),
       (5, 'Tuesday'),
       (6, 'Wednesday'),
       (7, 'Thursday'),
       (8, 'Friday'),
       (9, 'Saturday'),
       (10, 'Sunday');

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

INSERT INTO transaction_category (transaction_category_id, category_name, budget_sub_category_id)
VALUES (1, 'Paycheck', 1),
       (2, 'Other Income', 1),
       (3, 'Rent/Mortgage', 2),
       (4, 'Cable/Internet', 3),
       (5, 'Cable', 3),
       (6, 'Satellite', 3),
       (7, 'Internet', 3),
       (8, 'Electricity', 3),
       (9, 'Oil', 3),
       (10, 'Water', 3),
       (11, 'Telephone', 3),
       (12, 'Garbage Pickup', 3),
       (13, 'Cell Phone', 3),
       (14, 'Payment/Credit', 4),
       (15, 'Interest', 4),
       (16, 'Annual Fee', 4),
       (17, 'Finance Charge', 4),
       (18, 'Over the Limit Fee', 4),
       (19, 'Minimum Usage Fee', 4),
       (20, 'Cash Advance Fee', 4),
       (21, 'Late Fee', 4),
       (22, 'Rewards Program', 4),
       (23, 'Health Insurance', 5),
       (24, 'Dental Insurance', 5),
       (25, 'Medical and Hospital Costs', 5),
       (26, 'Doctor/Dentist', 5),
       (27, 'Medicine', 5),
       (28, 'Glasses', 5);

INSERT INTO transaction_category (transaction_category_id, category_name, budget_sub_category_id)
VALUES (29, 'Hearing Aids', 5),
       (30, 'First Aid Supplies', 5),
       (31, 'Treatment and Therapy', 5),
       (32, 'Fitness Club Membership', 5),
       (33, 'Investments', 17),
       (34, 'Emergency Fund', 17),
       (35, 'Retirement', 17),
       (36, 'Interest', 6),
       (37, 'Banking Fees', 6),
       (38, 'Groceries', 7),
       (39, 'Dining', 7),
       (40, 'Liquor/Beer', 7),
       (41, 'Snacks, Coffee Breaks', 7),
       (42, 'School Lunch', 7),
       (43, 'Work Lunch', 7),
       (44, 'Home Food Production', 7),
       (45, 'Gas', 8),
       (46, 'Insurance', 8),
       (47, 'Maintenance', 8),
       (48, 'Supplies/Tools', 8),
       (49, 'Licensing Fees', 8),
       (50, 'Taxi', 8),
       (51, 'Bus/Subway/Train', 8),
       (52, 'Parking', 8),
       (53, 'Tolls', 8),
       (54, 'Laundry Supplies', 9),
       (55, 'Cleaning Supplies', 9),
       (56, 'Furniture', 9),
       (57, 'Appliances', 9),
       (58, 'Dishes and Cutlery', 9),
       (59, 'Cooking Supplies', 9),
       (60, 'Linens', 9),
       (61, 'Soap and Shampoo', 9),
       (62, 'Facial Tissue', 9),
       (63, 'Yard Maintenance', 9),
       (64, 'House Repairs', 9),
       (65, 'Home Project Supplies', 9),
       (66, 'Safety Deposit Box Rental', 9),
       (67, 'Yard Improvement and Supplies', 9),
       (68, 'Books', 10),
       (69, 'Magazines', 10),
       (70, 'Movie Theater', 10),
       (71, 'Video Streaming/Rental/Pay Per View', 10),
       (72, 'Sports/Games', 10),
       (73, 'Sporting Events', 10),
       (74, 'Sporting Goods', 10),
       (75, 'Concerts', 10),
       (76, 'Music', 10),
       (77, 'Cultural Events', 10),
       (78, 'Video Games', 10),
       (79, 'Toys', 10),
       (80, 'Tourist Attractions', 10),
       (81, 'Electronics', 10),
       (82, 'Tuition', 11),
       (83, 'Books', 11),
       (84, 'Stationary', 11),
       (85, 'Courses/Lessons', 11),
       (86, 'Gifts', 12),
       (87, 'Cards and Wrapping Paper', 12),
       (88, 'Flowers', 12),
       (89, 'Charitable Donations', 12),
       (90, 'Haircuts', 13),
       (91, 'Beauty Shop', 13),
       (92, 'Cosmetics', 13),
       (93, 'Toiletries', 13),
       (94, 'Shaving Supplies', 13),
       (95, 'Clothing', 13),
       (96, 'Alterations', 13),
       (97, 'Cleaning', 13),
       (98, 'Miscellaneous', 13),
       (99, 'Attire', 14),
       (100, 'Office Supplies', 14),
       (101, 'Software', 14),
       (102, 'State Income Tax', 15),
       (103, 'Federal Income Tax', 15),
       (104, 'Social Security Tax', 15),
       (105, 'Medicare Tax', 15),
       (106, 'Investment Tax', 15),
       (107, 'Tax Fees', 15),
       (108, 'Software', 15),
       (109, 'Vacation', 16),
       (110, 'Unknown', 16),
       (111, 'Other', 16),
       (112, 'Transfer', 6);
