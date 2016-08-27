--
-- Triggers `test_action`
--

DROP TRIGGER IF EXISTS `test_action_insert_trigger`;
DELIMITER $$
CREATE TRIGGER `test_action_insert_trigger` AFTER INSERT ON `test_action`
FOR EACH ROW
    BEGIN
        INSERT INTO test_case_revision SET Test_Action_Id = NEW.Id, Type='Insert', Content = CONCAT('Insert action { name : ', NEW.Name, ', handler: ', NEW.Handler, ', remark: ', NEW.Remark, ' }');
    END
$$
DELIMITER ;

DROP TRIGGER IF EXISTS `test_action_update_trigger`;
DELIMITER $$
CREATE TRIGGER `test_action_update_trigger` AFTER UPDATE ON `test_action`
FOR EACH ROW
    BEGIN
        IF OLD.Name != New.Name Then
            INSERT INTO test_case_revision SET Test_Action_Id = NEW.Id, Type = 'Update', Content = CONCAT('Update name to: ', NEW.Name , ', from: ', OLD.Name);
        END IF ;

        IF OLD.Handler != NEW.Handler THEN
            INSERT INTO test_case_revision SET Test_Action_Id = NEW.Id, Type = 'Update', Content = CONCAT('Update handler to: ', NEW.Handler , ', from: ', OLD.Handler);
        END IF ;

        IF OLD.Remark != NEW.Remark THEN
            INSERT INTO test_case_revision SET Test_Action_Id = NEW.Id, Type = 'Update', Content = CONCAT('Update remark to: ', NEW.Remark , ', from: ', OLD.Remark);
        END IF ;
    END
$$
DELIMITER ;