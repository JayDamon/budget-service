ALTER TABLE budget_category_type ADD COLUMN display_order INTEGER;

UPDATE budget_category_type SET display_order = 1 WHERE budget_category_type_id = 1;
UPDATE budget_category_type SET display_order = 2 WHERE budget_category_type_id = 2;
UPDATE budget_category_type SET display_order = 3 WHERE budget_category_type_id = 3;

ALTER TABLE budget_category_name ADD COLUMN display_order INTEGER;

UPDATE budget_category_name SET display_order = 1 WHERE budget_category_name_id = 1;
UPDATE budget_category_name SET display_order = 2 WHERE budget_category_name_id = 2;
UPDATE budget_category_name SET display_order = 3 WHERE budget_category_name_id = 3;
UPDATE budget_category_name SET display_order = 4 WHERE budget_category_name_id = 4;
UPDATE budget_category_name SET display_order = 5 WHERE budget_category_name_id = 5;
UPDATE budget_category_name SET display_order = 6 WHERE budget_category_name_id = 6;
