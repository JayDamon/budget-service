ALTER TABLE goal DROP FOREIGN KEY FK_GoalType_Goal_GoalTypeId;
ALTER TABLE recurring_transaction DROP FOREIGN KEY FK_FrequencyType_RecurringTransaction_FrequencyTypeId;
ALTER TABLE recurring_transaction DROP FOREIGN KEY FK_Occurrence_RecurringTransaction_OccurrenceId;

DROP TABLE goal_type;
DROP TABLE goal;
DROP TABLE occurrence;
DROP TABLE recurring_transaction;
DROP TABLE transfer;

