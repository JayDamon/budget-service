CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE TABLE IF NOT EXISTS budget
(
    budget_id           uuid DEFAULT uuid_generate_v4() not null,
    amount              DECIMAL(19, 2),
    end_date            TIMESTAMP,
    in_use              BOOLEAN DEFAULT true,
    budget_item_name    VARCHAR(255),
    start_date          TIMESTAMP,
    budget_category_id  uuid,
    frequency_type_id   uuid,
    tenant_id           varchar(255),
    PRIMARY KEY (budget_id)
);

CREATE TABLE IF NOT EXISTS budget_category
(
    budget_category_id      uuid DEFAULT uuid_generate_v4() not null,
    budget_category_name_id uuid,
    budget_category_type_id uuid,
    budget_type             VARCHAR(9),
    PRIMARY KEY (budget_category_id)
);

CREATE TABLE IF NOT EXISTS budget_category_name
(
    budget_category_name_id uuid DEFAULT uuid_generate_v4() not null,
    category_name           VARCHAR(255),
    display_order           INTEGER,
    PRIMARY KEY (budget_category_name_id)
);

CREATE TABLE IF NOT EXISTS budget_category_type
(
    budget_category_type_id uuid DEFAULT uuid_generate_v4() not null,
    budget_category_type    VARCHAR(255),
    display_order           INTEGER,
    PRIMARY KEY (budget_category_type_id)
);

CREATE TABLE IF NOT EXISTS budget_item
(
    budget_item_id     uuid DEFAULT uuid_generate_v4() not null,
    name               VARCHAR(255),
    budget_category_id uuid,
    PRIMARY KEY (budget_item_id)
);

CREATE TABLE IF NOT EXISTS frequency_type
(
    frequency_type_id uuid DEFAULT uuid_generate_v4() not null,
    frequency_type    VARCHAR(255),
    month_factor      DECIMAL,
    PRIMARY KEY (frequency_type_id)
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

INSERT INTO frequency_type(frequency_type_id, frequency_type, month_factor) 
VALUES
       ('daf7d54a-1cea-4e1d-818a-bfde558870d7', 'Weekly',4),
       ('f5b475fe-3de6-4df1-8eaa-dba249450b58', 'Monthly',1),
       ('ea42a0de-5e11-4bbf-a8b6-ef13bb57a85d', 'Bi-Weekly',2),
       ('18f58022-23b7-4692-9082-a5522d964fe2', 'Hourly',672),
       ('a5b221fb-a725-43b2-9d29-6b933b5527e7', 'Annual',0.08333333);

INSERT INTO budget_category_type(budget_category_type_id,budget_category_type,display_order) 
VALUES
       ('0121056f-a732-4d9d-a554-06223969f0cc','fixed',1),
       ('b3c16c07-8c73-427b-94d3-b6aa2ec56c65','flexible',2),
       ('06aa4213-b898-4dae-8c5b-da5b07a47363','discretionary',3);

INSERT INTO budget_category_name(budget_category_name_id,category_name,display_order) 
VALUES
       ('3349cf3d-7adc-4f6d-9b3a-200abd574b31','income',1),
       ('e0ba8f7a-93cb-485d-aabf-4742fcf7f5ce','bill',2),
       ('3369b402-312e-4d64-8776-30824b3d1e21','spending',3),
       ('2dbae80e-2afd-480b-9158-cebcfcfa2cd8','subscription',4),
       ('9df2b26b-1a3e-4455-9490-d73cc4754c53','savings',5),
       ('97bd9e47-3e8e-4c70-842c-2b79f3f67f60','investment',6);

INSERT INTO budget_category(budget_category_id, budget_category_type_id, budget_category_name_id,budget_type)
VALUES
       ('5c07c147-1aab-472b-9c8f-e500d3161210','0121056f-a732-4d9d-a554-06223969f0cc','3349cf3d-7adc-4f6d-9b3a-200abd574b31','INCOME'),
       ('c8bdc3a5-4968-40ae-a4b3-5bd3dcb34fa2','0121056f-a732-4d9d-a554-06223969f0cc','e0ba8f7a-93cb-485d-aabf-4742fcf7f5ce','EXPENSE'),
       ('62d551d9-4cee-48fd-ac9f-bd9a3ea1f118','0121056f-a732-4d9d-a554-06223969f0cc','3369b402-312e-4d64-8776-30824b3d1e21','EXPENSE'),
       ('4c7706d6-5932-4ec4-b5e2-67d47ab794e6','b3c16c07-8c73-427b-94d3-b6aa2ec56c65','3349cf3d-7adc-4f6d-9b3a-200abd574b31','INCOME'),
       ('52a0ce47-56f6-4d21-bc69-84ee5acdd689','b3c16c07-8c73-427b-94d3-b6aa2ec56c65','e0ba8f7a-93cb-485d-aabf-4742fcf7f5ce','EXPENSE'),
       ('2bcb4c13-12db-41b0-92ad-f9a332db3bf9','b3c16c07-8c73-427b-94d3-b6aa2ec56c65','3369b402-312e-4d64-8776-30824b3d1e21','EXPENSE'),
       ('4ecbc3d7-3b4a-471e-9462-a5941975a96b','06aa4213-b898-4dae-8c5b-da5b07a47363','2dbae80e-2afd-480b-9158-cebcfcfa2cd8','EXPENSE'),
       ('4a9b8902-2b33-4409-851d-2b9b5dc28a35','06aa4213-b898-4dae-8c5b-da5b07a47363','9df2b26b-1a3e-4455-9490-d73cc4754c53','EXPENSE'),
       ('ca9bdefd-ab9f-44f1-ad08-83b8fcddc7c9','06aa4213-b898-4dae-8c5b-da5b07a47363','97bd9e47-3e8e-4c70-842c-2b79f3f67f60','EXPENSE'),
       ('39b2da5a-f3f7-409f-bb8a-805087e94a33','06aa4213-b898-4dae-8c5b-da5b07a47363','3369b402-312e-4d64-8776-30824b3d1e21','EXPENSE');

INSERT INTO budget_item(budget_item_id,budget_category_id,name) 
VALUES
       ('def29a93-31af-4198-9512-458579c23e6f','c8bdc3a5-4968-40ae-a4b3-5bd3dcb34fa2', 'Rent / Mortgage'),
       ('0085eec7-4576-4cd0-bf72-7cf4d7ce358f','c8bdc3a5-4968-40ae-a4b3-5bd3dcb34fa2', 'Cable / Internet'),
       ('5988357a-201d-4f16-a0f9-9fe7dc968b28','c8bdc3a5-4968-40ae-a4b3-5bd3dcb34fa2', 'Cable'),
       ('6c8daa21-d9d1-4398-863f-7d2ea408492d','c8bdc3a5-4968-40ae-a4b3-5bd3dcb34fa2', 'Internet'),
       ('ace30179-a553-4a45-bce3-40ccb0e582e7','c8bdc3a5-4968-40ae-a4b3-5bd3dcb34fa2', 'Satellite'),
       ('5b575961-9007-43c7-8389-a5a0a633627e','c8bdc3a5-4968-40ae-a4b3-5bd3dcb34fa2', 'Telephone'),
       ('09178354-e5c0-469a-a1f8-a7821d51bfdc','c8bdc3a5-4968-40ae-a4b3-5bd3dcb34fa2', 'Cell Phone'),
       ('ae00eaab-9cbb-40d8-9143-373f0ae19929','c8bdc3a5-4968-40ae-a4b3-5bd3dcb34fa2', 'Health Insurance'),
       ('d2ed09b1-8d8b-4803-baf4-b73133008975','52a0ce47-56f6-4d21-bc69-84ee5acdd689', 'Electricity'),
       ('d69c68c2-8944-4083-97c0-ecace283975e','52a0ce47-56f6-4d21-bc69-84ee5acdd689', 'Oil'),
       ('8bd3f5a4-92c6-4758-aa26-674421bd0fcd','52a0ce47-56f6-4d21-bc69-84ee5acdd689', 'Telephone'),
       ('6a83d4ac-6e21-4152-8b4f-0f087c1de4d1','52a0ce47-56f6-4d21-bc69-84ee5acdd689', 'Cell Phone'),
       ('8b124bf6-57db-448c-acf6-248879d49d4b','2bcb4c13-12db-41b0-92ad-f9a332db3bf9', 'Groceries'),
       ('69b38936-8a14-4b16-bec1-3adfc657d774','2bcb4c13-12db-41b0-92ad-f9a332db3bf9', 'Gas'),
       ('ed9c505d-46ee-4858-bc4b-8abefd2ba15d','2bcb4c13-12db-41b0-92ad-f9a332db3bf9', 'Household Items'),
       ('b5c7f940-7666-47ed-bd68-65d3f71e4961','4ecbc3d7-3b4a-471e-9462-a5941975a96b', 'Netflix'),
       ('07f2b0cb-01a7-410e-be27-411edd3302c6','4ecbc3d7-3b4a-471e-9462-a5941975a96b', 'Hulu'),
       ('77d081ce-8d20-4db8-9838-ca4c158504e3','4ecbc3d7-3b4a-471e-9462-a5941975a96b', 'Amazon Prime'),
       ('0e07e06e-cd05-419b-a74a-ec64046b16f1','4a9b8902-2b33-4409-851d-2b9b5dc28a35', 'Rainy Day Fund'),
       ('17a93f40-055d-42e5-acc0-c04f86f5bfc1','4a9b8902-2b33-4409-851d-2b9b5dc28a35', 'Home Downpayment'),
       ('4f70e84f-f9af-4225-9710-6a41b20fabfe','4a9b8902-2b33-4409-851d-2b9b5dc28a35', 'Vacation'),
       ('bc6fe3d4-cb39-41fe-8523-a2b06b3be542','ca9bdefd-ab9f-44f1-ad08-83b8fcddc7c9', 'Retirement / 401k'),
       ('47a9066a-dbca-4c64-8a02-e5f05454df8d','ca9bdefd-ab9f-44f1-ad08-83b8fcddc7c9', 'Stocks'),
       ('23cc2a41-8391-4c8d-bfee-f4e6d62de5ef','ca9bdefd-ab9f-44f1-ad08-83b8fcddc7c9', 'Mutual Funds'),
       ('3df6e1ef-6974-4a51-b1d2-d1b34cfe071e','ca9bdefd-ab9f-44f1-ad08-83b8fcddc7c9', 'Index Funds'),
       ('d82b3d56-46ae-4e2e-8f98-3b5fa45e6a8f','ca9bdefd-ab9f-44f1-ad08-83b8fcddc7c9', 'Exchange-traded funds'),
       ('88891989-2ae7-47ad-b350-785eb66f10ab','ca9bdefd-ab9f-44f1-ad08-83b8fcddc7c9', 'Options'),
       ('aac440ad-a1dd-458d-8b91-6b30cb38e06d','5c07c147-1aab-472b-9c8f-e500d3161210', 'General'),
       ('be8600a9-7fa4-42cb-961b-07197fde5f7b','c8bdc3a5-4968-40ae-a4b3-5bd3dcb34fa2', 'General'),
       ('a348c630-474c-442b-a678-63bd54b59ca6','62d551d9-4cee-48fd-ac9f-bd9a3ea1f118', 'General'),
       ('756e174e-7a3f-461d-b96e-16fc31047b1a','4c7706d6-5932-4ec4-b5e2-67d47ab794e6', 'General'),
       ('17d0d101-8bb3-495a-9a09-2c36ef9a6172','52a0ce47-56f6-4d21-bc69-84ee5acdd689', 'General'),
       ('c5fc49c2-e528-4928-b5d6-bb86145d3ac8','2bcb4c13-12db-41b0-92ad-f9a332db3bf9', 'General'),
       ('eb33c632-73ff-49db-80cc-30c38b748231','4ecbc3d7-3b4a-471e-9462-a5941975a96b', 'General'),
       ('615276c9-c226-414f-89e8-17fdd22a8baf','4a9b8902-2b33-4409-851d-2b9b5dc28a35', 'General'),
       ('aeb19834-7c81-4835-a1f7-78b89194025f','ca9bdefd-ab9f-44f1-ad08-83b8fcddc7c9', 'General'),
       ('02c2af4f-de4c-4c7d-ba5f-b06677d54063','39b2da5a-f3f7-409f-bb8a-805087e94a33', 'General');
