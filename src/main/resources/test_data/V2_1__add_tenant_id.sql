update budget SET tenant_id = '684996db-6cf8-4976-8336-6e664386dcda';

INSERT INTO budget (budget_category_id, budget_item_name, start_date, end_date,
                    frequency_type_id, amount, in_use, transaction_type_id, tenant_id)
VALUES (2, 'Rent / Mortgage', '2017-01-01', NULL, 2, 800, 1, 2, '7c21bdec-219e-4492-a2fd-e6a2ec5fb8fa');