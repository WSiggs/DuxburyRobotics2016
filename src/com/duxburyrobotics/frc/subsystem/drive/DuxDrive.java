package com.duxburyrobotics.frc.subsystem.drive;

import com.duxburyrobotics.frc.settings.Constants;
import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class DuxDrive extends RobotDrive
{

    private SpeedController middleWheelOne;
    private SpeedController middleWheelTwo;

    private SpeedController armLeft;
    private SpeedController armRight;

    private SpeedController intakeMotor;

    private DoubleSolenoid intakeSolenoid;
    private DoubleSolenoid rampSolenoid;

    private DigitalInput limitSwitch;
    private DigitalInput armLimitSwitchOneForward;
    private DigitalInput armLimitSwitchOneBack;
    private DigitalInput armLimitSwitchTwoForward;
    private DigitalInput armLimitSwitchTwoBack;
    private boolean limitSwitchReset = true;
    private boolean limitSwitchResetTwo = true;

    public DuxDrive()
    {
        super(new CANTalon(1), new CANTalon(4), new CANTalon(2), new CANTalon(3));

        this.middleWheelOne = new VictorSP(Constants.MIDDLE_WHEEL_LEFT_PORT);
        this.middleWheelTwo = new VictorSP(Constants.MIDDLE_WHEEL_RIGHT_PORT);

        this.armLeft = new TalonSRX(Constants.ARM_LEFT_MOTOR_PORT);
        this.armRight = new TalonSRX(Constants.ARM_RIGHT_MOTOR_PORT);

        this.intakeMotor = new TalonSRX(Constants.INTAKE_MOTOR_PORT);

        this.intakeSolenoid = new DoubleSolenoid(Constants.INTAKE_SOLENOID_PORT_ONE, Constants.INTAKE_SOLENOID_PORT_TWO);
        this.rampSolenoid = new DoubleSolenoid(Constants.RAMP_SOLENOID_PORT_ONE, Constants.RAMP_SOLENOID_PORT_TWO);

        this.limitSwitch = new DigitalInput(Constants.LIMIT_SWITCH_PORT);
        this.armLimitSwitchOneForward = new DigitalInput(Constants.ARM_LIMIT_SWITCH_PORT_ONE_FORWARD);
        this.armLimitSwitchOneBack = new DigitalInput(Constants.ARM_LIMIT_SWITCH_PORT_ONE_BACK);
        this.armLimitSwitchTwoForward = new DigitalInput(Constants.ARM_LIMIT_SWITCH_PORT_TWO_FORWARD);
        this.armLimitSwitchTwoBack = new DigitalInput(Constants.ARM_LIMIT_SWITCH_PORT_TWO_BACK);
    }

    public void moveArm(double moveValue)
    {
        double calculatedMoveValue = ((moveValue * 100) / Constants.ARM_SPEED_POWER) / 100;

        armLeft.set(calculatedMoveValue);
        armRight.set(calculatedMoveValue);
    }

    public void runIntakeMotor(double speed)
    {
    	SmartDashboard.putBoolean("Limit Switch", limitSwitch.get());

        if (!limitSwitch.get())
        {
            limitSwitchResetTwo = false;
            limitSwitchReset = false;
        }

        if (limitSwitch.get() && limitSwitchReset && limitSwitchResetTwo)
        {
            intakeMotor.set(speed);
        }
    }

    public void resetLimitSwitch()
    {
        limitSwitchReset = true;
    }

    public void movePneumatics(int direction)
    {
        if (limitSwitch.get() || limitSwitchReset)
        {
        	if (direction == 1)
            {
                intakeSolenoid.set(DoubleSolenoid.Value.kForward);
            }
            else if (direction == -1)
            {
                intakeSolenoid.set(DoubleSolenoid.Value.kReverse);
            }
            else
            {
                intakeSolenoid.set(DoubleSolenoid.Value.kOff);
            }
        	
        	if (limitSwitchReset && !limitSwitch.get())
        		limitSwitchReset = false;
        }
        else
        {
            intakeSolenoid.set(DoubleSolenoid.Value.kReverse);
        }
        
    }

    public void moveArmWithLimitSwitchChecking(boolean forward)
    {
        if (forward)
        {
            if (!armLimitSwitchOneForward.get())
                armLeft.set(0.33);
            if (!armLimitSwitchTwoForward.get())
                armRight.set(0.33);
        }
        else
        {
            if (!armLimitSwitchOneBack.get())
                armLeft.set(-0.33);
            if (!armLimitSwitchTwoBack.get())
                armRight.set(-0.33);
        }

    }

    public void arcadeDrive(double moveValue, double rotateValue, boolean shouldMoveMiddleWheel)
    {
        double calculatedMoveValue;
        double calculatedRotateValue;

        /** Forward / Back calculations */

        if (moveValue < 0.0)
        {
            // NOTE (Brendan): This means that the value is negative.
            calculatedMoveValue = -(Math.pow(Math.abs(moveValue), 1.8));
        }
        else
        {
            calculatedMoveValue = Math.pow(moveValue, 1.8);
        }

        /** Check if we should activate the middle wheel **/

        if(shouldMoveMiddleWheel)
        {
            middleWheelOne.set(-calculatedMoveValue);
            middleWheelTwo.set(calculatedMoveValue);
        }
        else
        {
            middleWheelOne.set(0);
            middleWheelTwo.set(0);
        }

        /** Actually move the bot */

        calculatedMoveValue = -calculatedMoveValue;
        rotateValue = -rotateValue;

        super.arcadeDrive(calculatedMoveValue, rotateValue, false);

    }

}
