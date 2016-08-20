--
-- Triggers `comment`
--

DROP TRIGGER IF EXISTS `comment_insert_trigger`;
DELIMITER $$
CREATE TRIGGER `comment_insert_trigger` AFTER INSERT ON `comment`
FOR EACH ROW
    BEGIN
        IF NEW.Execution_Id IS NOT NULL Then
            INSERT INTO notification (WATCH_LIST_ID, CONTENT, `STATUS`) (SELECT Id, CONCAT('New comment added :', NEW.Content), 'Produced' FROM watch_List WHERE Execution_Id = NEW.Execution_Id);
        END IF;

        IF NEW.Execution_Plan_Id IS NOT NULL Then
            INSERT INTO notification (WATCH_LIST_ID, CONTENT, `STATUS`) (SELECT Id, CONCAT('New comment added :', NEW.Content), 'Produced' FROM watch_List WHERE Execution_Plan_Id = NEW.Execution_Plan_Id);
        END IF;

        IF NEW.Test_Case_Id IS NOT NULL Then
            INSERT INTO notification (WATCH_LIST_ID, CONTENT, `STATUS`) (SELECT Id, CONCAT('New comment added :', NEW.Content), 'Produced' FROM watch_List WHERE Test_Case_Id = NEW.Test_Case_Id);
        END IF;
    END
$$
DELIMITER ;