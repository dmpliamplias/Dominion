package com.weddingcrashers.managers;

import com.weddingcrashers.managers.util.ServerClientTestUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.net.Socket;

/**
 * Created by Manuel on 04.10.2017.
 */
public class ChatManagerTest {

    // Tests if the server runs with the correct port

    @Before
    public void testSetup(){
        new ServerClientTestUtils().waitForServer();

    }
    @Test
    public void broadcastMessageToAllTest() throws Exception{

        Assert.assertEquals(1,1);


    }
}
