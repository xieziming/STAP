--
-- Triggers `execution_context`
--

DROP TRIGGER IF EXISTS `execution_context_insert_trigger`;
DELIMITER $$
CREATE TRIGGER `execution_context_insert_trigger` AFTER INSERT ON `execution_context`
FOR EACH ROW
    BEGIN
        INSERT INTO execution_revision SET Execution_Context_Id = NEW.Id, Type='Insert', Content = CONCAT('Insert execution context { name : ', NEW.Name, ', context: ', NEW.Content, ', remark: ', NEW.Remark, ' }');
    END
$$
DELIMITER ;

DROP TRIGGER IF EXISTS `execution_context_update_trigger`;
DELIMITER $$
CREATE TRIGGER `execution_context_update_trigger` AFTER UPDATE ON `execution_context`
FOR EACH ROW
    BEGIN
        IF OLD.Name != New.Name Then
            INSERT INTO execution_revision SET Execution_Context_Id = NEW.Id, Type = 'Update', Content = CONCAT('Update name to: ', NEW.Name , ', from: ', OLD.Name);
        END IF ;

        IF OLD.Content != NEW.Content THEN
            INSERT INTO execution_revision SET Execution_Context_Id = NEW.Id, Type = 'Update', Content = CONCAT('Update context to: ', NEW.Content , ', from: ', OLD.Content);
        END IF ;

        IF OLD.Remark != NEW.Remark THEN
            INSERT INTO execution_revision SET Execution_Context_Id = NEW.Id, Type = 'Update', Content = CONCAT('Update remark to: ', NEW.Remark , ', from: ', OLD.Remark);
        END IF ;
    END
$$
DELIMITER ;

DROP TRIGGER IF EXISTS `execution_context_delete_trigger`;
DELIMITER $$
CREATE TRIGGER `execution_context_delete_trigger` AFTER DELETE ON `execution_context`
FOR EACH ROW
    BEGIN
        INSERT INTO execution_revision SET Execution_Context_Id = OLD.Id, Type='Delete', Content = CONCAT('Delete execution context of : ', OLD.Name);
    END
$$
DELIMITER ;