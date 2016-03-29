
package com.duxburyrobotics.frc;

import com.duxburyrobotics.frc.subsystem.control.Autonomous;
import com.duxburyrobotics.frc.subsystem.control.Teleop;
import com.duxburyrobotics.frc.subsystem.drive.DuxDriveHelper;

import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

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
    
    private CameraServer camera;
    
    private Command autoCommand;
    private SendableChooser autoChooser;
    
    private Autonomous currentAuto;

    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit()
    {
        duxDriveHelper = new DuxDriveHelper();

        auto = new Autonomous(duxDriveHelper, 0);
        teleop = new Teleop(duxDriveHelper, auto);
        
        autoChooser = new SendableChooser();
        autoChooser.addDefault("Rockwall", auto);
        autoChooser.addObject("Drive forward", new Autonomous(duxDriveHelper, 1));
        autoChooser.addObject("Portcullis", new Autonomous(duxDriveHelper, 2));
        autoChooser.addObject("Chevel", new Autonomous(duxDriveHelper, 3));
        SmartDashboard.putData("Auto Chooser", autoChooser);
        
        camera = CameraServer.getInstance();
        camera.startAutomaticCapture("cam0");
    }
    
    public void autonomousInit()
    {
    	((Autonomous)autoChooser.getSelected()).init();
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic()
    {
        ((Autonomous)autoChooser.getSelected()).autonomous();
    }

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic()
    {
        teleop.periodic();
    }
    
}
