--
-- Triggers `test_case_meta`
--

DROP TRIGGER IF EXISTS `test_case_meta_insert_trigger`;
DELIMITER $$
CREATE TRIGGER `test_case_meta_insert_trigger` AFTER INSERT ON `test_case_meta`
FOR EACH ROW
    BEGIN
        INSERT INTO test_case_revision SET Test_Case_Id = NEW.Test_Case_Id, Type='Insert', Content = CONCAT('Insert meta { metaType : ', NEW.Meta_Type, ', metaKey: ', NEW.Meta_Key, ', metaValue: ', NEW.Meta_Value, ' }');
    END
$$
DELIMITER ;

DROP TRIGGER IF EXISTS `test_case_meta_update_trigger`;
DELIMITER $$
CREATE TRIGGER `test_case_meta_update_trigger` AFTER UPDATE ON `test_case_meta`
FOR EACH ROW
    BEGIN
        IF OLD.Meta_Type != New.Meta_Type Then
            INSERT INTO test_case_revision SET Test_Case_Id = NEW.Test_Case_Id, Type = 'Update', Content = CONCAT('Update meta(', OLD.Meta_Type, ', ', NEW.Meta_Key, ') set metaType to: ', NEW.Meta_Type , ', from: ', OLD.Meta_Type);
        END IF ;

        IF OLD.Meta_Key != NEW.Meta_Key THEN
            INSERT INTO test_case_revision SET Test_Case_Id = NEW.Test_Case_Id, Type = 'Update', Content = CONCAT('Update meta(', OLD.Meta_Type, ', ', OLD.Meta_Key, ') set metaKey to: ', NEW.Meta_Key , ', from: ', OLD.Meta_Key);
        END IF ;

        IF OLD.Meta_Value != NEW.Meta_Value THEN
            INSERT INTO test_case_revision SET Test_Case_Id = NEW.Test_Case_Id, Type = 'Update', Content = CONCAT('Update meta(', OLD.Meta_Type, ', ', NEW.Meta_Key, ') set metaValue to: ', NEW.Meta_Value , ', from: ', OLD.Meta_Value);
        END IF ;
    END
$$
DELIMITER ;

DROP TRIGGER IF EXISTS `test_case_meta_delete_trigger`;
DELIMITER $$
CREATE TRIGGER `test_case_meta_delete_trigger` AFTER DELETE ON `test_case_meta`
FOR EACH ROW
    BEGIN
        INSERT INTO test_case_revision SET Test_Case_Id = OLD.Test_Case_Id, Type='Delete', Content = CONCAT('Delete meta of : ', OLD.Test_Case_Id);
    END
$$
DELIMITER ;