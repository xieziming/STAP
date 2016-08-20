--
-- Triggers `execution_plan`
--

DROP TRIGGER IF EXISTS `execution_plan_insert_trigger`;
DELIMITER $$
CREATE TRIGGER `execution_plan_insert_trigger` AFTER INSERT ON `execution_plan`
FOR EACH ROW
    BEGIN
        INSERT INTO execution_revision SET Execution_Plan_Id = NEW.Id, Type='Insert', Content = CONCAT('Insert execution plan { name : ', NEW.Name, ', description: ', NEW.Description, ', status: ', NEW.Status, ' }');
    END
$$
DELIMITER ;

DROP TRIGGER IF EXISTS `execution_plan_update_trigger`;
DELIMITER $$
CREATE TRIGGER `execution_plan_update_trigger` AFTER UPDATE ON `execution_plan`
FOR EACH ROW
    BEGIN
        IF OLD.Name != New.Name Then
            INSERT INTO execution_revision SET Execution_Plan_Id = NEW.Id, Type = 'Update', Content = CONCAT('Update name to: ', NEW.Name , ', from: ', OLD.Name);
            INSERT INTO notification (WATCH_LIST_ID, CONTENT, `STATUS`) (SELECT Id, CONCAT('Name changed to ', NEW.Name), 'Produced' FROM watch_List WHERE Execution_Plan_Id = NEW.id);
        END IF ;

        IF OLD.Description != NEW.Description THEN
            INSERT INTO execution_revision SET Execution_Plan_Id = NEW.Id, Type = 'Update', Content = CONCAT('Update description to: ', NEW.Description , ', from: ', OLD.Description);
            INSERT INTO notification (WATCH_LIST_ID, CONTENT, `STATUS`) (SELECT Id, CONCAT('Description changed to ', NEW.Description), 'Produced' FROM watch_List WHERE Execution_Plan_Id = NEW.id);
        END IF ;

        IF OLD.Status != NEW.Status THEN
            INSERT INTO execution_revision SET Execution_Plan_Id = NEW.Id, Type = 'Update', Content = CONCAT('Update status to: ', NEW.Status , ', from: ', OLD.Status);
            INSERT INTO notification (WATCH_LIST_ID, CONTENT, `STATUS`) (SELECT Id, CONCAT('Status changed to ', NEW.Status), 'Produced' FROM watch_List WHERE Execution_Plan_Id = NEW.id);
        END IF ;
    END
$$
DELIMITER ;

DROP TRIGGER IF EXISTS `execution_plan_delete_trigger`;
DELIMITER $$
CREATE TRIGGER `execution_plan_delete_trigger` AFTER DELETE ON `execution_plan`
FOR EACH ROW
    BEGIN
        INSERT INTO trash SET Content = CONCAT('Delete test case of : ', OLD.Name);
    END
$$
DELIMITER ;