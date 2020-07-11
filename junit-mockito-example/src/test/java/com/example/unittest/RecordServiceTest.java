package com.example.unittest;

import org.junit.*;
import org.junit.runner.RunWith;
import org.mockito.Matchers;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.reflect.Whitebox;
/*In spring enviroment
    *
@RunWith(MockitoJUnitRunner.class)
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest(Util.class)
//@PrepareForTest(DataCollectingService.class)-use this when call test_Mock_private_method insted
public class RecordServiceTest {
    /*In spring env
    *
    @InjectMocks
    RecordService recordService;

    @Mock
    DatabaseDAO databaseMock;

    @Mock
    NetworkDAO networkMock;
    *
    and setUp method will be empty/removed
    * */
    RecordService recordService;
    DatabaseDAO databaseDAO;
    NetworkDAO networkDAO;
    SensorsDAO sensorsDAO;
    @BeforeClass
    public static void beforeClass(){
        //here we can add some configurations for out static methods
    }

    @Before
    public void setUp(){
        databaseDAO= Mockito.mock(DatabaseDAO.class);
        networkDAO=Mockito.mock(NetworkDAO.class);
        sensorsDAO=Mockito.mock(SensorsDAO.class);
        recordService=new RecordService(databaseDAO,networkDAO,sensorsDAO);
    }

    /*
    Stubbing VS Mocking
    *mock-creating a complete mock(fake object).The default behaviour of the method will be do nothing .This is changes when you stub it/mock it.
    *spy -creating a real object ,but we can replace part of its functionality
     */
    @Test
    public void test_instance_method_when_you_want_to_call_actual_method(){
        Assert.assertNotNull("RecordService is not null",recordService);
        //we need to mock metod save of DatabaseDao,because DatabaseDao is mock object
        //using this way we call the actual method
        Mockito.when(databaseDAO.save(Mockito.anyString())).thenReturn(true);
        //Mockito.doReturn(true).when(databaseDAO.save(Mockito.anyString()));
        boolean output=recordService.save("MsP");
        //check if the method is called once;we could check only for mock objects
        Mockito.verify(databaseDAO,Mockito.times(1)).save(Mockito.anyString());
        //check if the method is called atLeastOnce
        Mockito.verify(databaseDAO,Mockito.atLeast(1)).save(Mockito.anyString());
        Assert.assertTrue("Record Service is save",output);

    }
    
    @Test
    public void test_instance_method_when_you_dont_want_to_call_the_actual_method(){
        Assert.assertNotNull("RecordService is not null",recordService);
        //we need to mock metod save of DatabaseDao,because DatabaseDao is mock object
        //using this way we don`t call the actual method ->dirrectly replace with result
        Mockito.doReturn(true).when(databaseDAO).save2(Matchers.anyString());
        boolean output=recordService.save2("MsPtest");
        //check if the method is called once
        Mockito.verify(databaseDAO,Mockito.atLeast(1)).save2(Mockito.anyString());
        //check if the method is called atLeastOnce
       // Mockito.verify(recordService,Mockito.atLeast(1)).save2(Mockito.anyString());
        Assert.assertTrue("Record Service is save",output);

    }
    //test void method
    @Test
    public void test_void_method(){
        Assert.assertNotNull("RecordService is not null",recordService);
        //static method mock
        PowerMockito.mockStatic(Util.class);
        PowerMockito.when(Util.dataTransformation1(Matchers.anyString(),Matchers.anyString())).thenReturn("testtest");
        //void method
        Mockito.doAnswer(new Answer<Void>() {
            @Override
            public Void answer(InvocationOnMock invocation) throws Throwable {
                Object[] arguments = invocation.getArguments();
                if (arguments != null && arguments.length >= 1 && arguments[0] != null ) {
                   // System.out.println("test");
                    Assert.assertTrue("Validation of input",arguments[0].toString().length()==8);
                }
                return null;
            }
        }).when(sensorsDAO).send_response(Matchers.anyString());

        recordService.readInfo("test","new");
        Mockito.verify(sensorsDAO,Mockito.times(1)).send_response(Mockito.anyString());
    }

    //test with mock static method-PowerMock
    @Test
    public void test_mock_static_method(){
        Assert.assertNotNull("RecordService is not null",recordService);
        PowerMockito.mockStatic(Util.class);
        PowerMockito.when(Util.dataTransformation1(Matchers.anyString(),Matchers.anyString())).thenReturn("testtest");
        recordService.readInfo("test","test");
        Mockito.verify(sensorsDAO,Mockito.times(1)).send_response(Mockito.anyString());
    }



    @After
    public void tearDown(){
        //here you can remove chages
    }
    @AfterClass
    public static void afterClass(){
        //here we can tear down configs for out static methods
    }
}
