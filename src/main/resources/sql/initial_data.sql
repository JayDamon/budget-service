INSERT INTO transaction_type (transaction_type_id, transaction_type) VALUES(1, 'Income');
INSERT INTO transaction_type (transaction_type_id, transaction_type) VALUES(2, 'Expense');

INSERT INTO account_type (account_type_id, full_account_type, short_account_type) VALUES (1, 'Basic Checking', 'Checking');
INSERT INTO account_type (account_type_id, full_account_type, short_account_type) VALUES (2, 'Savings', 'Savings');
INSERT INTO account_type (account_type_id, full_account_type, short_account_type) VALUES (3, 'Interest Bearing Checking', 'Interest');
INSERT INTO account_type (account_type_id, full_account_type, short_account_type) VALUES (4, 'Money Market', 'MM');
INSERT INTO account_type (account_type_id, full_account_type, short_account_type) VALUES (5, 'Certification of Deposit', 'CD');
INSERT INTO account_type (account_type_id, full_account_type, short_account_type) VALUES (6, 'Investment Retirement', 'Retirement');
INSERT INTO account_type (account_type_id, full_account_type, short_account_type) VALUES (7, 'Brokerage', 'Brokerage');
INSERT INTO account_type (account_type_id, full_account_type, short_account_type) VALUES (8, 'Credit Card', 'Credit Card');

INSERT INTO account_classification (account_classification_id, classification) VALUES (1, 'Asset');
INSERT INTO account_classification (account_classification_id, classification) VALUES (2, 'Liability');
INSERT INTO account_classification (account_classification_id, classification) VALUES (3, 'Capital');
INSERT INTO account_classification (account_classification_id, classification) VALUES (4, 'Withdrawal');
INSERT INTO account_classification (account_classification_id, classification) VALUES (5, 'Revenue');
INSERT INTO account_classification (account_classification_id, classification) VALUES (6, 'Expense');

INSERT INTO frequency_type(frequency_type_id, frequency_type, month_factor) VALUES (1, 'Weekly', 4);
INSERT INTO frequency_type(frequency_type_id, frequency_type, month_factor) VALUES (2, 'Monthly', 1);
INSERT INTO frequency_type(frequency_type_id, frequency_type, month_factor) VALUES (3, 'Bi-Weekly', 2);
INSERT INTO frequency_type(frequency_type_id, frequency_type, month_factor) VALUES (4, 'Hourly', 672);
INSERT INTO frequency_type(frequency_type_id, frequency_type, month_factor) VALUES (5, 'Annual', .08333333);

INSERT INTO goal_type (goal_type_id, goal_type) VALUES (1, 'save');
INSERT INTO goal_type (goal_type_id, goal_type) VALUES (2, 'pay');

INSERT INTO occurrence(occurrence_id, occurrence) VALUES (1, 'Specific Date');
INSERT INTO occurrence(occurrence_id, occurrence) VALUES (2, 'End of Month');
INSERT INTO occurrence(occurrence_id, occurrence) VALUES (3, 'First of Month');
INSERT INTO occurrence(occurrence_id, occurrence) VALUES (4, 'Monday');
INSERT INTO occurrence(occurrence_id, occurrence) VALUES (5, 'Tuesday');
INSERT INTO occurrence(occurrence_id, occurrence) VALUES (6, 'Wednesday');
INSERT INTO occurrence(occurrence_id, occurrence) VALUES (7, 'Thursday');
INSERT INTO occurrence(occurrence_id, occurrence) VALUES (8, 'Friday');
INSERT INTO occurrence(occurrence_id, occurrence) VALUES (9, 'Saturday');
INSERT INTO occurrence(occurrence_id, occurrence) VALUES (10, 'Sunday');

INSERT INTO budget_category_type (budget_category_type_id, budget_category_type) VALUES (1, 'fixed');
INSERT INTO budget_category_type (budget_category_type_id, budget_category_type) VALUES (2, 'flexible');
INSERT INTO budget_category_type (budget_category_type_id, budget_category_type) VALUES (3, 'discretionary');
-- INSERT INTO budget_category (budget_category_id, category_name) VALUES (1, 'Income');
-- INSERT INTO budget_category (budget_category_id, category_name) VALUES (2, 'Discressionary');
-- INSERT INTO budget_category (budget_category_id, category_name) VALUES (3, 'Non-Discressionary');
-- INSERT INTO budget_category (budget_category_id, category_name) VALUES (4, 'Bills');
-- INSERT INTO budget_category (budget_category_id, category_name) VALUES (5, 'Savings');
-- INSERT INTO budget_category (budget_category_id, category_name) VALUES (6, 'Investment');
-- INSERT INTO budget_category (budget_category_id, category_name) VALUES (7, 'Other');

INSERT INTO budget_category_name (budget_category_name_id, category_name) VALUES (1, 'income');
INSERT INTO budget_category_name (budget_category_name_id, category_name) VALUES (2, 'bill');
INSERT INTO budget_category_name (budget_category_name_id, category_name) VALUES (3, 'spending');
INSERT INTO budget_category_name (budget_category_name_id, category_name) VALUES (4, 'subscription');
INSERT INTO budget_category_name (budget_category_name_id, category_name) VALUES (5, 'savings');
INSERT INTO budget_category_name (budget_category_name_id, category_name) VALUES (6, 'investment');

INSERT INTO budget_category (budget_category_id, budget_category_type_id, budget_category_name_id) VALUES (1, 1, 1); -- fixed income
INSERT INTO budget_category (budget_category_id, budget_category_type_id, budget_category_name_id) VALUES (2, 1, 2); -- fixed bill
INSERT INTO budget_category (budget_category_id, budget_category_type_id, budget_category_name_id) VALUES (3, 1, 3); -- fixed spending
INSERT INTO budget_category (budget_category_id, budget_category_type_id, budget_category_name_id) VALUES (4, 2, 1); -- flexible income
INSERT INTO budget_category (budget_category_id, budget_category_type_id, budget_category_name_id) VALUES (5, 2, 2); -- flexible bill
INSERT INTO budget_category (budget_category_id, budget_category_type_id, budget_category_name_id) VALUES (6, 2, 3); -- flexible spending
INSERT INTO budget_category (budget_category_id, budget_category_type_id, budget_category_name_id) VALUES (7, 3, 4); -- discretionary subscription
INSERT INTO budget_category (budget_category_id, budget_category_type_id, budget_category_name_id) VALUES (8, 3, 5); -- discretionary savings
INSERT INTO budget_category (budget_category_id, budget_category_type_id, budget_category_name_id) VALUES (9, 3, 6); -- discretionary investment
INSERT INTO budget_category (budget_category_id, budget_category_type_id, budget_category_name_id) VALUES (10, 3, 3); -- discretionary spending

-- TODO change to transaction sub category
INSERT INTO budget_sub_category (budget_sub_category_id, sub_category_name) VALUES (1, 'Income');
INSERT INTO budget_sub_category (budget_sub_category_id, sub_category_name) VALUES (2, 'Housing');
INSERT INTO budget_sub_category (budget_sub_category_id, sub_category_name) VALUES (3, 'Utilities');
INSERT INTO budget_sub_category (budget_sub_category_id, sub_category_name) VALUES (4, 'Credit Card');
INSERT INTO budget_sub_category (budget_sub_category_id, sub_category_name) VALUES (5, 'Health');
INSERT INTO budget_sub_category (budget_sub_category_id, sub_category_name) VALUES (6, 'Banking');
INSERT INTO budget_sub_category (budget_sub_category_id, sub_category_name) VALUES (7, 'Food/Beverage');
INSERT INTO budget_sub_category (budget_sub_category_id, sub_category_name) VALUES (8, 'Gas/Automotive');
INSERT INTO budget_sub_category (budget_sub_category_id, sub_category_name) VALUES (9, 'Household/Supplies');
INSERT INTO budget_sub_category (budget_sub_category_id, sub_category_name) VALUES (10, 'Entertainment');
INSERT INTO budget_sub_category (budget_sub_category_id, sub_category_name) VALUES (11, 'Education');
INSERT INTO budget_sub_category (budget_sub_category_id, sub_category_name) VALUES (12, 'Gifts and Contributions');
INSERT INTO budget_sub_category (budget_sub_category_id, sub_category_name) VALUES (13, 'Personal');
INSERT INTO budget_sub_category (budget_sub_category_id, sub_category_name) VALUES (14, 'Business');
INSERT INTO budget_sub_category (budget_sub_category_id, sub_category_name) VALUES (15, 'Taxes');
INSERT INTO budget_sub_category (budget_sub_category_id, sub_category_name) VALUES (16, 'Miscellaneous');
INSERT INTO budget_sub_category (budget_sub_category_id, sub_category_name) VALUES (17, 'Savings');
INSERT INTO budget_sub_category (budget_sub_category_id, sub_category_name) VALUES (18, 'Generic');

-- TODO this is just a list of generics and is being turned into a budget, not for user storage
INSERT INTO budget_item (budget_item_id, budget_category_id, name) VALUES (1, 2, 'Rent / Mortgage');
INSERT INTO budget_item (budget_item_id, budget_category_id, name) VALUES (2, 2, 'Cable / Internet');
INSERT INTO budget_item (budget_item_id, budget_category_id, name) VALUES (3, 2, 'Cable');
INSERT INTO budget_item (budget_item_id, budget_category_id, name) VALUES (4, 2, 'Internet');
INSERT INTO budget_item (budget_item_id, budget_category_id, name) VALUES (5, 2, 'Satellite');
INSERT INTO budget_item (budget_item_id, budget_category_id, name) VALUES (6, 2, 'Telephone');
INSERT INTO budget_item (budget_item_id, budget_category_id, name) VALUES (7, 2, 'Cell Phone');
INSERT INTO budget_item (budget_item_id, budget_category_id, name) VALUES (8, 2, 'Health Insurance');
INSERT INTO budget_item (budget_item_id, budget_category_id, name) VALUES (9, 5, 'Electricity');
INSERT INTO budget_item (budget_item_id, budget_category_id, name) VALUES (10, 5, 'Oil');
INSERT INTO budget_item (budget_item_id, budget_category_id, name) VALUES (11, 5, 'Telephone');
INSERT INTO budget_item (budget_item_id, budget_category_id, name) VALUES (12, 5, 'Cell Phone');
INSERT INTO budget_item (budget_item_id, budget_category_id, name) VALUES (13, 6, 'Groceries');
INSERT INTO budget_item (budget_item_id, budget_category_id, name) VALUES (14, 6, 'Gas');
INSERT INTO budget_item (budget_item_id, budget_category_id, name) VALUES (15, 6, 'Household Items');
INSERT INTO budget_item (budget_item_id, budget_category_id, name) VALUES (16, 7, 'Netflix');
INSERT INTO budget_item (budget_item_id, budget_category_id, name) VALUES (17, 7, 'Hulu');
INSERT INTO budget_item (budget_item_id, budget_category_id, name) VALUES (18, 7, 'Amazon Prime');
INSERT INTO budget_item (budget_item_id, budget_category_id, name) VALUES (19, 8, 'Rainy Day Fund');
INSERT INTO budget_item (budget_item_id, budget_category_id, name) VALUES (20, 8, 'Home Downpayment');
INSERT INTO budget_item (budget_item_id, budget_category_id, name) VALUES (21, 8, 'Vacation');
INSERT INTO budget_item (budget_item_id, budget_category_id, name) VALUES (22, 9, 'Retirement / 401k');
INSERT INTO budget_item (budget_item_id, budget_category_id, name) VALUES (23, 9, 'Stocks');
INSERT INTO budget_item (budget_item_id, budget_category_id, name) VALUES (24, 9, 'Mutual Funds');
INSERT INTO budget_item (budget_item_id, budget_category_id, name) VALUES (25, 9, 'Index Funds');
INSERT INTO budget_item (budget_item_id, budget_category_id, name) VALUES (26, 9, 'Exchange-traded funds');
INSERT INTO budget_item (budget_item_id, budget_category_id, name) VALUES (27, 9, 'Options');
INSERT INTO budget_item (budget_item_id, budget_category_id, name) VALUES (28, 1, 'General');
INSERT INTO budget_item (budget_item_id, budget_category_id, name) VALUES (29, 2, 'General');
INSERT INTO budget_item (budget_item_id, budget_category_id, name) VALUES (30, 3, 'General');
INSERT INTO budget_item (budget_item_id, budget_category_id, name) VALUES (31, 4, 'General');
INSERT INTO budget_item (budget_item_id, budget_category_id, name) VALUES (32, 2, 'General');
INSERT INTO budget_item (budget_item_id, budget_category_id, name) VALUES (33, 6, 'General');
INSERT INTO budget_item (budget_item_id, budget_category_id, name) VALUES (34, 7, 'General');
INSERT INTO budget_item (budget_item_id, budget_category_id, name) VALUES (35, 8, 'General');
INSERT INTO budget_item (budget_item_id, budget_category_id, name) VALUES (36, 9, 'General');
INSERT INTO budget_item (budget_item_id, budget_category_id, name) VALUES (37, 10, 'General');

INSERT INTO transaction_category (transaction_category_id, category_name, budget_sub_category_id) VALUES (1, 'Paycheck', 1);
INSERT INTO transaction_category (transaction_category_id, category_name, budget_sub_category_id) VALUES (2, 'Other Income', 1);
INSERT INTO transaction_category (transaction_category_id, category_name, budget_sub_category_id) VALUES (3, 'Rent/Mortgage', 2);
INSERT INTO transaction_category (transaction_category_id, category_name, budget_sub_category_id) VALUES (4, 'Cable/Internet', 3);
INSERT INTO transaction_category (transaction_category_id, category_name, budget_sub_category_id) VALUES (5, 'Cable', 3);
INSERT INTO transaction_category (transaction_category_id, category_name, budget_sub_category_id) VALUES (6, 'Satellite', 3);
INSERT INTO transaction_category (transaction_category_id, category_name, budget_sub_category_id) VALUES (7, 'Internet', 3);
INSERT INTO transaction_category (transaction_category_id, category_name, budget_sub_category_id) VALUES (8, 'Electricity', 3);
INSERT INTO transaction_category (transaction_category_id, category_name, budget_sub_category_id) VALUES (9, 'Oil', 3);
INSERT INTO transaction_category (transaction_category_id, category_name, budget_sub_category_id) VALUES (10, 'Water', 3);
INSERT INTO transaction_category (transaction_category_id, category_name, budget_sub_category_id) VALUES (11, 'Telephone', 3);
INSERT INTO transaction_category (transaction_category_id, category_name, budget_sub_category_id) VALUES (12, 'Garbage Pickup', 3);
INSERT INTO transaction_category (transaction_category_id, category_name, budget_sub_category_id) VALUES (13, 'Cell Phone', 3);
INSERT INTO transaction_category (transaction_category_id, category_name, budget_sub_category_id) VALUES (14, 'Payment/Credit', 4);
INSERT INTO transaction_category (transaction_category_id, category_name, budget_sub_category_id) VALUES (15, 'Interest', 4);
INSERT INTO transaction_category (transaction_category_id, category_name, budget_sub_category_id) VALUES (16, 'Annual Fee', 4);
INSERT INTO transaction_category (transaction_category_id, category_name, budget_sub_category_id) VALUES (17, 'Finance Charge', 4);
INSERT INTO transaction_category (transaction_category_id, category_name, budget_sub_category_id) VALUES (18, 'Over the Limit Fee', 4);
INSERT INTO transaction_category (transaction_category_id, category_name, budget_sub_category_id) VALUES (19, 'Minimum Usage Fee', 4);
INSERT INTO transaction_category (transaction_category_id, category_name, budget_sub_category_id) VALUES (20, 'Cash Advance Fee', 4);
INSERT INTO transaction_category (transaction_category_id, category_name, budget_sub_category_id) VALUES (21, 'Late Fee', 4);
INSERT INTO transaction_category (transaction_category_id, category_name, budget_sub_category_id) VALUES (22, 'Rewards Program', 4);
INSERT INTO transaction_category (transaction_category_id, category_name, budget_sub_category_id) VALUES (23, 'Health Insurance', 5);
INSERT INTO transaction_category (transaction_category_id, category_name, budget_sub_category_id) VALUES (24, 'Dental Insurance', 5);
INSERT INTO transaction_category (transaction_category_id, category_name, budget_sub_category_id) VALUES (25, 'Medical and Hospital Costs', 5);
INSERT INTO transaction_category (transaction_category_id, category_name, budget_sub_category_id) VALUES (26, 'Doctor/Dentist', 5);
INSERT INTO transaction_category (transaction_category_id, category_name, budget_sub_category_id) VALUES (27, 'Medicine', 5);
INSERT INTO transaction_category (transaction_category_id, category_name, budget_sub_category_id) VALUES (28, 'Glasses', 5);
INSERT INTO transaction_category (transaction_category_id, category_name, budget_sub_category_id) VALUES (29, 'Hearing Aids', 5);
INSERT INTO transaction_category (transaction_category_id, category_name, budget_sub_category_id) VALUES (30, 'First Aid Supplies', 5);
INSERT INTO transaction_category (transaction_category_id, category_name, budget_sub_category_id) VALUES (31, 'Treatment and Therapy', 5);
INSERT INTO transaction_category (transaction_category_id, category_name, budget_sub_category_id) VALUES (32, 'Fitness Club Membership', 5);
INSERT INTO transaction_category (transaction_category_id, category_name, budget_sub_category_id) VALUES (33, 'Investments', 17);
INSERT INTO transaction_category (transaction_category_id, category_name, budget_sub_category_id) VALUES (34, 'Emergency Fund', 17);
INSERT INTO transaction_category (transaction_category_id, category_name, budget_sub_category_id) VALUES (35, 'Retirement', 17);
INSERT INTO transaction_category (transaction_category_id, category_name, budget_sub_category_id) VALUES (36, 'Interest', 6);
INSERT INTO transaction_category (transaction_category_id, category_name, budget_sub_category_id) VALUES (37, 'Banking Fees', 6);
INSERT INTO transaction_category (transaction_category_id, category_name, budget_sub_category_id) VALUES (38, 'Groceries', 7);
INSERT INTO transaction_category (transaction_category_id, category_name, budget_sub_category_id) VALUES (39, 'Dining', 7);
INSERT INTO transaction_category (transaction_category_id, category_name, budget_sub_category_id) VALUES (40, 'Liquor/Beer', 7);
INSERT INTO transaction_category (transaction_category_id, category_name, budget_sub_category_id) VALUES (41, 'Snacks, Coffee Breaks', 7);
INSERT INTO transaction_category (transaction_category_id, category_name, budget_sub_category_id) VALUES (42, 'School Lunch', 7);
INSERT INTO transaction_category (transaction_category_id, category_name, budget_sub_category_id) VALUES (43, 'Work Lunch', 7);
INSERT INTO transaction_category (transaction_category_id, category_name, budget_sub_category_id) VALUES (44, 'Home Food Production', 7);
INSERT INTO transaction_category (transaction_category_id, category_name, budget_sub_category_id) VALUES (45, 'Gas', 8);
INSERT INTO transaction_category (transaction_category_id, category_name, budget_sub_category_id) VALUES (46, 'Insurance', 8);
INSERT INTO transaction_category (transaction_category_id, category_name, budget_sub_category_id) VALUES (47, 'Maintenance', 8);
INSERT INTO transaction_category (transaction_category_id, category_name, budget_sub_category_id) VALUES (48, 'Supplies/Tools', 8);
INSERT INTO transaction_category (transaction_category_id, category_name, budget_sub_category_id) VALUES (49, 'Licensing Fees', 8);
INSERT INTO transaction_category (transaction_category_id, category_name, budget_sub_category_id) VALUES (50, 'Taxi', 8);
INSERT INTO transaction_category (transaction_category_id, category_name, budget_sub_category_id) VALUES (51, 'Bus/Subway/Train', 8);
INSERT INTO transaction_category (transaction_category_id, category_name, budget_sub_category_id) VALUES (52, 'Parking', 8);
INSERT INTO transaction_category (transaction_category_id, category_name, budget_sub_category_id) VALUES (53, 'Tolls', 8);
INSERT INTO transaction_category (transaction_category_id, category_name, budget_sub_category_id) VALUES (54, 'Laundry Supplies', 9);
INSERT INTO transaction_category (transaction_category_id, category_name, budget_sub_category_id) VALUES (55, 'Cleaning Supplies', 9);
INSERT INTO transaction_category (transaction_category_id, category_name, budget_sub_category_id) VALUES (56, 'Furniture', 9);
INSERT INTO transaction_category (transaction_category_id, category_name, budget_sub_category_id) VALUES (57, 'Appliances', 9);
INSERT INTO transaction_category (transaction_category_id, category_name, budget_sub_category_id) VALUES (58, 'Dishes and Cutlery', 9);
INSERT INTO transaction_category (transaction_category_id, category_name, budget_sub_category_id) VALUES (59, 'Cooking Supplies', 9);
INSERT INTO transaction_category (transaction_category_id, category_name, budget_sub_category_id) VALUES (60, 'Linens', 9);
INSERT INTO transaction_category (transaction_category_id, category_name, budget_sub_category_id) VALUES (61, 'Soap and Shampoo', 9);
INSERT INTO transaction_category (transaction_category_id, category_name, budget_sub_category_id) VALUES (62, 'Facial Tissue', 9);
INSERT INTO transaction_category (transaction_category_id, category_name, budget_sub_category_id) VALUES (63, 'Yard Maintenance', 9);
INSERT INTO transaction_category (transaction_category_id, category_name, budget_sub_category_id) VALUES (64, 'House Repairs', 9);
INSERT INTO transaction_category (transaction_category_id, category_name, budget_sub_category_id) VALUES (65, 'Home Project Supplies', 9);
INSERT INTO transaction_category (transaction_category_id, category_name, budget_sub_category_id) VALUES (66, 'Safety Deposit Box Rental', 9);
INSERT INTO transaction_category (transaction_category_id, category_name, budget_sub_category_id) VALUES (67, 'Yard Improvement and Supplies', 9);
INSERT INTO transaction_category (transaction_category_id, category_name, budget_sub_category_id) VALUES (68, 'Books', 10);
INSERT INTO transaction_category (transaction_category_id, category_name, budget_sub_category_id) VALUES (69, 'Magazines', 10);
INSERT INTO transaction_category (transaction_category_id, category_name, budget_sub_category_id) VALUES (70, 'Movie Theater', 10);
INSERT INTO transaction_category (transaction_category_id, category_name, budget_sub_category_id) VALUES (71, 'Video Streaming/Rental/Pay Per View', 10);
INSERT INTO transaction_category (transaction_category_id, category_name, budget_sub_category_id) VALUES (72, 'Sports/Games', 10);
INSERT INTO transaction_category (transaction_category_id, category_name, budget_sub_category_id) VALUES (73, 'Sporting Events', 10);
INSERT INTO transaction_category (transaction_category_id, category_name, budget_sub_category_id) VALUES (74, 'Sporting Goods', 10);
INSERT INTO transaction_category (transaction_category_id, category_name, budget_sub_category_id) VALUES (75, 'Concerts', 10);
INSERT INTO transaction_category (transaction_category_id, category_name, budget_sub_category_id) VALUES (76, 'Music', 10);
INSERT INTO transaction_category (transaction_category_id, category_name, budget_sub_category_id) VALUES (77, 'Cultural Events', 10);
INSERT INTO transaction_category (transaction_category_id, category_name, budget_sub_category_id) VALUES (78, 'Video Games', 10);
INSERT INTO transaction_category (transaction_category_id, category_name, budget_sub_category_id) VALUES (79, 'Toys', 10);
INSERT INTO transaction_category (transaction_category_id, category_name, budget_sub_category_id) VALUES (80, 'Tourist Attractions', 10);
INSERT INTO transaction_category (transaction_category_id, category_name, budget_sub_category_id) VALUES (81, 'Electronics', 10);
INSERT INTO transaction_category (transaction_category_id, category_name, budget_sub_category_id) VALUES (82, 'Tuition', 11);
INSERT INTO transaction_category (transaction_category_id, category_name, budget_sub_category_id) VALUES (83, 'Books', 11);
INSERT INTO transaction_category (transaction_category_id, category_name, budget_sub_category_id) VALUES (84, 'Stationary', 11);
INSERT INTO transaction_category (transaction_category_id, category_name, budget_sub_category_id) VALUES (85, 'Courses/Lessons', 11);
INSERT INTO transaction_category (transaction_category_id, category_name, budget_sub_category_id) VALUES (86, 'Gifts', 12);
INSERT INTO transaction_category (transaction_category_id, category_name, budget_sub_category_id) VALUES (87, 'Cards and Wrapping Paper', 12);
INSERT INTO transaction_category (transaction_category_id, category_name, budget_sub_category_id) VALUES (88, 'Flowers', 12);
INSERT INTO transaction_category (transaction_category_id, category_name, budget_sub_category_id) VALUES (89, 'Charitable Donations', 12);
INSERT INTO transaction_category (transaction_category_id, category_name, budget_sub_category_id) VALUES (90, 'Haircuts', 13);
INSERT INTO transaction_category (transaction_category_id, category_name, budget_sub_category_id) VALUES (91, 'Beauty Shop', 13);
INSERT INTO transaction_category (transaction_category_id, category_name, budget_sub_category_id) VALUES (92, 'Cosmetics', 13);
INSERT INTO transaction_category (transaction_category_id, category_name, budget_sub_category_id) VALUES (93, 'Toiletries', 13);
INSERT INTO transaction_category (transaction_category_id, category_name, budget_sub_category_id) VALUES (94, 'Shaving Supplies', 13);
INSERT INTO transaction_category (transaction_category_id, category_name, budget_sub_category_id) VALUES (95, 'Clothing', 13);
INSERT INTO transaction_category (transaction_category_id, category_name, budget_sub_category_id) VALUES (96, 'Alterations', 13);
INSERT INTO transaction_category (transaction_category_id, category_name, budget_sub_category_id) VALUES (97, 'Cleaning', 13);
INSERT INTO transaction_category (transaction_category_id, category_name, budget_sub_category_id) VALUES (98, 'Miscellaneous', 13);
INSERT INTO transaction_category (transaction_category_id, category_name, budget_sub_category_id) VALUES (99, 'Attire', 14);
INSERT INTO transaction_category (transaction_category_id, category_name, budget_sub_category_id) VALUES (100, 'Office Supplies', 14);
INSERT INTO transaction_category (transaction_category_id, category_name, budget_sub_category_id) VALUES (101, 'Software', 14);
INSERT INTO transaction_category (transaction_category_id, category_name, budget_sub_category_id) VALUES (102, 'State Income Tax', 15);
INSERT INTO transaction_category (transaction_category_id, category_name, budget_sub_category_id) VALUES (103, 'Federal Income Tax', 15);
INSERT INTO transaction_category (transaction_category_id, category_name, budget_sub_category_id) VALUES (104, 'Social Security Tax', 15);
INSERT INTO transaction_category (transaction_category_id, category_name, budget_sub_category_id) VALUES (105, 'Medicare Tax', 15);
INSERT INTO transaction_category (transaction_category_id, category_name, budget_sub_category_id) VALUES (106, 'Investment Tax', 15);
INSERT INTO transaction_category (transaction_category_id, category_name, budget_sub_category_id) VALUES (107, 'Tax Fees', 15);
INSERT INTO transaction_category (transaction_category_id, category_name, budget_sub_category_id) VALUES (108, 'Software', 15);
INSERT INTO transaction_category (transaction_category_id, category_name, budget_sub_category_id) VALUES (109, 'Vacation', 16);
INSERT INTO transaction_category (transaction_category_id, category_name, budget_sub_category_id) VALUES (110, 'Unknown', 16);
INSERT INTO transaction_category (transaction_category_id, category_name, budget_sub_category_id) VALUES (111, 'Other', 16);
INSERT INTO transaction_category (transaction_category_id, category_name, budget_sub_category_id) VALUES (112, 'Transfer', 6);
