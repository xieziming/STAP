--
-- Triggers `test_step`
--
DROP TRIGGER IF EXISTS `test_step_insert_trigger`;
DELIMITER $$
CREATE TRIGGER `test_step_insert_trigger` AFTER INSERT ON `test_step`
FOR EACH ROW
    INSERT INTO test_case_revision SET Test_Case_Id = NEW.Test_Case_Id, Type = 'Insert', Content = CONCAT('Insert step { order : ', NEW.Step_Order , ', action: ', NEW.Test_Action_Id, ' }')
$$
DELIMITER ;

DROP TRIGGER IF EXISTS `test_step_update_trigger`;
DELIMITER $$
CREATE TRIGGER `test_step_update_trigger` AFTER UPDATE ON `test_step`
FOR EACH ROW
    BEGIN
        IF OLD.Step_Order != NEW.Step_Order THEN
            INSERT INTO test_case_revision SET Test_Case_Id= OLD.Test_Case_Id, Type = 'Update', Content = CONCAT('Update step-', OLD.Step_Order, ' set order to: ', NEW.Step_Order , ', from: ', OLD.Step_Order);
        END IF ;

        IF OLD.Parameter != New.Parameter Then
            INSERT INTO test_case_revision SET Test_Case_Id= OLD.Test_Case_Id, Type = 'Update', Content = CONCAT('Update step-', NEW.Step_Order, ' set parameter to: ', NEW.Parameter , ', from: ', OLD.Parameter);
        END IF ;

        IF OLD.Test_Action_Id != NEW.Test_Action_Id THEN
            INSERT INTO test_case_revision SET Test_Case_Id= OLD.Test_Case_Id, Type = 'Update', Content = CONCAT('Update step-', NEW.Step_Order, ' set action to: ', NEW.Test_Action_Id , ', from: ', OLD.Test_Action_Id);
        END IF ;
    END
$$
DELIMITER ;

DROP TRIGGER IF EXISTS `test_step_delete_trigger`;
DELIMITER $$
CREATE TRIGGER `test_step_delete_trigger` AFTER DELETE ON `test_step`
FOR EACH ROW
    INSERT INTO test_case_revision SET Test_Case_Id = OLD.Test_Case_Id, Type = 'Delete', Content = CONCAT('Delete step of order : ', OLD.Step_Order, ', action: ', OLD.Test_Action_Id)
$$
DELIMITER ;