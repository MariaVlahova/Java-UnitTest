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

//In presentation i forget to change @PrepareForTest to use DataCollectingService.class instead of Util.class
@PrepareForTest(DataCollectingService.class)
@RunWith(PowerMockRunner.class)
public class DataCollectingServiceTest {
        private DataCollectingService spy;

        @Before
        public void setUp(){
            spy=PowerMockito.spy(new DataCollectingService());
        }


        @Test
        public void test_Mock_private_method() throws Exception {
            Assert.assertTrue("DataCollectingService is not null",spy!=null);
            PowerMockito.doReturn("from spy method").when(spy, "getInfoFromUSB");
            PowerMockito.when(spy.collectData()).thenCallRealMethod();
            //if method expect params
            //PowerMockito.doReturn("from spy method").when(spy, "getInfoFromUSB",Matchers.anyString());
            String result=spy.collectData();
            Assert.assertTrue("Data is correct",result.equals("from spy method"));

        }
        //mock private method (partia mocking /spy)
        @Test
        public void test_call_private_method()throws Exception {
            Assert.assertNotNull("RecordService is not null",spy);
            //calling private method
            String result = Whitebox.invokeMethod(spy, "getInfoFromUSB");
            Assert.assertTrue(result!=null);
        }


        @After
        public void tearDown(){
            //here you can remove chages
        }


}
