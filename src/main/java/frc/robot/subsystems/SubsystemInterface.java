/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

/**
 * Add your docs here.
 */
public interface SubsystemInterface {


    // Every Subsystem needs  way to be disabled
    void disable();


    // Every Subsystem has data that should be published
    void publishData();

    // All subsystems must periodically be outputting information from the publishData() method
    void periodic();
}
