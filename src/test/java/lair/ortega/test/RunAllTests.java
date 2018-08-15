package lair.ortega.test;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({
	lair.ortega.dao.UserDaoTest.class,
	lair.ortega.logic.UserLogicTest.class
})
public class RunAllTests {

}
