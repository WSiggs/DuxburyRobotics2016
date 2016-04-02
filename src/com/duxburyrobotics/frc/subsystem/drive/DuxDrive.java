package com.duxburyrobotics.frc.subsystem.drive;

import com.duxburyrobotics.frc.settings.Constants;
import edu.wpi.first.wpilibj.*;

public class DuxDrive extends RobotDrive
{

    private final SpeedController middleWheelOne;
    private final SpeedController middleWheelTwo;

    private final SpeedController armLeft;
    private final SpeedController armRight;

    private final SpeedController intakeMotor;

    private final DoubleSolenoid intakeSolenoid;

    DuxDrive()
    {
    	//    Front Left       Rear left        Front Right      Rear Right
        super(new CANTalon(1), new CANTalon(4), new CANTalon(2), new CANTalon(3));

        this.middleWheelOne = new VictorSP(Constants.Motors.MIDDLE_WHEEL_LEFT_PORT);
        this.middleWheelTwo = new VictorSP(Constants.Motors.MIDDLE_WHEEL_RIGHT_PORT);

        this.armLeft = new TalonSRX(Constants.Motors.ARM_LEFT_MOTOR_PORT);
        this.armRight = new TalonSRX(Constants.Motors.ARM_RIGHT_MOTOR_PORT);

        this.intakeMotor = new TalonSRX(Constants.Motors.INTAKE_MOTOR_PORT);

        this.intakeSolenoid = new DoubleSolenoid(Constants.Pneumatics.INTAKE_SOLENOID_PORT_ONE, Constants.Pneumatics.INTAKE_SOLENOID_PORT_TWO);
    }

    public void moveArm(double moveValue)
    {
        double calculatedMoveValue = ((moveValue * 100) / Constants.Misc.ARM_SPEED_POWER) / 100;

        armLeft.set(calculatedMoveValue);
        armRight.set(calculatedMoveValue);
    }

    public void runIntakeMotor(double speed)
    {
    	intakeMotor.set(speed);
    }

    public void movePneumatics(int direction)
    {
    	if (direction == 1)
    		intakeSolenoid.set(DoubleSolenoid.Value.kForward);
    	else if (direction == -1)
            intakeSolenoid.set(DoubleSolenoid.Value.kReverse);
        else
        	intakeSolenoid.set(DoubleSolenoid.Value.kOff);
    }

    public void arcadeDrive(double moveValue, double rotateValue, boolean shouldMoveMiddleWheel)
    {
        double calculatedMoveValue;

        /** Forward / Back calculations */

        if (moveValue < 0.0)
            // NOTE (Brendan): This means that the value is negative.
            calculatedMoveValue = -(Math.pow(Math.abs(moveValue), 1.8));
        else
            calculatedMoveValue = Math.pow(moveValue, 1.8);

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
        super.arcadeDrive(-calculatedMoveValue, -rotateValue, false);

    }
}
