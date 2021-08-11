ALTER TABLE budget_category ADD COLUMN budget_type VARCHAR(9);

UPDATE budget_category bc
SET bc.budget_type = 'INCOME'
WHERE bc.budget_category_name_id IN (Select budget_category_name_id FROM budget_category_name WHERE category_name = 'income');

UPDATE budget_category bc
SET bc.budget_type = 'EXPENSE'
WHERE bc.budget_category_name_id IN (Select budget_category_name_id FROM budget_category_name WHERE category_name <> 'income');
