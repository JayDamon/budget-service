CREATE TABLE IF NOT EXISTS budget
(
    budget_id           BIGINT NOT NULL AUTO_INCREMENT,
    amount              DECIMAL(19, 2),
    end_date            TIMESTAMP,
    in_use              BOOLEAN DEFAULT true,
    budget_item_name    VARCHAR(255),
    start_date          TIMESTAMP,
    transaction_type_id INTEGER,
    budget_category_id  INTEGER,
    frequency_type_id   INTEGER,
    PRIMARY KEY (budget_id)
);

CREATE TABLE IF NOT EXISTS budget_category
(
    budget_category_id      INTEGER NOT NULL AUTO_INCREMENT,
    budget_category_name_id INTEGER,
    budget_category_type_id INTEGER,
    PRIMARY KEY (budget_category_id)
);

CREATE TABLE IF NOT EXISTS budget_category_name
(
    budget_category_name_id INTEGER NOT NULL AUTO_INCREMENT,
    category_name           VARCHAR(255),
    PRIMARY KEY (budget_category_name_id)
);

CREATE TABLE IF NOT EXISTS budget_category_type
(
    budget_category_type_id INTEGER NOT NULL AUTO_INCREMENT,
    budget_category_type    VARCHAR(255),
    PRIMARY KEY (budget_category_type_id)
);

CREATE TABLE IF NOT EXISTS budget_item
(
    budget_item_id     BIGINT NOT NULL AUTO_INCREMENT,
    name               VARCHAR(255),
    budget_category_id INTEGER,
    PRIMARY KEY (budget_item_id)
);

CREATE TABLE IF NOT EXISTS frequency_type
(
    frequency_type_id INTEGER NOT NULL AUTO_INCREMENT,
    frequency_type    VARCHAR(255),
    month_factor      DOUBLE,
    PRIMARY KEY (frequency_type_id)
);

CREATE TABLE IF NOT EXISTS goal
(
    goal_id      BIGINT NOT NULL AUTO_INCREMENT,
    account_id   INTEGER,
    amount       DECIMAL(19, 2),
    end_date     TIMESTAMP,
    name         VARCHAR(255),
    priority     INTEGER,
    start_date   TIMESTAMP,
    goal_type_id INTEGER,
    PRIMARY KEY (goal_id)
);

CREATE TABLE IF NOT EXISTS goal_type
(
    goal_type_id INTEGER NOT NULL AUTO_INCREMENT,
    goal_type    VARCHAR(255),
    PRIMARY KEY (goal_type_id)
);

CREATE TABLE IF NOT EXISTS occurrence
(
    occurrence_id INTEGER NOT NULL AUTO_INCREMENT,
    occurrence    VARCHAR(255),
    PRIMARY KEY (occurrence_id)
);

CREATE TABLE IF NOT EXISTS recurring_transaction
(
    recurring_transaction_id BIGINT NOT NULL AUTO_INCREMENT,
    account_id               BIGINT,
    amount                   DECIMAL(19, 2),
    budget_category_id       INTEGER,
    end_date                 TIMESTAMP,
    frequency                INTEGER,
    name                     VARCHAR(255),
    start_date               TIMESTAMP,
    transaction_category_id  INTEGER,
    transaction_type_id      INTEGER,
    frequency_type_id        INTEGER,
    occurrence_id            INTEGER,
    PRIMARY KEY (recurring_transaction_id)
);

CREATE TABLE IF NOT EXISTS transfer
(
    transfer_id         BIGINT NOT NULL AUTO_INCREMENT,
    amount              DECIMAL(19, 2),
    transfer_date       TIMESTAMP,
    from_account_id     BIGINT,
    from_transaction_id BIGINT,
    to_account_id       BIGINT,
    to_transaction_id   BIGINT,
    PRIMARY KEY (transfer_id)
);

ALTER TABLE
    budget
    ADD
        CONSTRAINT FK_BudgetCategory_Budget_BudgetCategoryId FOREIGN KEY (budget_category_id) REFERENCES budget_category(budget_category_id);

ALTER TABLE
    budget
    ADD
        CONSTRAINT FK_FrequencyType_Budget_FrequencyTypeId FOREIGN KEY (frequency_type_id) REFERENCES frequency_type(frequency_type_id);

ALTER TABLE
    budget_category
    ADD
        CONSTRAINT FK_BudgetCategoryName_BudgetCategory_BudgetCategoryNameId FOREIGN KEY (budget_category_name_id) REFERENCES budget_category_name(budget_category_name_id);

ALTER TABLE
    budget_category
    ADD
        CONSTRAINT FK_BudgetCategoryType_BudgetCategory_BudgetCategoryTypeId FOREIGN KEY (budget_category_type_id) REFERENCES budget_category_type(budget_category_type_id);

ALTER TABLE
    budget_item
    ADD
        CONSTRAINT FK_BudgetCategory_BudgetItem_BudgetCategoryId FOREIGN KEY (budget_category_id) REFERENCES budget_category(budget_category_id);

ALTER TABLE
    goal
    ADD
        CONSTRAINT FK_GoalType_Goal_GoalTypeId FOREIGN KEY (goal_type_id) REFERENCES goal_type(goal_type_id);

ALTER TABLE
    recurring_transaction
    ADD
        CONSTRAINT FK_FrequencyType_RecurringTransaction_FrequencyTypeId FOREIGN KEY (frequency_type_id) REFERENCES frequency_type(frequency_type_id);

ALTER TABLE
    recurring_transaction
    ADD
        CONSTRAINT FK_Occurrence_RecurringTransaction_OccurrenceId FOREIGN KEY (occurrence_id) REFERENCES occurrence(occurrence_id);


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
       (10, 3, 3); -- discretionary spending


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