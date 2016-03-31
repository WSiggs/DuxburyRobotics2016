package com.duxburyrobotics.frc.subsystem.control;

import com.duxburyrobotics.frc.subsystem.drive.DuxDriveHelper;

public class Autonomous
{

    private final byte AUTO_INSTRUCTION = 0x00;
    private final byte CHEVAL_INSTRUCTION = 0x01;
    private final byte PORTCULLIS_INSTRUCTION = 0x02;

    private int mode = 0; // 0 == Low Bar/Goal, 1 == Pass B & D Defense, 2 == Portcullis, 3 == Cheval

    private DuxDriveHelper duxDrive;

    private int currentInstruction;

    private long lastTime;
    
    private boolean firstTimeRun = true;

    public Autonomous(DuxDriveHelper duxDrive, int mode)
    {
        this.duxDrive = duxDrive;
        this.mode = mode;

        this.currentInstruction = 0;

        this.lastTime = System.nanoTime();
    }

    public void init()
    {
    	currentInstruction = 0;
    	firstTimeRun = true;
    }
    
    public void autonomous()
    {
    	if (firstTimeRun)
    	{
    		lastTime = System.nanoTime();
    		firstTimeRun = false;
    	}
    	
    	if (mode == 0)
    	{
    		if (currentInstruction == 0)
    		{
    			incrementTime(1);
    		}
    		else if (currentInstruction == 1)
    		{
    			duxDrive.lowerIntake();
    			incrementTime(1);
    		}
    		else if (currentInstruction == 2)
    		{
    			duxDrive.raiseIntake();
    			incrementTime(1);
    		}
    		else if (currentInstruction == 3)
    		{
    			duxDrive.arcadeDrive(-0.85, 0.0, true);
    			incrementTime(4);
    		}
    	}
    	else if (mode == 1)
    	{
    		if(currentInstruction == 0)
    		{
    			duxDrive.arcadeDrive(-0.66, 0.0, false);
    			incrementTime(4);
    		}
    	}
    	else if (mode == 2)
    	{
    		if(currentInstruction == 0)
    		{
    			duxDrive.moveArm(0.75);
    			incrementTime(2);
    		}
    		else if (currentInstruction == 1)
    		{
    			duxDrive.arcadeDrive(-0.66, 0.0, false);
    			incrementTime(4);
    		}
    	}
    	else if (mode == 3)
    	{
    		if(currentInstruction == 0)
    		{
    			duxDrive.arcadeDrive(-0.3, 0.0, false);
    			incrementTime(4);
    		}
    		else if (currentInstruction == 1)
    		{
    			duxDrive.moveArm(0.75);
    			incrementTime(2);
    		}
    		else if (currentInstruction == 2)
    		{
    			duxDrive.arcadeDrive(-0.66, 0.0, false);
    			incrementTime(4);
    		}
    		else if (currentInstruction == 3)
    		{
    			duxDrive.moveArm(-0.75);
    			incrementTime(2);
    		}
    	}
    }

    public void incrementTime(double secondsToWait)
    {
        if (checkTime(secondsToWait))
                currentInstruction++;
    }

    public boolean checkTime(double secondsToWait)
    {
        if ((System.nanoTime() - lastTime) >= (secondsToWait * 1000000000))
        {
            lastTime = System.nanoTime();
            return true;
        }

        return false;
    }

}