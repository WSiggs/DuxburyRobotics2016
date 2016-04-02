package com.duxburyrobotics.frc.subsystem.control;

import com.duxburyrobotics.frc.subsystem.drive.DuxDriveHelper;

public class Autonomous
{
	private int mode = 0; // 0 == Low Bar/Goal, 1 == Pass B & D Defense, 2 == Portcullis, 3 == Cheval

    private final DuxDriveHelper duxDrive;

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
    	
    	if (mode == 0) // b + d defenses
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
    	else if (mode == 1) // simple low bar
    	{
    		if(currentInstruction == 0)
    		{
    			duxDrive.arcadeDrive(-0.66, 0.0, false);
    			incrementTime(4);
    		}
    	}
    	else if (mode == 2) // portcullis
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
    	else if (mode == 3) // cheval
    	{
    		if (currentInstruction == 0)
    		{
    			duxDrive.lowerIntake();
    			incrementTime(1);
    		}
    		else if (currentInstruction == 1)
    		{
    			duxDrive.raiseIntake();
    			incrementTime(1);
    		}
    		else if(currentInstruction == 2)
    		{
    			duxDrive.arcadeDrive(-0.3, 0.0, false);
    			incrementTime(4.5);
    		}
    		else if (currentInstruction == 3)
    		{
    			duxDrive.moveArm(0.5);
    			incrementTime(2);
    		}
    		else if (currentInstruction == 4)
    		{
    			duxDrive.arcadeDrive(-0.66, 0.0, false);
    			incrementTime(3);
    		}
    		else if (currentInstruction == 5)
    		{
    			duxDrive.moveArm(-0.75);
    			incrementTime(2);
    		}
    	}
    	else if (mode == 4) // low goal 
        {
            if(currentInstruction == 0)
            {
                duxDrive.arcadeDrive(-0.66, 0.0, false);
                incrementTime(4);
            }
            else if(currentInstruction == 1)
            {
                duxDrive.arcadeDrive(0, -0.5, false);
                incrementTime(2);
            }
            else if(currentInstruction == 2)
            {
                duxDrive.arcadeDrive(-0.5, 0.0, false);
                incrementTime(2);
            }
            else if(currentInstruction == 3)
            {
                duxDrive.runIntakeMotor(1.0);
                incrementTime(2);
            }
        }
    }

    private void incrementTime(double secondsToWait)
    {
        if (checkTime(secondsToWait))
                currentInstruction++;
    }

    private boolean checkTime(double secondsToWait)
    {
        if ((System.nanoTime() - lastTime) >= (secondsToWait * 1000000000))
        {
            lastTime = System.nanoTime();
            return true;
        }

        return false;
    }

}
