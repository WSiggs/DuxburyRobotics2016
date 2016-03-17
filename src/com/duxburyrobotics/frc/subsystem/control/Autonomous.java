package com.duxburyrobotics.frc.subsystem.control;

import com.duxburyrobotics.frc.settings.Constants;
import com.duxburyrobotics.frc.subsystem.drive.DuxDriveHelper;
import edu.wpi.first.wpilibj.Encoder;

public class Autonomous
{

    private final byte AUTO_INSTRUCTION = 0x00;
    private final byte CHEVAL_INSTRUCTION = 0x01;
    private final byte PORTCULLIS_INSTRUCTION = 0x02;

    private final int mode = 0; // 0 == Low Bar/Goal, 1 == Pass B & D Defense, 2 == Portcullis, 3 == Cheval

    private DuxDriveHelper duxDrive;

    private Encoder frontLeftEncoder;
    private Encoder rearLeftEncoder;
    private Encoder frontRightEncoder;
    private Encoder rearRightEncoder;

    private int currentInstruction;
    private int chevalInstruction;
    private int portcullisInstruction;

    private long lastTime;
    private long lastChevalTime;
    private long lastPortcullisTime;

    public Autonomous(DuxDriveHelper duxDrive)
    {
        this.duxDrive = duxDrive;

        this.frontLeftEncoder = new Encoder(Constants.FRONT_LEFT_ENCODER_PORT_ONE, Constants.FRONT_LEFT_ENCODER_PORT_TWO);
        this.rearLeftEncoder = new Encoder(Constants.REAR_LEFT_ENCODER_PORT_ONE, Constants.REAR_LEFT_ENCODER_PORT_TWO);
        this.frontRightEncoder = new Encoder(Constants.FRONT_RIGHT_ENCODER_PORT_ONE, Constants.FRONT_RIGHT_ENCODER_PORT_TWO);
        this.rearRightEncoder = new Encoder(Constants.REAR_RIGHT_ENCODER_PORT_ONE, Constants.REAR_RIGHT_ENCODER_PORT_TWO);

        this.frontLeftEncoder.setMaxPeriod(0.1);
        this.rearLeftEncoder.setMaxPeriod(0.1);
        this.frontRightEncoder.setMaxPeriod(0.1);
        this.rearRightEncoder.setMaxPeriod(0.1);

        this.frontLeftEncoder.setDistancePerPulse(5);
        this.rearLeftEncoder.setDistancePerPulse(5);
        this.frontRightEncoder.setDistancePerPulse(5);
        this.rearRightEncoder.setDistancePerPulse(5);

        this.frontLeftEncoder.setSamplesToAverage(50);
        this.rearLeftEncoder.setSamplesToAverage(50);
        this.frontRightEncoder.setSamplesToAverage(50);
        this.rearRightEncoder.setSamplesToAverage(50);

        this.currentInstruction = 0;
        this.chevalInstruction = 0;
        this.portcullisInstruction = 0;

        this.lastTime = System.nanoTime();
        this.lastChevalTime = lastTime;
        this.lastPortcullisTime = lastTime;
    }

    public void autonomous()
    {
        lastTime = System.nanoTime();
        if (mode == 0) // Low Bar / Goal
        {
            if (currentInstruction == 0)
            {
                duxDrive.lowerIntake();
                incrementTime(0.1, AUTO_INSTRUCTION);
            }
            else if (currentInstruction == 1)
            {
                duxDrive.raiseIntake();
                incrementTime(0.1, AUTO_INSTRUCTION);
            }
            else if (currentInstruction == 2)
            {
                duxDrive.arcadeDrive(0.5, 0.0, true);
                if (frontLeftEncoder.getDistance() == 10.0)
                {
                    currentInstruction++;
                    frontLeftEncoder.reset();
                }
            }
            else if (currentInstruction == 3)
            {
                duxDrive.arcadeDrive(0.0, 0.5, false);
                if (frontRightEncoder.getDistance() == 2)
                {
                    currentInstruction++;
                    frontRightEncoder.reset();
                }
            }
            else if (currentInstruction == 4)
            {
                duxDrive.arcadeDrive(0.5, 0.0, true);
                if (frontLeftEncoder.getDistance() == 5.0)
                {
                    currentInstruction++;
                    frontLeftEncoder.reset();
                }
            }
            else if (currentInstruction == 5)
            {
                duxDrive.lowerIntake();
                incrementTime(0.1, AUTO_INSTRUCTION);
            }
            else if (currentInstruction == 6)
            {
                duxDrive.shootIntake();
                incrementTime(0.1, AUTO_INSTRUCTION);
            }
            else if (currentInstruction == 7)
            {
                duxDrive.arcadeDrive(-0.5, 0.0, false);
                if (frontLeftEncoder.getDistance() == -1.0)
                {
                    currentInstruction++;
                    frontLeftEncoder.reset();
                }
            }
            else if (currentInstruction == 8)
            {
                duxDrive.stopAll();
            }
        }
        else if (mode == 1) // B & D Defenses
        {
            if (currentInstruction == 0)
            {
                duxDrive.lowerIntake();
                incrementTime(0.1, AUTO_INSTRUCTION);
            }
            else if (currentInstruction == 1)
            {
                duxDrive.raiseIntake();
                incrementTime(0.1, AUTO_INSTRUCTION);
            }
            else if (currentInstruction == 2)
            {
                duxDrive.arcadeDrive(1, 0.0, true);
                if (frontLeftEncoder.getDistance() == 10.0)
                {
                    currentInstruction++;
                    frontLeftEncoder.reset();
                }
            }
        }
        else if (mode == 2) // Portcullis
        {
            if (currentInstruction == 0)
            {
                duxDrive.lowerIntake();
                incrementTime(0.1, AUTO_INSTRUCTION);
            }
            else if (currentInstruction == 1)
            {
                duxDrive.raiseIntake();
                incrementTime(0.1, AUTO_INSTRUCTION);
            }
            else if (currentInstruction == 2)
            {
                duxDrive.arcadeDrive(0.5, 0.0, true);
                if (frontLeftEncoder.getDistance() == 1.0)
                {
                    currentInstruction++;
                    frontLeftEncoder.reset();
                }
            }
            else  if (currentInstruction == 3)
            {
                if (portcullisMethod())
                    currentInstruction++;
            }
            else if (currentInstruction == 4)
            {
                duxDrive.arcadeDrive(0.5, 0.0, true);
                if (frontLeftEncoder.getDistance() == 1.0)
                {
                    currentInstruction++;
                    frontLeftEncoder.reset();
                }
            }
        }
        else if (mode == 3) // Cheval
        {
            if (currentInstruction == 0)
            {
                duxDrive.lowerIntake();
                incrementTime(0.1, AUTO_INSTRUCTION);
            }
            else if (currentInstruction == 1)
            {
                duxDrive.raiseIntake();
                incrementTime(0.1, AUTO_INSTRUCTION);
            }
            else if (currentInstruction == 2)
            {
                duxDrive.arcadeDrive(0.5, 0.0, true);
                if (frontLeftEncoder.getDistance() == 1.0)
                {
                    currentInstruction++;
                    frontLeftEncoder.reset();
                }
            }
            else if (currentInstruction == 3)
            {
                if (chevalMethod())
                    currentInstruction++;
            }
            else if (currentInstruction == 4)
            {
                duxDrive.arcadeDrive(0.5, 0.0, true);
                if (frontLeftEncoder.getDistance() == 1.0)
                {
                    currentInstruction++;
                    frontLeftEncoder.reset();
                }
            }
        }

        lastTime = 0;
        currentInstruction = 0;
    }

    public boolean portcullisMethod()
    {
        lastPortcullisTime = System.nanoTime();
        if (portcullisInstruction == 0)
        {
            duxDrive.moveArm(0.25);
            incrementTime(3, PORTCULLIS_INSTRUCTION);
        }
        else if (portcullisInstruction == 1)
        {
            duxDrive.arcadeDrive(0.75, 0.0);
            if (frontLeftEncoder.getDistance() == 1.0)
            {
                portcullisInstruction++;
                frontLeftEncoder.reset();
            }
        }
        else if (portcullisInstruction == 2)
        {
            duxDrive.moveArm(-0.1);
            duxDrive.arcadeDrive(0.5, 0.0);
            if (frontLeftEncoder.getDistance() == 2.5)
            {
                portcullisInstruction = 0;
                return true;
            }
        }

        return false;
    }

    public boolean chevalMethod()
    {
        lastChevalTime = System.nanoTime();
        if (chevalInstruction == 0)
        {
            duxDrive.arcadeDrive(0.5, 0.0);
            if (frontLeftEncoder.getDistance() == 1.0)
            {
                chevalInstruction++;
                frontLeftEncoder.reset();
            }
        }
        else if (chevalInstruction == 1)
        {
            duxDrive.moveArm(0.25);
            incrementTime(1, CHEVAL_INSTRUCTION);
        }
        else if (chevalInstruction == 2)
        {
            duxDrive.arcadeDrive(0.5, 0.0);
            duxDrive.moveArm(-0.1);

            if (checkTime(5, CHEVAL_INSTRUCTION))
            {
                chevalInstruction = 0;
                return true;
            }
        }

        return false;
    }

    public void incrementTime(double secondsToWait, byte instruction)
    {
        if (instruction == AUTO_INSTRUCTION)
            if (checkTime(secondsToWait, instruction))
                currentInstruction++;

        if (instruction == CHEVAL_INSTRUCTION)
            if (checkTime(secondsToWait, instruction))
                chevalInstruction++;

        if (instruction == PORTCULLIS_INSTRUCTION)
            if (checkTime(secondsToWait, instruction))
                portcullisInstruction++;
    }

    public boolean checkTime(double secondsToWait, byte instruction)
    {
        if (instruction == AUTO_INSTRUCTION)
        {
            if ((System.nanoTime() - lastTime) >= (secondsToWait * 1000000000))
            {
                lastTime = System.nanoTime();
                return true;
            }

            return false;
        }
        else if (instruction == CHEVAL_INSTRUCTION)
        {
            if ((System.nanoTime() - lastChevalTime) >= (secondsToWait * 1000000000))
            {
                lastChevalTime = System.nanoTime();
                return true;
            }

            return false;
        }
        else if (instruction == PORTCULLIS_INSTRUCTION)
        {
            if ((System.nanoTime() - lastPortcullisTime) >= (secondsToWait * 1000000000))
            {
                lastPortcullisTime = System.nanoTime();
                return true;
            }

            return false;
        }

        return false;
    }

    public void chevalReset()
    {
        chevalInstruction = 0;
    }

    public void portcullisReset()
    {
        portcullisInstruction = 0;
    }

}
