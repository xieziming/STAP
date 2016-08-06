--
-- Triggers `test_data_definition`
--

DROP TRIGGER IF EXISTS `test_data_definition_insert_trigger`;
DELIMITER $$
CREATE TRIGGER `test_data_definition_insert_trigger` AFTER INSERT ON `test_data_definition`
FOR EACH ROW
    INSERT INTO test_case_revision SET Test_Data_Definition_Id = NEW.Id, Type='Insert', Content = CONCAT('Insert definition { type : ', NEW.Type , ', field: ', NEW.Field, ', value: ', NEW.Value, ', fileId: ', NEW.File_Id, ', remark: ', NEW.Remark, ' }')
$$
DELIMITER ;

DROP TRIGGER IF EXISTS `test_data_definition_update_trigger`;
DELIMITER $$
CREATE TRIGGER `test_data_definition_update_trigger` AFTER UPDATE ON `test_data_definition`
FOR EACH ROW
    BEGIN
        IF OLD.Field != NEW.Field THEN
            INSERT INTO test_case_revision SET Test_Data_Definition_Id = NEW.Id, Type='Update', Content = CONCAT('Update field to: ', NEW.Field , ', from: ', OLD.Field);
        END IF ;

        IF OLD.Value != New.Value Then
            INSERT INTO test_case_revision SET Test_Data_Definition_Id = NEW.Id, Type='Update', Content = CONCAT('Update value to: ', NEW.Value , ', from: ', OLD.Value);
        END IF ;

        IF OLD.File_Id != NEW.File_Id THEN
            INSERT INTO test_case_revision SET Test_Data_Definition_Id = NEW.Id, Type='Update', Content = CONCAT('Update fileId to: ', NEW.File_Id , ', from: ', OLD.File_Id);
        END IF ;

        IF OLD.Remark != NEW.Remark THEN
            INSERT INTO test_case_revision SET Test_Data_Definition_Id = NEW.Id, Type='Update', Content = CONCAT('Update remark to: ', NEW.Remark , ', from: ', OLD.Remark);
        END IF ;

        IF OLD.Type != NEW.Type THEN
            INSERT INTO test_case_revision SET Test_Data_Definition_Id = NEW.Id, Type='Update', Content = CONCAT('Update type to: ', NEW.Type , ', from: ', OLD.Type);
        END IF ;
    END
$$
DELIMITER ;

DROP TRIGGER IF EXISTS `test_data_definition_delete_trigger`;
DELIMITER $$
CREATE TRIGGER `test_data_definition_delete_trigger` AFTER DELETE ON `test_data_definition`
FOR EACH ROW
    INSERT INTO test_case_revision SET Test_Data_Definition_Id = OLD.Id, Type='Delete', Content = CONCAT('Delete definition of field : ', OLD.Field)
$$
DELIMITER ;