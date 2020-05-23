package com;

import java.util.Random;

public class Waiter
{
    public static void sleep(int bound, int minTime)
    {
        int randomTime = minTime + new Random().nextInt(bound);
        try
        {
            Thread.sleep(randomTime);
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
    }
}
