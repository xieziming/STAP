--
-- Triggers `execution`
--

DROP TRIGGER IF EXISTS `execution_update_trigger`;
DELIMITER $$
CREATE TRIGGER `execution_update_trigger` AFTER UPDATE ON `execution`
FOR EACH ROW
    BEGIN
        IF OLD.Remark != New.Remark Then
            INSERT INTO notification (WATCH_LIST_ID, CONTENT, `STATUS`) (SELECT Id, CONCAT('Remark changed to ', NEW.Remark), 'Produced' FROM watch_List WHERE Execution_Id = NEW.id);
        END IF ;

        IF OLD.Result != NEW.Result THEN
            INSERT INTO notification (WATCH_LIST_ID, CONTENT, `STATUS`) (SELECT Id, CONCAT('Result changed to ', NEW.Result), 'Produced' FROM watch_List WHERE Execution_Id = NEW.id);
        END IF ;

        IF OLD.Status != NEW.Status THEN
            INSERT INTO notification (WATCH_LIST_ID, CONTENT, `STATUS`) (SELECT Id, CONCAT('Status changed to ', NEW.Status), 'Produced' FROM watch_List WHERE Execution_Id = NEW.id);
        END IF ;
    END
$$
DELIMITER ;