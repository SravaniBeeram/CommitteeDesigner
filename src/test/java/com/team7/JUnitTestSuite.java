package com.team7;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

//JUnit Suite Test
@RunWith(Suite.class)

@Suite.SuiteClasses({ 
   ImplementRegisterTest.class,ImplementSearchTest.class,ImplementQueryBuilderTest.class,ImplementLoginTest.class,ImplementParseDatabaseTest.class,ImplementSchemaDBTest.class,
   ImplementCommitteesTest.class, SearchParameterTest.class, 
   ImplementLoginUITest.class, ImplementRegisterUITest.class,
   ImplementSearchUI.class, ImplementDisplayUI.class, ImplementSavedUITest.class
})

public class JUnitTestSuite {

}
