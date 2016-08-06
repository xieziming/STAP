--
-- Triggers `test_case_relation`
--

DROP TRIGGER IF EXISTS `test_case_relation_insert_trigger`;
DELIMITER $$
CREATE TRIGGER `test_case_relation_insert_trigger` AFTER INSERT ON `test_case_relation`
FOR EACH ROW
    BEGIN
        INSERT INTO test_case_revision SET Test_Case_Id = NEW.Test_Case_Id, Type='Insert', Content = CONCAT('Insert relation { relatedTestCase : ', NEW.Related_Test_Case_Id, ', relation: ', NEW.Relation, ', remark: ', NEW.Remark, ' }');
        INSERT INTO test_case_revision SET Test_Case_Id = NEW.Related_Test_Case_Id, Type='Insert', Content = CONCAT('Be inserted relation { testCase : ', NEW.Test_Case_Id, ', relation: ', NEW.Relation, ', remark: ', NEW.Remark, ' }');
    END
$$
DELIMITER ;

DROP TRIGGER IF EXISTS `test_case_relation_update_trigger`;
DELIMITER $$
CREATE TRIGGER `test_case_relation_update_trigger` AFTER UPDATE ON `test_case_relation`
FOR EACH ROW
    BEGIN
        IF OLD.Test_Case_Id != NEW.Test_Case_Id THEN
            INSERT INTO test_case_revision SET Test_Case_Id = NEW.Related_Test_Case_Id, Type = 'Update', Content = CONCAT('Update testCaseId to: ', NEW.Test_Case_Id , ', from: ', OLD.Test_Case_Id);
        END IF ;

        IF OLD.Related_Test_Case_Id != New.Related_Test_Case_Id Then
            INSERT INTO test_case_revision SET Test_Case_Id = NEW.Test_Case_Id, Type = 'Update', Content = CONCAT('Update relatedTestCaseId to: ', NEW.Related_Test_Case_Id , ', from: ', OLD.Related_Test_Case_Id);
        END IF ;

        IF OLD.Relation != NEW.Relation THEN
            INSERT INTO test_case_revision SET Test_Case_Id = NEW.Test_Case_Id, Type = 'Update', Content = CONCAT('Update relation to: ', NEW.Relation , ', from: ', OLD.Relation);
            INSERT INTO test_case_revision SET Test_Case_Id = NEW.Related_Test_Case_Id, Type= 'Update', Content = CONCAT('Update relation to: ', NEW.Relation , ', from: ', OLD.Relation);

        END IF ;

        IF OLD.Remark != NEW.Remark THEN
            INSERT INTO test_case_revision SET Test_Case_Id = NEW.Test_Case_Id, Type = 'Update', Content = CONCAT('Update remark to: ', NEW.Remark , ', from: ', OLD.Remark);
            INSERT INTO test_case_revision SET Test_Case_Id = NEW.Related_Test_Case_Id, Type = 'Update', Content = CONCAT('Update remark to: ', NEW.Remark , ', from: ', OLD.Remark);

        END IF ;
    END
$$
DELIMITER ;

DROP TRIGGER IF EXISTS `test_case_relation_delete_trigger`;
DELIMITER $$
CREATE TRIGGER `test_case_relation_delete_trigger` AFTER DELETE ON `test_case_relation`
FOR EACH ROW
    BEGIN
        INSERT INTO test_case_revision SET Test_Case_Id = OLD.Test_Case_Id, Type='Delete', Content = CONCAT('Delete relation to : ', OLD.Related_Test_Case_Id);
        INSERT INTO test_case_revision SET Test_Case_Id = OLD.Related_Test_Case_Id, Type = 'Delete', Content = CONCAT('Delete relation to : ', OLD.Test_Case_Id);
    END
$$
DELIMITER ;