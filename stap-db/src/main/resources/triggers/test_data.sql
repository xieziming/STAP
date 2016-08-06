--
-- Triggers `test_data`
--
DROP TRIGGER IF EXISTS `test_data_insert_trigger`;
DELIMITER $$
CREATE TRIGGER `test_data_insert_trigger` AFTER INSERT ON `test_data`
FOR EACH ROW
  INSERT INTO test_case_revision SET Test_Case_Id = NEW.Test_Case_Id, Type = 'Insert', Content = CONCAT('Insert data(', NEW.Id, ', ', (SELECT Field FROM test_data_definition WHERE Id = NEW.Test_Data_Definition_Id), ')');
$$
DELIMITER ;

DROP TRIGGER IF EXISTS `test_data_update_trigger`;
DELIMITER $$
CREATE TRIGGER `test_data_update_trigger` AFTER UPDATE ON `test_data`
FOR EACH ROW
  BEGIN
    IF OLD.Test_Data_Definition_Id != NEW.Test_Data_Definition_Id THEN
      INSERT INTO test_case_revision SET Test_Case_Id = NEW.Test_Case_Id, Type = 'Update', Content = CONCAT('Update data(', OLD.Test_Data_Definition_Id, ', ', (SELECT Field FROM test_data_definition WHERE Id = OLD.Test_Data_Definition_Id), ') to data(', NEW.Test_Data_Definition_Id, ', ', (SELECT Field FROM test_data_definition WHERE Id = NEW.Test_Data_Definition_Id), ')' );
    END IF ;
  END
$$
DELIMITER ;

DROP TRIGGER IF EXISTS `test_data_delete_trigger`;
DELIMITER $$
CREATE TRIGGER `test_data_delete_trigger` AFTER DELETE ON `test_data`
FOR EACH ROW
    INSERT INTO test_case_revision SET Test_Case_Id = OLD.Test_Case_Id, Type = 'Delete', Content = CONCAT('Delete data(', OLD.Id, ', ', (SELECT Field FROM test_data_definition WHERE Id = OLD.Test_Data_Definition_Id), ')')
$$
DELIMITER ;