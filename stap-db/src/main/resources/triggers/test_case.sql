--
-- Triggers `test_case`
--

DROP TRIGGER IF EXISTS `test_case_insert_trigger`;
DELIMITER $$
CREATE TRIGGER `test_case_insert_trigger` AFTER INSERT ON `test_case`
FOR EACH ROW
    BEGIN
        INSERT INTO test_case_revision SET Test_Case_Id = NEW.Id, Type='Insert', Content = CONCAT('Insert test case { name : ', NEW.Name, ', description: ', NEW.Description, ', status: ', NEW.Status, ' }');
    END
$$
DELIMITER ;

DROP TRIGGER IF EXISTS `test_case_update_trigger`;
DELIMITER $$
CREATE TRIGGER `test_case_update_trigger` AFTER UPDATE ON `test_case`
FOR EACH ROW
    BEGIN
        IF OLD.Name != New.Name Then
            INSERT INTO test_case_revision SET Test_Case_Id = NEW.Id, Type = 'Update', Content = CONCAT('Update name to: ', NEW.Name , ', from: ', OLD.Name);
        END IF ;

        IF OLD.Description != NEW.Description THEN
            INSERT INTO test_case_revision SET Test_Case_Id = NEW.Id, Type = 'Update', Content = CONCAT('Update description to: ', NEW.Description , ', from: ', OLD.Description);
        END IF ;

        IF OLD.Status != NEW.Status THEN
            INSERT INTO test_case_revision SET Test_Case_Id = NEW.Id, Type = 'Update', Content = CONCAT('Update status to: ', NEW.Status , ', from: ', OLD.Status);
        END IF ;
    END
$$
DELIMITER ;

DROP TRIGGER IF EXISTS `test_case_delete_trigger`;
DELIMITER $$
CREATE TRIGGER `test_case_delete_trigger` AFTER DELETE ON `test_case`
FOR EACH ROW
    BEGIN
        INSERT INTO test_case_revision SET Test_Case_Id = OLD.Id, Type='Delete', Content = CONCAT('Delete test case of : ', OLD.Name);
    END
$$
DELIMITER ;