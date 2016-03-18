
package com.duxburyrobotics.frc;

import com.duxburyrobotics.frc.subsystem.control.Autonomous;
import com.duxburyrobotics.frc.subsystem.control.Teleop;
import com.duxburyrobotics.frc.subsystem.drive.DuxDriveHelper;
import edu.wpi.first.wpilibj.IterativeRobot;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot
{

    private DuxDriveHelper duxDriveHelper;

    private Teleop teleop;
    private Autonomous auto;

    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit()
    {
        duxDriveHelper = new DuxDriveHelper();

        auto = new Autonomous(duxDriveHelper);
        teleop = new Teleop(duxDriveHelper, auto);

    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic()
    {
        auto.autonomous();
    }

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic()
    {
        teleop.periodic();
    }
    
}
